package cc.worldmandia;

import cc.worldmandia.DataBase.DataBase;
import cc.worldmandia.DataBase.Objects.CityUser;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class App {

    static App app;
    DataBase<CityUser> dataBase;

    public static void main(String[] args) {
        app = new App();
        //app.run(args);
        //app.mongodbTest();
        app.localdbTest();
    }

    public void run(String[] args) {
        AtomicReference<Integration> integration = new AtomicReference<>();
        if (args.length > 0) {
            Arrays.stream(args).forEach(s -> {
                switch (s.toLowerCase()) {
                    case "discord" -> {
                    }
                    default -> integration.set(new CityApplication());
                }
            });
        } else {
            integration.set(new CityApplication());
        }

        integration.get().start(args);
    }

    private void mongodbTest() {
        dataBase = new DataBase<>("mongodb://developer:KJHL6DHBRapuZxx9kq9t9dkZDfjBWfVB@82.66.203.77:27018", "mongo", CityUser.class);
        CityUser cityUser = new CityUser();
        cityUser.setUsername("Test");
        dataBase.getDataBaseAPI().createObject(cityUser);
        cityUser.setUsername("OtherUserName");
        if (dataBase.getDataBaseAPI().replaceObject("username", "Test", cityUser)) {
            Utils.getLogger(app.getClass()).info("Done!");
        }
    }

    private void localdbTest() {
        dataBase = new DataBase<>("testdb.json", "local", CityUser.class);
        CityUser cityUser = new CityUser();
        cityUser.setUsername("Test");
        dataBase.getDataBaseAPI().createObject(cityUser);
        cityUser.setUsername("OtherUserName");
        if (dataBase.getDataBaseAPI().replaceObject("username", "Test", cityUser)) {
            Utils.getLogger(app.getClass()).info("Done!");
        }
    }

}
