package dev.kybu.unicacity.fbi.config;

public class FBIModConfig {

    private FBICorruptionConfig corruption = new FBICorruptionConfig();
    private FBIEquipConfig equip = new FBIEquipConfig();

    public FBICorruptionConfig getCorruption() {
        return corruption;
    }
}
