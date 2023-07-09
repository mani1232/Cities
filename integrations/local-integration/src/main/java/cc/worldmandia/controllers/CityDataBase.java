package cc.worldmandia.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CityDataBase {
    public static List<String> cities = new ArrayList<>();

    static {
        cities.add("Kyiv");
        cities.add("Lviv");
        cities.add("Odesa");
        cities.add("Vinitsa");
        cities.add("London");
        cities.add("Paris");
        cities.add("Berlin");
        cities.add("Madrid");
        cities.add("Rome");
        cities.add("Tokyo");
        cities.add("New York");
        cities.add("Sydney");
        cities.add("Moscow");
        cities.add("Toronto");
        cities.add("Cairo");
        cities.add("Amsterdam");
        cities.add("Dubai");
        cities.add("Beijing");
        cities.add("Sao Paulo");
        cities.add("Mumbai");
        cities.add("Bangkok");
    }

    public static boolean contains(String city) {
        return cities.contains(city);
    }

    public static String getRandomCityFromDataBase(String userInput, List<String> usedCities) {
        List<String> availableCities = new ArrayList<>();

        for (String city : cities) {
            if (!usedCities.contains(city) && city.substring(0, 1).equalsIgnoreCase(userInput.substring(userInput.length() - 1))) {
                availableCities.add(city);
            }
        }

        if (availableCities.isEmpty()) {
            return null;
        }

        int randomIndex = new Random().nextInt(availableCities.size());
        return availableCities.get(randomIndex);
    }
}
