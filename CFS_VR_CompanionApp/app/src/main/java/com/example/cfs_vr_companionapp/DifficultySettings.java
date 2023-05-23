package com.example.cfs_vr_companionapp;

import org.json.JSONObject;

public class DifficultySettings {

    // This class contains various options regarding difficulty settings for the VR world

    private enum windSpeed {
        Low,
        Medium,
        High
    }

    private windSpeed currentWindSpeed;

    private enum grassHeight {
        Low,
        Medium,
        High
    }

    private grassHeight currentGrassHeight;

    private static final float[] WATER_CAPACITY = new float[] {20000, 30000, 40000};
    private float currentWaterCapacity;

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

    public void setQuickDifficulty(String level) {
        // Quickly set difficulty of all settings
        setWindSpeed(level);
        setCurrentGrassHeight(level);
        setCurrentMoistureLevel(level);
        System.out.println(setDifficulty());

    }

    public String setDifficulty() {
        // Convert the current difficulty settings to a string and send to VR client
        String difficulty = currentWindSpeed.toString() + "|" + currentGrassHeight.toString() + "|"
                + currentMoistureLevel.toString();
        return difficulty;
    }

}
