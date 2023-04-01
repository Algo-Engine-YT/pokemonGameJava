import java.util.Scanner;
import java.util.Random;

/**
 * Driver class
 */
public class PokemonGame {
    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        Pokemon pokemon = new Pokemon("dummy", 1, "atk1", "atk2", "atk3");

        // Get user input
        boolean validInput = false;
        while (!validInput) {
            String choice = getUserInput(scan);
            if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                 pokemon = makePokemon(choice);
                validInput = true;
            } else {
                System.out.println("Invalid choice");
            }
        }

        // Generate computer's Pokemon
        Pokemon compPokemon = new Pokemon("Mewtwo", 500, "Confusion", "Psystrike", "Hyper Beam");

        // Start game
        System.out.println("Your Pokemon is " + pokemon.getName());
        System.out.println("Your opponent is " + compPokemon.getName());
        System.out.println("BATTLE!!\n");

        boolean play = true;
        boolean userTurn = true;
        while (play) {
            // User's turn
            if (userTurn) {
                System.out.print("*** YOUR TURN ***\n" +
                        "Choose an attack:\n" +
                        "  1. " + pokemon.getAtk(1) + " (Low Risk)\n" +
                        "  2. " + pokemon.getAtk(2) + " (Medium Risk)\n" +
                        "  3. " + pokemon.getAtk(3) + " (High Risk)\n" +
                        "Enter 1, 2, or 3: "
                );
                String userRisk = scan.nextLine().trim();

                // Attack and show results
                if (userRisk.equals("1") || userRisk.equals("2") || userRisk.equals("3")) {
                    int risk = Integer.parseInt(userRisk);
                    int atkVal = pokemon.attack(risk);
                    compPokemon.setHp(compPokemon.getHp() - atkVal);
                    System.out.println(pokemon.getName() + " used " + pokemon.getAtk(risk) + " and dealt " + atkVal + " damage!");
                    System.out.println("The opponent's HP is now " + Math.max(compPokemon.getHp(), 0) + "\n");
                } else {
                    System.out.println("Invalid option. Enter 1, 2, or 3.\n");
                    continue;
                }
            // Computer's turn
            } else {
                // Attack and show results
                System.out.println("*** OPPONENT'S TURN ***\nPress enter to continue");
                scan.nextLine();
                int compRisk = rand.nextInt(3) + 1;
                int compAtkVal = compPokemon.attack(compRisk);
                pokemon.setHp(pokemon.getHp() - compAtkVal);
                System.out.println(compPokemon.getName() + " used " + compPokemon.getAtk(compRisk) + " and dealt " + compAtkVal + " damage!");
                System.out.println("Your HP is now " + Math.max(pokemon.getHp(), 0) + "\n");
            }

            // Check end condition
            play = checkEndCondition(pokemon.getHp(), compPokemon.getHp());

            // Switch turn
            userTurn = !userTurn;
        }
    }

    /**
     * Gets user input
     * @param reader Scanner object
     * @return The trimmed user input
     */
    private static String getUserInput(Scanner reader) {
        System.out.print("Choose your Pokemon!\n" +
                "1. Pikachu\n" +
                "2. Charmander\n" +
                "3. Squirtle\n\n" +
                "Enter 1, 2, or 3: "
        );
        return reader.nextLine().trim();
    }


    /**
     * Makes a Pokemon object
     * @param userChoice The user's choice of Pokemon
     * @return the Pokemon
     */
    private static Pokemon makePokemon(String userChoice) {
        switch (userChoice) {
            case "1":
                return new Pokemon("Pikachu", 500, "Tail Whip", "Volt Tackle", "Thunderbolt");
            case "2":
                return new Pokemon("Charmander", 400, "Ember", "Flamethrower", "Inferno");
            case "3":
                return new Pokemon("Squirtle", 300, "Bubble", "Water Gun", "Aqua Jet");
            default:
                return new Pokemon("Dummy", 1, "atk1", "atk2", "atk3");
        }
    }


    /**
     * Checks whether the game should continue or end
     * @param userHp The user's Pokemon's HP
     * @param compHp The computer's Pokemon's HP
     * @return Whether the game should continue or not
     */
    private static boolean checkEndCondition(int userHp, int compHp) {
        // User loses
        if (userHp <= 0) {
            System.out.println("You lose :(");
            return false;
        }
        // User wins
        if (compHp <= 0) {
            System.out.println("You win!");
            return false;
        }
        // Continue game
        return true;
    }
}
