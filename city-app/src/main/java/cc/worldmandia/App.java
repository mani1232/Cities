package cc.worldmandia;

import cc.worldmandia.DataBase.DataBase;
import cc.worldmandia.DataBase.Objects.CityUser;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class App {

    public static void main(String[] args) {
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

        integration.get().start(args);
    }

    private static void dbTest() {
        DataBase<CityUser> dataBase = new DataBase<>("mongodb://developer:KJHL6DHBRapuZxx9kq9t9dkZDfjBWfVB@192.168.1.111:27018", "mongo", CityUser.class);
        CityUser cityUser = new CityUser();
        cityUser.setUsername("Test");
        dataBase.getDataBaseAPI().createObject(cityUser);
        //cityUser = dataBase.getDataBaseAPI().getObject("username", "Not Set");
        cityUser.setUsername("OtherUserName");
        if (dataBase.getDataBaseAPI().updateObject("", "", new CityUser())) {
            System.out.println("Done!");
        }
    }

}
