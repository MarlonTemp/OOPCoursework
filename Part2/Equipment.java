class Equipment {
    private String name;
    private double confidenceBoost; //multiplier

    public Equipment(String name, double confidenceBoost) {
        this.name = name;
        this.confidenceBoost = confidenceBoost;
    }

    public String getName() {
        return name;
    }

    public double getConfidenceBoost() {
        return confidenceBoost;
    }
}
