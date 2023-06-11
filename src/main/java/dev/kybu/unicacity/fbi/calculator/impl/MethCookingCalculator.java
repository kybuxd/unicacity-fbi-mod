package dev.kybu.unicacity.fbi.calculator.impl;

import dev.kybu.unicacity.fbi.calculator.IFBICalculator;
import dev.kybu.unicacity.fbi.config.FBIModConfig;

import java.util.HashMap;
import java.util.Map;

public class MethCookingCalculator implements IFBICalculator {

    private final FBIModConfig fbiModConfig;

    /*=-------------------------------------------------------=*/

    public MethCookingCalculator(final FBIModConfig fbiModConfig) {
        this.fbiModConfig = fbiModConfig;
    }

    /*=-------------------------------------------------------=*/

    @Override
    public Map<String, String> calculateValues(double value) {
        final Map<String, String> map = new HashMap<>();
        final long amountOfHoursButEqual = (long) (value % 2 == 1 ? value + 1 : value);
        map.put("Anzahl der berechneten Stunden", amountOfHoursButEqual + " Stunden");
        map.put("Ausgerechneter Wert", String.valueOf(amountOfHoursButEqual * this.fbiModConfig.getCorruption().getPricePerHoursOnMeth()));
        return map;
    }

}

