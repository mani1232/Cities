package cc.worldmandia;

import cc.worldmandia.DataBase.DataBase;
import cc.worldmandia.DataBase.DataBaseType;
import cc.worldmandia.DataBase.Objects.CityUser;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class App {
    static App app;
    static DataBase<CityUser> dataBase;

    // Test url mongodb: mongodb://mani123:7b84HMGhvR9jFGka9MbU6UGhjKWH8d4H@82.66.203.77:27017
    String dbUrlOrPath = "mongodb://mani123:7b84HMGhvR9jFGka9MbU6UGhjKWH8d4H@82.66.203.77:27017"; // Or database.json

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
                    default -> integration.set(new CityApplication());
                }
            });
        } else {
            integration.set(new CityApplication());
        }

        integration.get().start(args, dataBase);
    }

}
