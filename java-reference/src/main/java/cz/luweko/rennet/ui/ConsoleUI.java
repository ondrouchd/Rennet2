package cz.luweko.rennet.ui;

import cz.luweko.rennet.calculator.RennetCalculator;
import cz.luweko.rennet.calculator.TemperatureConverter;
import cz.luweko.rennet.model.CheeseType;
import cz.luweko.rennet.model.MilkType;
import cz.luweko.rennet.model.Recipe;

import java.util.Scanner;

/**
 * Console-based user interface for the Rennet cheese-making advisor.
 */
public class ConsoleUI {

    private final Scanner scanner;
    private final RennetCalculator calculator;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.calculator = new RennetCalculator();
    }

    /**
     * Starts the interactive console application.
     */
    public void run() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   Sýrařův rádce (Rennet v1.0)       ║");
        System.out.println("║   Cheese-maker's Advisor             ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Vaše volba: ", 1, 4);

            switch (choice) {
                case 1:
                    calculateRecipe();
                    break;
                case 2:
                    showCheeseTypes();
                    break;
                case 3:
                    convertTemperature();
                    break;
                case 4:
                    running = false;
                    System.out.println("Na shledanou! (Goodbye!)");
                    break;
                default:
                    break;
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("--- Hlavní menu ---");
        System.out.println("1. Vypočítat recept (Calculate recipe)");
        System.out.println("2. Přehled typů sýrů (Cheese types overview)");
        System.out.println("3. Převod teploty (Temperature conversion)");
        System.out.println("4. Konec (Exit)");
    }

    private void calculateRecipe() {
        System.out.println("\n--- Výpočet receptu ---");

        MilkType milkType = selectMilkType();
        CheeseType cheeseType = selectCheeseType();
        double milkLiters = readDouble("Množství mléka v litrech (Milk in liters): ", 0.1, 1000.0);

        double rennetMl = calculator.calculateRennetMl(milkLiters, cheeseType, milkType);
        double yieldKg = calculator.estimateYieldKg(milkLiters, cheeseType, milkType);

        Recipe recipe = new Recipe(
            cheeseType.getCzechName() + " z " + milkType.getCzechName().toLowerCase() + "ho mléka",
            cheeseType, milkType, milkLiters, rennetMl,
            cheeseType.getOptimalTempCelsius(), cheeseType.getCoagulationMinutes(), yieldKg
        );

        System.out.println("\n" + recipe);
    }

    private void showCheeseTypes() {
        System.out.println("\n--- Přehled typů sýrů ---");
        System.out.printf("%-15s %-10s %-15s %-10s%n",
            "Typ sýra", "Teplota", "Doba srážení", "Síla syřidla");
        System.out.println("-".repeat(55));
        for (CheeseType type : CheeseType.values()) {
            System.out.printf("%-15s %5.1f °C   %5d min       %.1fx%n",
                type.getCzechName(), type.getOptimalTempCelsius(),
                type.getCoagulationMinutes(), type.getRennetStrengthFactor());
        }
    }

    private void convertTemperature() {
        System.out.println("\n--- Převod teploty ---");
        System.out.println("1. °C → °F");
        System.out.println("2. °F → °C");
        int direction = readInt("Směr převodu: ", 1, 2);

        if (direction == 1) {
            double celsius = readDouble("Teplota v °C: ", -273.15, 1000.0);
            double fahrenheit = TemperatureConverter.celsiusToFahrenheit(celsius);
            System.out.printf("%.1f °C = %.1f °F%n", celsius, fahrenheit);
            if (TemperatureConverter.isSafeTemperature(celsius)) {
                System.out.println("✓ Teplota je v bezpečném rozmezí pro výrobu sýra.");
            } else {
                System.out.println("⚠ Teplota je mimo typické rozmezí pro výrobu sýra (20-55 °C).");
            }
        } else {
            double fahrenheit = readDouble("Teplota v °F: ", -459.67, 1832.0);
            double celsius = TemperatureConverter.fahrenheitToCelsius(fahrenheit);
            System.out.printf("%.1f °F = %.1f °C%n", fahrenheit, celsius);
            if (TemperatureConverter.isSafeTemperature(celsius)) {
                System.out.println("✓ Teplota je v bezpečném rozmezí pro výrobu sýra.");
            } else {
                System.out.println("⚠ Teplota je mimo typické rozmezí pro výrobu sýra (20-55 °C).");
            }
        }
    }

    private MilkType selectMilkType() {
        System.out.println("Vyberte typ mléka (Select milk type):");
        MilkType[] types = MilkType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.printf("  %d. %s (tuk ~%.1f%%)%n",
                i + 1, types[i].getCzechName(), types[i].getAvgFatPercent());
        }
        int choice = readInt("Typ mléka: ", 1, types.length);
        return types[choice - 1];
    }

    private CheeseType selectCheeseType() {
        System.out.println("Vyberte typ sýra (Select cheese type):");
        CheeseType[] types = CheeseType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.printf("  %d. %s%n", i + 1, types[i].getCzechName());
        }
        int choice = readInt("Typ sýra: ", 1, types.length);
        return types[choice - 1];
    }

    private int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Zadejte číslo od %d do %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Neplatný vstup. Zadejte celé číslo.");
            }
        }
    }

    private double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Zadejte číslo od %.1f do %.1f.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Neplatný vstup. Zadejte číslo.");
            }
        }
    }
}
