package cz.luweko.rennet.model;

/**
 * Represents a cheese-making recipe with all calculated parameters.
 */
public class Recipe {

    private final String name;
    private final CheeseType cheeseType;
    private final MilkType milkType;
    private final double milkLiters;
    private final double rennetMl;
    private final double temperatureCelsius;
    private final int coagulationMinutes;
    private final double expectedYieldKg;

    public Recipe(String name, CheeseType cheeseType, MilkType milkType,
                  double milkLiters, double rennetMl, double temperatureCelsius,
                  int coagulationMinutes, double expectedYieldKg) {
        this.name = name;
        this.cheeseType = cheeseType;
        this.milkType = milkType;
        this.milkLiters = milkLiters;
        this.rennetMl = rennetMl;
        this.temperatureCelsius = temperatureCelsius;
        this.coagulationMinutes = coagulationMinutes;
        this.expectedYieldKg = expectedYieldKg;
    }

    public String getName() {
        return name;
    }

    public CheeseType getCheeseType() {
        return cheeseType;
    }

    public MilkType getMilkType() {
        return milkType;
    }

    public double getMilkLiters() {
        return milkLiters;
    }

    public double getRennetMl() {
        return rennetMl;
    }

    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public int getCoagulationMinutes() {
        return coagulationMinutes;
    }

    public double getExpectedYieldKg() {
        return expectedYieldKg;
    }

    @Override
    public String toString() {
        return String.format(
            "=== %s ===%n" +
            "Typ sýra: %s%n" +
            "Typ mléka: %s%n" +
            "Množství mléka: %.1f l%n" +
            "Syřidlo: %.2f ml%n" +
            "Teplota: %.1f °C%n" +
            "Doba srážení: %d min%n" +
            "Očekávaný výnos: %.2f kg",
            name, cheeseType.getCzechName(), milkType.getCzechName(),
            milkLiters, rennetMl, temperatureCelsius,
            coagulationMinutes, expectedYieldKg
        );
    }
}
