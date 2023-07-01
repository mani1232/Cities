package cc.worldmandia.DataBase.MongoDB;

import cc.worldmandia.DataBase.DataBase;
import cc.worldmandia.DataBase.DataBaseAPI;
import cc.worldmandia.DataBase.Objects.ObjectsDefault;
import cc.worldmandia.Utils;
import com.electronwill.nightconfig.core.conversion.ObjectConverter;
import com.electronwill.nightconfig.core.file.FileConfig;
import com.electronwill.nightconfig.core.file.FileNotFoundAction;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class JsonDBAPI<T extends ObjectsDefault> implements DataBaseAPI<T> {
    private final ObjectConverter objectConverter;
    private final FileConfig fileConfig;

    Config<T> config;

    public JsonDBAPI(DataBase<T> tDataBase) {
        objectConverter = new ObjectConverter();
        config = new Config<>();
        FileConfig fileConfigTemp;
        try {
            fileConfigTemp = FileConfig.builder(tDataBase.getDbUrlOrPath()).autosave().onFileNotFound(FileNotFoundAction.THROW_ERROR).build();
            objectConverter.toObject(fileConfigTemp, config);
        } catch (Exception e) {
            fileConfigTemp = FileConfig.builder(tDataBase.getDbUrlOrPath()).autosave().onFileNotFound(FileNotFoundAction.CREATE_EMPTY).build();
        }
        fileConfig = fileConfigTemp;
        fileConfig.load();
        Utils.scheduleWithFixedDelay(() -> objectConverter.toConfig(config, fileConfig), 1, 300, TimeUnit.SECONDS); // Save config every 5 min
    }

    @Override
    public T getObject(String fieldId, Object fieldValue) {
        return config.objects.stream().filter(t -> {
            try {
                return t.getClass().getField(fieldId).get(fieldValue).equals(fieldValue);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }).findFirst().orElse(null);
    }

    @Override
    public boolean createObject(T newObject) {
        config.objects.add(newObject);
        return true;
    }

    @Override
    public boolean replaceObject(String fieldId, Object fieldValue, T updateData) {
        Optional<T> objectToUpdate = config.objects.stream().filter(t -> {
            try {
                return t.getClass().getDeclaredField(fieldId).get(t).equals(fieldValue);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }).findFirst();

        if (objectToUpdate.isPresent()) {
            int index = config.objects.indexOf(objectToUpdate.get());
            config.objects.set(index, updateData);
            return true;
        }

        return false;
    }

    @Override
    public boolean contains(String fieldId, Object fieldValue) {
        for (T object : config.objects) {
            try {
                Object value = object.getClass().getDeclaredField(fieldId).get(object);
                if (value.equals(fieldValue)) {
                    return true;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }


    private static class Config<T> {
        ArrayList<T> objects = new ArrayList<>();
    }
}
