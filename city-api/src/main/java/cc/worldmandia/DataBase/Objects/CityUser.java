package cc.worldmandia.DataBase.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class CityUser extends ObjectsDefault {

    {
        objectId = ObjectId.get();
        passedCities = new ArrayList<>();
    }

    String username;
    String password;
    ArrayList<String> passedCities;
}
