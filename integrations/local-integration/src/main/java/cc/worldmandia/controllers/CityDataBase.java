package cc.worldmandia.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static cc.worldmandia.CityApplication.cities;

public class CityDataBase {
    private static UsedCities usedCities = new UsedCities();
    public CityDataBase() {
        List<String> cities = new ArrayList<>();
        cities.add("kyiv");
        cities.add("Lviv");
        cities.add("Odesa");
        cities.add("vinitsa");
    }

    public static boolean contains(String city) {
        return cities.contains(city);
    }

    public static String getRandomCityFromDataBase(String userInput, UsedCities usedCities) {
        List<Object> availableCities = new ArrayList<>();

        for (String city : cities) {
            if (!CityDataBase.usedCities.contains(city) && city.toString().startsWith(userInput.substring(userInput.length() - 1))) {
                availableCities.add(city);
            }
        }

        if (availableCities.isEmpty()) {
            return null;
        }

        int randomIndex = new Random().nextInt(availableCities.size());
        return availableCities.get(randomIndex).toString();
    }
}
