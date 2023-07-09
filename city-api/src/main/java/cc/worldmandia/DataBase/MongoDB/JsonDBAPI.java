package cc.worldmandia.DataBase.MongoDB;

import cc.worldmandia.DataBase.DataBase;
import cc.worldmandia.DataBase.DataBaseAPI;
import cc.worldmandia.DataBase.Objects.LocalDB;
import cc.worldmandia.DataBase.Objects.ObjectsDefault;
import cc.worldmandia.Utils;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.conversion.ObjectConverter;
import com.electronwill.nightconfig.core.file.FileConfig;
import com.electronwill.nightconfig.core.file.FileNotFoundAction;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class JsonDBAPI<T extends ObjectsDefault> implements DataBaseAPI<T> {

    private final ObjectConverter objectConverter;
    private final FileConfig fileConfig;
    LocalDB<T> localDB = new LocalDB<>();

    public JsonDBAPI(DataBase<T> tDataBase, Class<T> tClass) {
        objectConverter = new ObjectConverter();
        fileConfig = FileConfig.builder(tDataBase.getDbUrlOrPath()).onFileNotFound(FileNotFoundAction.CREATE_EMPTY).autosave().build();
        fileConfig.load();
        List<Config> cfgSections = fileConfig.getOrElse("objects", new ArrayList<>());
        if (!cfgSections.isEmpty()) {
            localDB.getObjects().addAll(convertFromConfig(cfgSections, tClass));
        }
        Utils.scheduleWithFixedDelay(() -> {
            Utils.getLogger(this).info("Saved " + localDB.getObjects().size());
            objectConverter.toConfig(localDB, fileConfig);
            fileConfig.save();
        }, 3, 15, TimeUnit.SECONDS); // Save config every 10 sec
    }

    @Override
    public T getObject(String fieldId, Object fieldValue) {
        return localDB.getObjects().stream().filter(t -> {
            try {
                Object o = t.getClass().getDeclaredField(fieldId).get(t);
                if (o != null) {
                    return o.equals(fieldValue);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
            return false;
        }).findFirst().orElse(null);
    }

    @Override
    public boolean createObject(T newObject) {
        localDB.getObjects().add(newObject);
        return true;
    }

    @Override
    public boolean replaceObject(String fieldId, Object fieldValue, T updateData) {
        Optional<T> objectToUpdate = localDB.getObjects().stream().filter(t -> {
            try {
                return t.getClass().getDeclaredField(fieldId).get(t).equals(fieldValue);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }).findFirst();

        if (objectToUpdate.isPresent()) {
            int index = localDB.getObjects().indexOf(objectToUpdate.get());
            localDB.getObjects().set(index, updateData);
            return true;
        }

        return false;
    }

    @Override
    public boolean contains(String fieldId, Object fieldValue) {
        for (T t : localDB.getObjects()) {
            try {
                Object o = t.getClass().getDeclaredField(fieldId).get(t);
                if (o != null && o.equals(fieldValue)) {
                    return true;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private List<T> convertFromConfig(List<Config> cfgSections, Class<T> tClass) {
        return cfgSections.stream().map(section -> {
            try {
                T aClass = tClass.getDeclaredConstructor().newInstance();
                Arrays.stream(tClass.getDeclaredFields()).forEach(field -> {
                    try {
                        Object value = section.get(field.getName());
                        Field targetField = aClass.getClass().getDeclaredField(field.getName());
                        targetField.setAccessible(true);
                        targetField.set(aClass, value);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
                return aClass;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

}
