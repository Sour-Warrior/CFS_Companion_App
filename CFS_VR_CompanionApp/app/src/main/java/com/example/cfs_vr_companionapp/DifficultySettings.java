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

    public JSONObject setDifficulty() {
        // Parse the current difficulty settings to JSON format to be used within the VR system
        
        return null;
    }

}
