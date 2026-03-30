package cz.luweko.rennet.model;

/**
 * Types of milk used in cheese making.
 */
public enum MilkType {
    COW("Kravské", 3.5),
    GOAT("Kozí", 4.0),
    SHEEP("Ovčí", 6.5),
    BUFFALO("Buvolí", 7.5);

    private final String czechName;
    private final double avgFatPercent;

    MilkType(String czechName, double avgFatPercent) {
        this.czechName = czechName;
        this.avgFatPercent = avgFatPercent;
    }

    public String getCzechName() {
        return czechName;
    }

    public double getAvgFatPercent() {
        return avgFatPercent;
    }

    @Override
    public String toString() {
        return czechName + " (" + name().toLowerCase() + ")";
    }
}
