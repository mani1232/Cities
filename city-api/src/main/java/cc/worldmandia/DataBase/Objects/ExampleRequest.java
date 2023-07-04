package cc.worldmandia.DataBase.Objects;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.io.ConfigParser;
import com.electronwill.nightconfig.json.JsonFormat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

public class ExampleRequest {

    public static void main(String[] args) {
        exampleRequest();
    }
    public static void exampleRequest() {
        String name = "Sedro-Woolley".replace(" ", "%20");
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
}
