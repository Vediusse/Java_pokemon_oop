package Lab2.utils;

import Lab2.Main;
import ru.ifmo.se.pokemon.Type;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Default {

    public static final String pokemonsFileName = "Lab2/data/token.csv";
    public final List<List<String>> objectData;

    public Default(String fileName, String className) {
        this.objectData = getValueByName(fileName, className);
        if (objectData == null) {
            throw new IllegalArgumentException("objectData is empty");
        }
    }

    private static List<List<String>> getAllData(String fileName) {
        List<List<String>> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(Objects.requireNonNull(Default.class.getClassLoader().getResourceAsStream(fileName)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                data.add(Arrays.asList(values));
            }
        }
        return data;
    }


    private static List<List<String>> getValueByName(String fileName, String className) {
        List<List<String>> fileData = getAllData(fileName);
        for (List<String> fileDatum : fileData) {
            if (fileDatum.get(0).equals(className)) {
                List<List<String>> data = new ArrayList<>();
                data.add(fileData.get(0));
                data.add(fileDatum);

                return data;
            }
        }
        return null;
    }

    public static List<String> getAllName(String fileName) {
        List<List<String>> fileData = getAllData(fileName);
        List<String> data = new ArrayList<>();
        for (int i = 1; i < fileData.size(); i++) {
            data.add(fileData.get(i).get(fileData.get(0).indexOf("name")));
        }
        return data;
    }


    public static List<Type> getObjectTypes(List<String> keys, List<String> values) {
        String pokemonTypes = values.get(keys.indexOf("types"));
        List<Type> types = new ArrayList<>();
        for (String pokemonType : pokemonTypes.split(";")) {
            types.add(Type.valueOf(pokemonType));
        }
        return types;
    }

    public static List<Object> getObjectByValue(List<String> keys, List<String> values, String field) {
        String Field = values.get(keys.indexOf(field));
        return new ArrayList<>(Arrays.asList(Field.split(";")));
    }


    public static HashMap<String, List<Object>> getObjectByValues(List<String> keys, List<String> values, List<String> fields) {
        HashMap<String, List<Object>> resultMap = new HashMap<>();
        for (String field : fields) {
            List<Object> value = getObjectByValue(keys, values, field);
            resultMap.put(field, value);
        }
        return resultMap;

    }

    public static List<String> getObjectAttack(List<String> keys, List<String> values) {
        String pokemonAttacks = values.get(keys.indexOf("attacks"));
        String[] attackArray = pokemonAttacks.split(";");
        return new ArrayList<>(Arrays.asList(attackArray));
    }
}
