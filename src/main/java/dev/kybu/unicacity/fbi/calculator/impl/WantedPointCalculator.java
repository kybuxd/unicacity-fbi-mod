package dev.kybu.unicacity.fbi.calculator.impl;

import dev.kybu.unicacity.fbi.calculator.IFBICalculator;
import dev.kybu.unicacity.fbi.config.FBIDrugConfig;
import dev.kybu.unicacity.fbi.config.FBIModConfig;

import java.util.HashMap;
import java.util.Map;

public class WantedPointCalculator implements IFBICalculator {

    private final FBIModConfig modConfig;

    /*=-------------------------------------------------------=*/

    public WantedPointCalculator(final FBIModConfig modConfig) {
        this.modConfig = modConfig;
    }

    /*=-------------------------------------------------------=*/

    @Override
    public Map<String, String> calculateValues(final double value) {
        final Map<String, String> map = new HashMap<>();

        final double fullPrice = value * this.modConfig.getCorruption().getPricePerWantedPoint();
        map.put("Price", fullPrice + "");
        for (final Map.Entry<String, FBIDrugConfig> entry : this.modConfig.getCorruption().getDrugPricesMap().entrySet()) {
            final StringBuilder stringBuilder = new StringBuilder("[");
            for(int i = 0; i < entry.getValue().getPurityPrices().length; i++) {
                stringBuilder.append(i + ": " + (int) (fullPrice / entry.getValue().getPurityPrices()[i]) + ", ");
            }
            stringBuilder.setLength(stringBuilder.length() - 2);
            stringBuilder.append("]");
            map.put(entry.getKey(), stringBuilder.toString());
        }

        return map;
    }

}
