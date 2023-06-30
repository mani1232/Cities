package cc.worldmandia.DataBase.MongoDB;

import cc.worldmandia.DataBase.DataBase;
import cc.worldmandia.DataBase.DataBaseAPI;
import cc.worldmandia.DataBase.Objects.ObjectsDefault;

public class JsonDBAPI<T extends ObjectsDefault> implements DataBaseAPI<T> {
    public JsonDBAPI(DataBase<T> tDataBase, Class<T> tClass) {

    }

    @Override
    public T getObject(String fieldName, Object id) {
        // TODO
        return null;
    }

    @Override
    public boolean createObject(T newObject) {
        // TODO
        return false;
    }

    @Override
    public boolean updateObject(T updateData) {
        // TODO
        return false;
    }

    @Override
    public boolean contains(String fieldName, Object id) {
        // TODO
        return false;
    }
}
