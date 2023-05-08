package com.example.cfs_vr_companionapp;

public class DifficultySettings {

    // This class contains various options regarding

    public enum windSpeed {
        Low,
        Medium,
        High
    }

    public enum grassHeight {
        Low,
        Medium,
        High
    }

    public static final float[] WATER_CAPACITY = new float[] {20000, 30000, 40000};

    public enum moistureLevels {
        Low,
        Medium,
        High
    }

    public enum terrainRoughness {
        Smooth,
        Rough
    }

    public DifficultySettings() {

    }

}
