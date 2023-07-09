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
        objectId = ObjectId.get().toString();
        availableCities = new ArrayList<>();
    }

    public String username;
    public String password;
    public ArrayList<String> availableCities;

    @Override
    public String toString() {
        return "CityUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passedCities=" + availableCities +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
