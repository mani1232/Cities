package cc.worldmandia.DataBase.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@Getter
@Setter
@NoArgsConstructor
public abstract class ObjectsDefault {
    @BsonId
    ObjectId objectId;
    String username;

}
