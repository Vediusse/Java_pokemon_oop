package Lab2.Attacks;

import Lab2.Attacks.Extends.Physical;
import Lab2.Attacks.Extends.Special;
import Lab2.Entity.Pokemonchill;
import Lab2.utils.DefaultAttack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Status;

public class Attack {
    public Attack() {

    }
    protected void applyEffects(Pokemon poke, List<Object> attackEffects) {
        if (attackEffects.get(0).equals("null")) return;
        String ability = attackEffects.get(0).toString();
        double turns = Double.parseDouble(attackEffects.get(1).toString());
        double chance = Double.parseDouble(attackEffects.get(2).toString());
        double attack = Double.parseDouble(attackEffects.get(3).toString());
        try {
            Status attackStatus = Status.valueOf(ability);
            Effect effect = (new Effect()).condition(attackStatus).turns((int) turns).chance(chance).attack(attack);
            poke.addEffect(effect);
        }
        catch (IllegalArgumentException e) {
            String effectName = attackEffects.get(0).toString();
            switch (effectName) {
                case "ADD_SPECIAL_DEFENSE", "ADD_ATTACK" -> {
                    String stat = effectName.split("_")[1];
                    if (stat.equals("SPECIAL")) {
                        stat = "SPECIAL_DEFENSE";
                    }
                    poke.setMod(Stat.valueOf(stat), (int) turns);
                }
                case "CONFUSE", "Taunt" -> {
                    if (Math.random() < chance) Effect.confuse(poke);
                }
                case "COPY_ABILITY" ->{
                    if(poke instanceof Pokemonchill) {
                        List<String> all_enemy_ability = (((Pokemonchill) poke).getPokemonAttacks());
                        String copied_ability = all_enemy_ability.get((int) (Math.random() * all_enemy_ability.size()));
                        List<Object> copyEffect = new ArrayList<>(Arrays.asList(copied_ability, turns, chance, attack));
                        applyEffects(poke,copyEffect);
                    }
                }
            }
        }

    }

    protected void applyDamage(Pokemon poke, String attackDamage, double damage) {
        if (attackDamage.equals("null")) return;
        poke.setMod(Stat.HP, (int) Math.round(damage));
    }

    public void applyOppEffects(Pokemon poke) {

    }
    public void applySelfEffects(Pokemon poke) {
    }

    public void applyOppDamage(Pokemon poke, double damage) {
    }

    public void applySelfDamage(Pokemon poke, double damage) {
    }
}
