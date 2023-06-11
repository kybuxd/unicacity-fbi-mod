package dev.kybu.unicacity.fbi.data;

public enum ReportType {

    GEISELNAHME("eine Geiselnahme"),
    FLUGZEUGENTFUEHRUNG("eine Flugzeugentführung"),
    LABOR("ein Einbruch in das Labor"),
    FBI("ein Einbruch in das FBI-HQ"),
    BANK("ein Banküberfall bei der Staatsbank"),
    UBOOTENTFUEHRUNG("eine U-Boot Entführung (Scheiß Terrors junge)"),
    WPJAGD("eine Jagd auf High-Wpler (5+ online)"),
    GEBIETSEINNAHME("eine Gebietseinnahme");

    /*=-------------------------------------------------------=*/

    private String displayName;

    /*=-------------------------------------------------------=*/

    ReportType(final String displayName) {
        this.displayName = displayName;
    }

    /*=-------------------------------------------------------=*/

    public String getDisplayName() {
        return displayName;
    }
}
