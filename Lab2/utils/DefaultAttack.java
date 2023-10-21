package Lab2.utils;

import Lab2.Main;
import ru.ifmo.se.pokemon.Type;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DefaultAttack extends Default{
    public static final String pokemonsFileName = "Lab2/data/attacs.csv";

    public DefaultAttack(String fileName, String className) {
        super(fileName, className);
    }

    public List<Type> getDefaultAttackTypes() {
        assert objectData != null;

        return getObjectTypes(objectData.get(0), objectData.get(1));
    }

    public List<Object> getDefaultAttackExtends() {
        assert objectData != null;

        return getObjectByValue(objectData.get(0), objectData.get(1),"extends");
    }

    public HashMap<String, List<Object>> getDefaultAttackStats(List<String> objectDataKeys) {
        assert objectData != null;
        return getObjectByValues(objectData.get(0), objectData.get(1), objectDataKeys);
    }

}
