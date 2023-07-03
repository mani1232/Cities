package cc.worldmandia.DataBase;

import cc.worldmandia.DataBase.MongoDB.JsonDBAPI;
import cc.worldmandia.DataBase.MongoDB.MongoDBAPI;
import cc.worldmandia.DataBase.Objects.ObjectsDefault;
import cc.worldmandia.Utils;
import lombok.Getter;

@Getter
public class DataBase<T extends ObjectsDefault> {

    String dbUrlOrPath;
    DataBaseAPI<T> dataBaseAPI;

    public DataBase(String dbUrlOrPath, DataBaseType type, Class<T> tClass) {
        this.dbUrlOrPath = dbUrlOrPath;
        switch (type) {
            case LOCAL -> this.dataBaseAPI = new JsonDBAPI<>(this, tClass);
            case MONGO -> this.dataBaseAPI = new MongoDBAPI<>(this, tClass);
            default -> Utils.getLogger(this).error("DataBase type not found");
        }
    }
}
