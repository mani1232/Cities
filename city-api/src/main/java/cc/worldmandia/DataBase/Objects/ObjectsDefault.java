package cc.worldmandia.DataBase.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;

@Getter
@Setter
@NoArgsConstructor
public abstract class ObjectsDefault {
    @BsonId
    public String objectId;
}
