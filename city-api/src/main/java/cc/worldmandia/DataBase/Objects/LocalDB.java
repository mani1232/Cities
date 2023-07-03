package cc.worldmandia.DataBase.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class LocalDB<T extends ObjectsDefault> {

    ArrayList<T> objects = new ArrayList<>();

}
