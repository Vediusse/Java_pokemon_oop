package Lab2;
import Lab2.Entity.Pokemonchill;
import Lab2.utils.Default;
import Lab2.utils.DefaultState;
import ru.ifmo.se.pokemon.Battle;

import java.util.List;
import java.util.Scanner;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final int DEFAULT_LEVEL = 22; // Set default level for Pokemon

    public static void main(String[] args) {
        Battle battle = startPokemonSelectionProcess();
        battle.go();
    }

    // Starts the process of selecting Pokemon for player and enemy
    public static Battle startPokemonSelectionProcess() {
        Scanner scanner = new Scanner(System.in);
        List<String> pokemonTypes = Default.getAllName(DefaultState.pokemonsFileName);

        System.out.println("Выберите своего персонажа:");
        int playerChoice = makeChoice(pokemonTypes, scanner);

        System.out.println("Выберите персонажа для противника:");
        int enemyChoice = makeChoice(pokemonTypes, scanner);

        String playerPokemon = pokemonTypes.get(playerChoice);
        String enemyPokemon = pokemonTypes.get(enemyChoice);

        Pokemonchill player = new Pokemonchill(playerPokemon, DEFAULT_LEVEL);
        Pokemonchill enemy = new Pokemonchill(enemyPokemon, DEFAULT_LEVEL);

        Battle battle = new Battle();
        battle.addAlly(player);
        battle.addFoe(enemy);
        return battle;
    }

    // Prompts the user to select a Pokemonchill and returns the choice
    public static int makeChoice(List<String> pokemonTypes, Scanner scanner) {
        for (int i = 0; i < pokemonTypes.size(); i++) {
            System.out.println((i + 1) + ") " + pokemonTypes.get(i));
        }
        int choice = scanner.nextInt() - 1;
        scanner.nextLine();
        return choice;
    }
}