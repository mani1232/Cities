package cc.worldmandia.controllers;

import java.util.ArrayList;
import java.util.List;

public class UsedCities {
    public static List<String> usedCities = new ArrayList<>();

    public void addUserInput(String userInput) {
        usedCities.add(userInput);
    }

    public void addComputerCity(String computerCity) {
        usedCities.add(computerCity);
    }

    public boolean contains(String city) {
        return usedCities.contains(city);
    }
}
