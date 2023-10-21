package Lab2.utils;

import ru.ifmo.se.pokemon.Type;

import java.util.*;

public class DefaultState extends Default{


    public DefaultState(String fileName, String className) {
        super(fileName, className);
    }


    public List<Type> getDefaultPokemonTypes() {
        assert objectData != null;
        return getObjectTypes(objectData.get(0), objectData.get(1));
    }


    public HashMap<String, List<Object>> getDefaultPokemonStats(List<String> objectDataKeys) {
        assert objectData != null;
        return getObjectByValues(objectData.get(0), objectData.get(1), objectDataKeys);
    }

    public List<String> getDefaultAttack() {
        assert objectData != null;
        return getObjectAttack(objectData.get(0), objectData.get(1));
    }

}
