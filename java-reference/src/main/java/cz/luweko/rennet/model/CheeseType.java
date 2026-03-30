package cz.luweko.rennet.model;

/**
 * Common cheese types with their production parameters.
 */
public enum CheeseType {
    FRESH("Čerstvý sýr", 32, 30, 0.5),
    CAMEMBERT("Camembert", 32, 60, 1.0),
    GOUDA("Gouda", 32, 45, 0.8),
    CHEDDAR("Čedar", 31, 45, 1.0),
    PARMESAN("Parmezán", 33, 60, 1.2),
    MOZZARELLA("Mozzarella", 35, 30, 0.6),
    FETA("Feta", 32, 60, 0.7),
    BRIE("Brie", 32, 60, 0.9);

    private final String czechName;
    private final double optimalTempCelsius;
    private final int coagulationMinutes;
    private final double rennetStrengthFactor;

    CheeseType(String czechName, double optimalTempCelsius,
               int coagulationMinutes, double rennetStrengthFactor) {
        this.czechName = czechName;
        this.optimalTempCelsius = optimalTempCelsius;
        this.coagulationMinutes = coagulationMinutes;
        this.rennetStrengthFactor = rennetStrengthFactor;
    }

    public String getCzechName() {
        return czechName;
    }

    public double getOptimalTempCelsius() {
        return optimalTempCelsius;
    }

    public int getCoagulationMinutes() {
        return coagulationMinutes;
    }

    public double getRennetStrengthFactor() {
        return rennetStrengthFactor;
    }

    @Override
    public String toString() {
        return czechName + " (" + name().toLowerCase() + ")";
    }
}
