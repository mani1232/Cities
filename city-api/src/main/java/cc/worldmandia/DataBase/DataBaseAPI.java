package cc.worldmandia.DataBase;

import cc.worldmandia.DataBase.Objects.ObjectsDefault;

public interface DataBaseAPI<T extends ObjectsDefault> {

    T getObject(String fieldName, Object id);
    boolean createObject(T newObject);
    boolean updateObject(T updateData);
    boolean contains(String fieldName, Object id);

}
