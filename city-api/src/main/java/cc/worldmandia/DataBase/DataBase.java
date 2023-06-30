package cc.worldmandia.DataBase;

import cc.worldmandia.DataBase.MongoDB.JsonDBAPI;
import cc.worldmandia.DataBase.MongoDB.MongoDBAPI;
import cc.worldmandia.DataBase.Objects.ObjectsDefault;
import lombok.Getter;

@Getter
public class DataBase<T extends ObjectsDefault> {

    String dbUrlOrPath;
    DataBaseAPI<T> dataBaseAPI;

    public DataBase(String dbUrlOrPath, String type, Class<T> tClass) {
        this.dbUrlOrPath = dbUrlOrPath;
        if (type.equalsIgnoreCase("mongo")) this.dataBaseAPI = new MongoDBAPI<>(this, tClass);
        else this.dataBaseAPI = new JsonDBAPI<>(this, tClass);
    }
}
