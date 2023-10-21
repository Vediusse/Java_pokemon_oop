package Lab2.Attacks.Extends;

import Lab2.Attacks.Attack;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

import java.util.HashMap;
import java.util.List;

public class Status extends StatusMove {
    private final Attack baseAttack;
    public String describe;

    public Status(String attack, Type attackType, HashMap<String, List<Object>> attackStats) {
        super(
                attackType,
                Double.parseDouble((String) attackStats.get("power").get(0)),
                Double.parseDouble((String) attackStats.get("accuracy").get(0)),
                Integer.parseInt((String) attackStats.get("priority").get(0)),
                Integer.parseInt((String) attackStats.get("hits").get(0))
        );
         baseAttack = new Base(attack);
        describe = (String) attackStats.get("describe").get(0);
    }
    @Override
    protected void applyOppEffects(Pokemon poke) {
        this.baseAttack.applyOppEffects(poke);
    }

    @Override
    protected void applySelfEffects(Pokemon poke) {
        this.baseAttack.applySelfEffects(poke);
    }

    @Override
    protected String describe() {
        return describe;
    }
}
