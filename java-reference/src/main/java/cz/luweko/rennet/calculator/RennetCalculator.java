package cz.luweko.rennet.calculator;

import cz.luweko.rennet.model.CheeseType;
import cz.luweko.rennet.model.MilkType;

/**
 * Calculates the amount of rennet (syřidlo) needed for cheese production.
 *
 * The standard dosage is approximately 0.2 ml of liquid rennet per liter of milk
 * for standard-strength rennet (1:10000). This is adjusted based on:
 * - Cheese type (some require stronger coagulation)
 * - Milk type (fat content affects coagulation)
 */
public class RennetCalculator {

    /** Base rennet dosage in ml per liter of milk for standard rennet (1:10000) */
    private static final double BASE_RENNET_ML_PER_LITER = 0.2;

    /**
     * Calculates the required amount of liquid rennet in milliliters.
     *
     * @param milkLiters volume of milk in liters (must be positive)
     * @param cheeseType the type of cheese being produced
     * @param milkType the type of milk being used
     * @return amount of rennet needed in milliliters
     * @throws IllegalArgumentException if milkLiters is not positive
     */
    public double calculateRennetMl(double milkLiters, CheeseType cheeseType, MilkType milkType) {
        if (milkLiters <= 0) {
            throw new IllegalArgumentException("Milk volume must be positive, got: " + milkLiters);
        }

        double baseDosage = milkLiters * BASE_RENNET_ML_PER_LITER;
        double cheeseAdjustment = cheeseType.getRennetStrengthFactor();
        double fatAdjustment = milkType.getAvgFatPercent() / 3.5; // normalized to cow milk

        return baseDosage * cheeseAdjustment * fatAdjustment;
    }

    /**
     * Estimates the expected cheese yield in kilograms.
     *
     * A general rule is ~10% yield from cow's milk for hard cheeses,
     * more for soft cheeses. Fat content and cheese type affect this.
     *
     * @param milkLiters volume of milk in liters
     * @param cheeseType the type of cheese being produced
     * @param milkType the type of milk being used
     * @return estimated cheese yield in kilograms
     */
    public double estimateYieldKg(double milkLiters, CheeseType cheeseType, MilkType milkType) {
        if (milkLiters <= 0) {
            throw new IllegalArgumentException("Milk volume must be positive, got: " + milkLiters);
        }

        // Base yield: approximately 1 kg cheese per 10 liters of cow's milk
        double baseYieldFraction = 0.10;

        // Soft cheeses yield more (higher moisture content)
        double typeMultiplier;
        switch (cheeseType) {
            case FRESH:
                typeMultiplier = 1.5;
                break;
            case MOZZARELLA:
                typeMultiplier = 1.3;
                break;
            case CAMEMBERT:
            case BRIE:
                typeMultiplier = 1.2;
                break;
            case FETA:
                typeMultiplier = 1.1;
                break;
            case GOUDA:
            case CHEDDAR:
                typeMultiplier = 1.0;
                break;
            case PARMESAN:
                typeMultiplier = 0.8;
                break;
            default:
                typeMultiplier = 1.0;
        }

        // Higher fat milk yields more cheese
        double fatMultiplier = milkType.getAvgFatPercent() / 3.5;

        return milkLiters * baseYieldFraction * typeMultiplier * fatMultiplier;
    }
}
