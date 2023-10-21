package Lab2.Entity;

import Lab2.Attacks.Extends.*;
import Lab2.utils.DefaultAttack;
import Lab2.utils.DefaultState;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pokemonchill extends Pokemon {
    public List<String> pokemonAttacks;

    public Pokemonchill(String pokemonClassName, int lvl) {
        super(pokemonClassName, lvl);

        DefaultState ds = new DefaultState(DefaultState.pokemonsFileName, pokemonClassName);
        List<Type> pokemonTypes = ds.getDefaultPokemonTypes();
        for (Type pokemonType : pokemonTypes) {
            try {
                setType(pokemonType);
            } catch (NumberFormatException ignored) {
            }
        }

        HashMap<String, List<Object>> stats = ds.getDefaultPokemonStats(Arrays.asList("hp", "att", "def", "spAtt", "spDef", "speed"));

        setStats(
                parseStatValue(stats, "hp"),
                parseStatValue(stats, "att"),
                parseStatValue(stats, "def"),
                parseStatValue(stats, "spAtt"),
                parseStatValue(stats, "spDef"),
                parseStatValue(stats, "speed")
        );

        pokemonAttacks = ds.getDefaultAttack();

        for (String attack : pokemonAttacks) {
            DefaultAttack da = new DefaultAttack(DefaultAttack.pokemonsFileName, attack);
            HashMap<String, List<Object>> attackStats = da.getDefaultAttackStats(Arrays.asList("power", "accuracy", "priority", "hits", "oppEffects;turns;chance;attack", "selfEffects;turns;chance;attack", "describe"));
            String attackExtend = da.getDefaultAttackExtends().get(0).toString();
            Type attackType = da.getDefaultAttackTypes().get(0);
            switch (attackExtend) {
                case "Special" -> this.addMove(new Special(attack, attackType, attackStats));
                case "Physical" -> this.addMove(new Physical(attack, attackType, attackStats));
                case "Status" -> this.addMove(new Status(attack, attackType, attackStats));
            }

        }

    }

    private double parseStatValue(Map<String, List<Object>> stats, String key) {
        return Double.parseDouble(stats.get(key).get(0).toString());
    }

    public List<String> getPokemonAttacks() {
        return this.pokemonAttacks;
    }

}

