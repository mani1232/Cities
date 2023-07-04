package cc.worldmandia;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.io.ConfigParser;
import com.electronwill.nightconfig.json.JsonFormat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class CityValidationMethods {
    static List<String> usedCities = List.of("London", "Tokyo", "New York");

    public static void main(String[] args) {
        All3Validations();
    }

    public static String ScanPlayersCityInput() {
        Scanner scanner = new Scanner(System.in);
        String cityName = scanner.nextLine().trim();
        return cityName;
    }

    public static void All3Validations() {
        while (true) {
            String playerCity = ScanPlayersCityInput();
            if (!CheckCityExistanceWithAPI(playerCity)) {
                System.out.println("This city doesn't exist");
                continue;
            }

            if (IsCityInDatabase(playerCity)) {
                System.out.println("This city is used twice");
                continue;
            }

            if (!CheckCityNameFirstChar(playerCity)) {
                System.out.println("Wrong first char");
                continue;
            }

            System.out.println("Validated");
            break;
        }
    }

    public static boolean CheckCityExistanceWithAPI(String cityName) {
        String name = cityName.replace(" ", "%20");
        try {
            ConfigParser<Config> jsonParser = JsonFormat.emptyTolerantInstance().createParser();
            String json = HttpClient.newHttpClient()
                    .sendAsync(HttpRequest.newBuilder()
                            .uri(URI.create("https://api.api-ninjas.com/v1/city?name=" + name))
                            .GET()
                            .setHeader("accept", "application/json")
                            .header("X-Api-Key", "2Pcp9UV3NqYcEaHjX53hjw==GRtzwQ41Dvdbw9Xp")
                            .build(), HttpResponse.BodyHandlers.ofString())
                    .get()
                    .body();
            Config config = jsonParser.parse(json.replace("[", "").replace("]", ""));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    public static boolean IsCityInDatabase(String cityName) {

        if (usedCities.contains(cityName)) {
            return true;
        } else {
            usedCities.add(cityName);
            return false;
        }
    }

    public static boolean CheckCityNameFirstChar(String cityName) {
        String lastCity = "Kyiv";
        if (lastCity.isEmpty() || lastCity.charAt(lastCity.length() - 1) == cityName.charAt(0)) {
            lastCity = cityName;
            usedCities.add(cityName);
            return true;
        } else {
            return false;
        }
    }
}

