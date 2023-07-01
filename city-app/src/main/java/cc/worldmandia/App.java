package cc.worldmandia;

import cc.worldmandia.DataBase.DataBase;
import cc.worldmandia.DataBase.Objects.CityUser;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class App {
    // Test url mongodb: mongodb://developer:KJHL6DHBRapuZxx9kq9t9dkZDfjBWfVB@82.66.203.77:27018
    static App app;
    DataBase<CityUser> dataBase;

    public static void main(String[] args) {
        app = new App();
        app.run(args);
    }

    public void run(String[] args) {
        dataBase = new DataBase<>("database.json", "local", CityUser.class);
        AtomicReference<Integration> integration = new AtomicReference<>();
        if (args.length > 0) {
            Arrays.stream(args).forEach(s -> {
                switch (s.toLowerCase()) {
                    case "discord" -> {
                    }
                    case "telegram" -> {
                    }
                    default -> integration.set(new CityApplication());
                }
            });
        } else {
            integration.set(new CityApplication());
        }

        integration.get().start(args);
    }

}
