package cc.worldmandia.DataBase;

import cc.worldmandia.DataBase.Objects.ObjectsDefault;

public interface DataBaseAPI<T extends ObjectsDefault> {

    T getObject(String fieldId, Object fieldValue);
    boolean createObject(T newObject);
    boolean replaceObject(String fieldId, Object fieldValue, T updateData);
    boolean contains(String fieldId, Object fieldValue);
    void saveAll(boolean shutdown);

}
