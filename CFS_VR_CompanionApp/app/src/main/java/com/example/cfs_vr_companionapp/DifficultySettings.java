package com.example.cfs_vr_companionapp;



public class DifficultySettings {

    // This class contains various options regarding difficulty settings for the VR world

    private enum windSpeed {
        Low,
        Medium,
        High
    }

    private enum treeDensity {
        Low,
        Medium,
        High
    }

    private treeDensity currentTreeDensity;

    private windSpeed currentWindSpeed;

    private enum grassHeight {
        Low,
        Medium,
        High
    }

    private grassHeight currentGrassHeight;

    private static final int[] WATER_CAPACITY = new int[] {20000, 30000, 40000};
    private int currentWaterCapacity;

    private enum moistureLevels {
        Low,
        Medium,
        High
    }

    private moistureLevels currentMoistureLevel;

    public enum terrainRoughness {
        Smooth,
        Rough
    }

    private terrainRoughness currentTerrainRoughness;


    public DifficultySettings() {
        // All difficulty settings will be set to low by default
        currentWindSpeed = windSpeed.Low;
        currentGrassHeight = grassHeight.Low;
        currentTerrainRoughness = terrainRoughness.Smooth;
        currentMoistureLevel = moistureLevels.Low;
        currentWaterCapacity = WATER_CAPACITY[0];
    }

    public void setWindSpeed(String speed) {
        switch (speed) {
            case "Low":
                currentWindSpeed = windSpeed.Low;
                break;
            case "Medium":
                currentWindSpeed = windSpeed.Medium;
                break;
            case "High":
                currentWindSpeed = windSpeed.High;
                break;
        }
    }

    public void setCurrentGrassHeight(String height) {
        switch (height) {
            case "Low":
                currentGrassHeight = grassHeight.Low;
                break;
            case "Medium":
                currentGrassHeight = grassHeight.Medium;
                break;
            case "High":
                currentGrassHeight = grassHeight.High;
                break;
        }
    }

    public void setCurrentTreeDensity(String density) {
        switch (density) {
            case "Low":
                currentTreeDensity = treeDensity.Low;
                break;
            case "Medium":
                currentTreeDensity = treeDensity.Medium;
                break;
            case "High":
                currentTreeDensity = treeDensity.High;
                break;
        }
    }

    public void setCurrentMoistureLevel(String level) {
        switch (level) {
            case "Low":
                currentMoistureLevel = moistureLevels.Low;
                break;
            case "Medium":
                currentMoistureLevel = moistureLevels.Medium;
                break;
            case "High":
                currentMoistureLevel = currentMoistureLevel.High;
                break;
        }
    }

    public void setWaterCapacity(String level) {
        switch (level) {
            case "Low":
                currentWaterCapacity = WATER_CAPACITY[0];
                break;
            case "Medium":
                currentWaterCapacity = WATER_CAPACITY[1];
                break;
            case "High":
                currentWaterCapacity = WATER_CAPACITY[2];
                break;
        }
    }

    public void setQuickDifficulty(String level) {
        // Quickly set difficulty of all settings
        setWindSpeed(level);
        setCurrentGrassHeight(level);
        setCurrentMoistureLevel(level);
        setCurrentTreeDensity(level);
        setWaterCapacity(level);
        System.out.println(setDifficulty());

    }

    public String setDifficulty() {
        // Convert the current difficulty settings to a string and send to VR client
        String difficulty = "DIFFICULTY_SETTINGS|" + currentWindSpeed.toString() + "|" + currentGrassHeight.toString() + "|"
                + currentMoistureLevel.toString() + "|" + currentTreeDensity.toString() + "|" + currentWaterCapacity;
        return difficulty;
    }

}
