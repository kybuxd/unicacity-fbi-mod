package dev.kybu.unicacity.fbi.config;

public class FBIDrugConfig {

    private double[] purityPrices = {10, 20, 30};

    public double[] getPurityPrices() {
        return purityPrices;
    }

    public void setPurityPrices(double[] purityPrices) {
        this.purityPrices = purityPrices;
    }
}
