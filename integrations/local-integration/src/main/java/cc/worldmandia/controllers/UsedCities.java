package cc.worldmandia.controllers;

import java.util.ArrayList;

public class UsedCities {
    private ArrayList<String> cities = new ArrayList<>();

    public void addUserInput(String userInput) {
        cities.add(userInput);
    }

    public void addComputerCity(String computerCity) {
        cities.add(computerCity);
    }

    public boolean contains(String city) {
        return cities.contains(city);
    }
}
