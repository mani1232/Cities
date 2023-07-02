package cc.worldmandia.DataBase.MongoDB;

import cc.worldmandia.DataBase.DataBase;
import cc.worldmandia.DataBase.DataBaseAPI;
import cc.worldmandia.DataBase.Objects.ObjectsDefault;
import cc.worldmandia.Utils;
import com.electronwill.nightconfig.core.conversion.ObjectConverter;
import com.electronwill.nightconfig.core.file.FileConfig;
import com.electronwill.nightconfig.core.file.FileNotFoundAction;
import com.electronwill.nightconfig.core.io.ParsingMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class JsonDBAPI<T extends ObjectsDefault> implements DataBaseAPI<T> {
    private final ObjectConverter objectConverter;
    private final FileConfig fileConfig;
    Config config = new Config();

    public JsonDBAPI(DataBase<T> tDataBase) {
        objectConverter = new ObjectConverter();
        fileConfig = FileConfig.builder(tDataBase.getDbUrlOrPath()).onFileNotFound(FileNotFoundAction.CREATE_EMPTY).parsingMode(ParsingMode.ADD).autosave().build();
        fileConfig.load();
        Utils.scheduleWithFixedDelay(() -> objectConverter.toConfig(config, fileConfig), 150, 300, TimeUnit.SECONDS); // Save config every 5 min
        objectConverter.toObject(fileConfig, config);
    }

    @Override
    public T getObject(String fieldId, Object fieldValue) {
        return config.objects.stream().filter(t -> {
            try {
                return t.getClass().getDeclaredField(fieldId).get(t).equals(fieldValue);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }).findFirst().orElse(null);
    }

    @Override
    public boolean createObject(T newObject) {
        System.out.println(config.objects.toString());
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
        for (T t : config.objects) {
            try {
                if (t.getClass().getDeclaredField(fieldId).get(t).equals(fieldValue)) {
                    return true;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }


    @NoArgsConstructor
    @Getter
    @Setter
    public class Config {
        ArrayList<T> objects = new ArrayList<>();
    }
}
