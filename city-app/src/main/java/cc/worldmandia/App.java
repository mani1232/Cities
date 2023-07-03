package cc.worldmandia;

import cc.worldmandia.DataBase.DataBase;
import cc.worldmandia.DataBase.DataBaseType;
import cc.worldmandia.DataBase.Objects.CityUser;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class App {
    static App app;
    DataBase<CityUser> dataBase;

    // Test url mongodb: mongodb://developer:KJHL6DHBRapuZxx9kq9t9dkZDfjBWfVB@82.66.203.77:27018
    String dbUrlOrPath = "database.json";

    public static void main(String[] args) {
        app = new App();
        app.run(args);
    }

    public void run(String[] args) {
        dataBase = dbUrlOrPath.startsWith("mongodb://") ? new DataBase<>(dbUrlOrPath, DataBaseType.MONGO, CityUser.class) : new DataBase<>(dbUrlOrPath, DataBaseType.LOCAL, CityUser.class);
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
