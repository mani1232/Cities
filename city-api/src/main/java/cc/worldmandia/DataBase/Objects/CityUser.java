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
        passedCities = new ArrayList<>();
    }

    public String username;
    public String password;
    public ArrayList<String> passedCities;

    @Override
    public String toString() {
        return "CityUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passedCities=" + passedCities +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
