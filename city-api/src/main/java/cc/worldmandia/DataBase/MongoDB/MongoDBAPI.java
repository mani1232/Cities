package cc.worldmandia.DataBase.MongoDB;

import cc.worldmandia.DataBase.DataBase;
import cc.worldmandia.DataBase.DataBaseAPI;
import cc.worldmandia.DataBase.Objects.ObjectsDefault;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBAPI<T extends ObjectsDefault> implements DataBaseAPI<T> {

    public final MongoClient mongoClient;
    private final MongoCollection<T> collection;

    public MongoDBAPI(DataBase<T> dataBase, Class<T> tClass) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        this.mongoClient = MongoClients.create(dataBase.getDbUrlOrPath());
        this.collection = mongoClient.getDatabase("Users").withCodecRegistry(pojoCodecRegistry).getCollection("CityUser", tClass);
    }

    @Override
    public T getObject(String fieldId, Object fieldValue) {
        if (fieldValue instanceof Long idLong) {
            return collection.find(Filters.eq(fieldId, idLong)).first();
        } else if (fieldValue instanceof String idString) {
            return collection.find(Filters.eq(fieldId, idString)).first();
        }
        return null;
    }

    @Override
    public boolean createObject(T newObject) {
        InsertOneResult result = collection.insertOne(newObject);
        return result.wasAcknowledged();
    }

    @Override
    public boolean replaceObject(String fieldId, Object fieldValue, T updateData) {
        UpdateResult updateResult = collection.replaceOne(new Document(fieldId, fieldValue), updateData);
        return updateResult.wasAcknowledged();
    }

    @Override
    public boolean contains(String fieldId, Object fieldValue) {
        return collection.countDocuments(Filters.eq(fieldId, fieldValue)) > 0;
    }

    @Override
    public void saveAll(boolean shutdown) {
        if (shutdown) {
            mongoClient.close();
        }
    }
}
