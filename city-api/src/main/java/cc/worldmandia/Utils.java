package cc.worldmandia;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.io.ConfigParser;
import com.electronwill.nightconfig.json.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Utils {

    static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public static void scheduleWithFixedDelay(Runnable task, long initialDelay, long delay, TimeUnit unit) {
        executor.scheduleWithFixedDelay(task, initialDelay, delay, unit);
    }

    public static Logger getLogger(Object loggerClass) {
        return LoggerFactory.getLogger(loggerClass.getClass());
    }

    public static void main(String[] args) {
       // exampleRequest();
        System.out.println(CheckCityExistanceWithAPI("Coeur d'Alene"));
    }

    public static void exampleRequest() {
        String name = "Coeur d'Alene45456".replace(" ", "%20");//.replace("'", "%27");
        try {
            ConfigParser<Config> jsonParser = JsonFormat.emptyTolerantInstance().createParser();
            String json = HttpClient.newHttpClient().sendAsync(HttpRequest.newBuilder().uri(URI.create("https://api.api-ninjas.com/v1/city?name=" + name)).GET().setHeader("accept", "application/json").header("X-Api-Key", "2Pcp9UV3NqYcEaHjX53hjw==GRtzwQ41Dvdbw9Xp").build(), HttpResponse.BodyHandlers.ofString()).get().body();
            System.out.println(json);
            Config config = jsonParser.parse(json.replace("[", "").replace("]", ""));
            System.out.println(config.getOrElse("country", "empty"));
            System.out.println(config);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
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
            return !config.getOrElse("country", "empty").equals("empty");
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
