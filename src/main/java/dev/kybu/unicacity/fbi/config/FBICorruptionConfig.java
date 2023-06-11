package dev.kybu.unicacity.fbi.config;

import java.util.HashMap;
import java.util.Map;

public class FBICorruptionConfig {

    private double pricePerWantedPoint = 150D;
    private Map<String, FBIDrugConfig> drugPricesMap = new HashMap<String, FBIDrugConfig>(){{
        put("Meth", new FBIDrugConfig());
    }};
    private double pricePerHoursOnMeth = 3000D;

    public double getPricePerWantedPoint() {
        return pricePerWantedPoint;
    }

    public Map<String, FBIDrugConfig> getDrugPricesMap() {
        return drugPricesMap;
    }

    public double getPricePerHoursOnMeth() {
        return pricePerHoursOnMeth;
    }
}
