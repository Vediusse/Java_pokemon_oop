package Lab2.Attacks.Extends;

import Lab2.Attacks.Attack;
import Lab2.utils.DefaultAttack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import ru.ifmo.se.pokemon.Pokemon;

public class Base extends Attack {
    private final List<Object> oppAttackEffects;
    private final List<Object> selfAttackEffects ;
    private final String oppAttackDamage ;
    private final String selfAttackDamage ;
    public String describe;
    public Base(String attack) {
        DefaultAttack da = new DefaultAttack(DefaultAttack.pokemonsFileName, attack);
        HashMap<String, List<Object>> attackStats = da.getDefaultAttackStats(Arrays.asList("oppEffects;turns;chance;attack","selfEffects;turns;chance;attack", "oppDamage", "selfDamage","describe"));
        oppAttackEffects = attackStats.get("oppEffects;turns;chance;attack");
        selfAttackEffects =  attackStats.get("selfEffects;turns;chance;attack");
        oppAttackDamage = (String) attackStats.get("oppDamage").get(0);
        selfAttackDamage = (String) attackStats.get("selfDamage").get(0);
        describe = (String) attackStats.get("describe").get(0);

    }

    public void applyOppEffects(Pokemon poke) {
        applyEffects(poke, oppAttackEffects);
    }

    public void applySelfEffects(Pokemon poke) {
        applyEffects(poke, selfAttackEffects);
    }

    public void applyOppDamage(Pokemon poke, double damage) {

        applyDamage(poke, oppAttackDamage, damage);
    }

    public void applySelfDamage(Pokemon poke, double damage) {
        applyDamage(poke, selfAttackDamage, damage);
    }
}
