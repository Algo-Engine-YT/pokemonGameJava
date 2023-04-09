import java.util.Random;

/**
 * Pokemon class
 */
public class Pokemon {
    // Instance variables
    private String name;
    private int hp;
    private String atk1;
    private String atk2;
    private String atk3;

    /**
     * Constructor
     */
    Pokemon(String name, int hp, String atk1, String atk2, String atk3) {
        this.name = name;
        this.hp = hp;
        this.atk1 = atk1;
        this.atk2 = atk2;
        this.atk3 = atk3;
    }

    /**
     * Gets the name of the Pokemon
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the hp of the Pokemon
     * @return the hp
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * Gets the name of an attack
     * @param index identifies the attack
     * @return the name of the attack
     */
    public String getAtkName(int index) {
        switch (index) {
            case 1: return this.atk1;
            case 2: return this.atk2;
            case 3: return this.atk3;
            default: return "Invalid attack number";
        }
    }

    /**
     * Sets the hp of the Pokemon
     * @param newHp the new hp of the Pokemon
     */
    public void setHp(int newHp) {
        this.hp = newHp;
    }

    /**
     * Performs the Pokemon's attack based on the level
     * @param risk 1, 2, or 3, with 3 being the highest risk
     * @return The attack value
     */
    public int attack(int risk) {
        Random rand = new Random();
        // Stronger attacks are also riskier
        switch (risk) {
            case 1: return 100 + rand.nextInt(21) - 10;
            case 2: return 100 + rand.nextInt(101) - 50;
            case 3: return 100 + rand.nextInt(201) - 100;
            default: return -1; // Invalid risk value
        }
    }
}