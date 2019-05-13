package constvalues;

/**
 *
 * @author salam
 */
public class Org {
    // ANIMALS /////////////

//GENERAL
    public static final int SUCCESS = 1;
    public static final int FAILURE = 0;

    public static final int N_SPECIES = 11; // without cyber sheep // with human
    public static final double MULTIPLIER_FOR_ORGANISMS = 0.9; // percent of the board that will be filled with animals (0.1 = 10%)
    public static final String GENERAL_SPECIES_NAME = "Organism";
    public static final char GENERAL_ORGANISM_SYMBOL = 'o';
    public static final int GENERAL_STEP_SIZE = 1;
    public static final int GENERAL_CHANCE_OF_MOVING = 100; //100% desire to move
    public static final int GENERAL_MATURE_AGE = 0;
    public static final int GENERAL_PROPAGATION_CHANCE = 0;
    public static final int ANIMAL_PROPAGATION_CHANCE = 100; //in % 
    public static final int GENERAL_DEATH_AGE = 15;
    public static final int NORMAL_SMELL_SKILL = 0; // animal's smell doesn't influence moving on the board
    public static final int AVOID_STR_SMELL_SKILL = 1; // animals won't step on the board, where a stronger organism stands

//BREEDING
    public static final int MALE = 0;
    public static final int FEMALE = 1;
    public static final int PERFECT = 2; //(term for plants)
    public static final int BREEDING_RANGE = 1;
//BREEDING RESULTS
    public static final int NO_PLACE_FOR_A_DESCENDANT = 0;
    public static final int NOT_ENOUGH_LUCK_FOR_BREEDING = 1;
    public static final int BREEDING_SUCCESS = 2;

//INDIVIDUAL

//WOLF
    public static final String WOLF_NAME = "Wolf";
    public static final int WOLF_BASIC_STR = 9;
    public static final int WOLF_BASIC_INIT = 5;
    public static final char WOLF_SYMBOL = 'w';
    public static final int WOLF_MATURE_AGE = 3;
    public static final int WOLF_PROPAGATION_CHANCE = 100; //in % 
    public static final int WOLF_DEATH_AGE = 120;

//SHEEP
    public static final String SHEEP_NAME = "Sheep";
    public static final int SHEEP_BASIC_STR = 4;
    public static final int SHEEP_BASIC_INIT = 4;
    public static final char SHEEP_SYMBOL = 's';
    public static final int SHEEP_MATURE_AGE = 1;
    public static final int SHEEP_PROPAGATION_CHANCE = 100; //in % 
    public static final int SHEEP_DEATH_AGE = 145;

//FOX
public static final String FOX_NAME = "Fox";
public static final int FOX_BASIC_STR = 3;
public static final int FOX_BASIC_INIT = 7;
public static final char FOX_SYMBOL = 'f';
public static final int FOX_MATURE_AGE = 1;
public static final int FOX_PROPAGATION_CHANCE = 100; //in % 
public static final int FOX_DEATH_AGE = 150; //in %

//TURTLE
public static final String TURTLE_NAME = "Turtle";
public static final int TURTLE_BASIC_STR = 2;
public static final int TURTLE_BASIC_INIT = 1;
public static final char TURTLE_SYMBOL = 't';
public static final int TURTLE_MATURE_AGE = 5;
public static final int TURTLE_CHANCE_OF_MOVING = 25; // (%)
public static final int TURTLE_REFLECTION_CONDITION = 5; // (max strength of attacker = 5)
public static final int TURTLE_PROPAGATION_CHANCE = 100; //in % 
public static final int TURTLE_DEATH_AGE = 400; //in % 

//ANTELOPE
public static final String ANTELOPE_NAME = "Antelope";
public static final int ANTELOPE_BASIC_STR = 4;
public static final int ANTELOPE_BASIC_INIT = 4;
public static final char ANTELOPE_SYMBOL = 'a';
public static final int ANTELOPE_MATURE_AGE = 3;
public static final int ANTELOPE_STEP_SIZE = 2;
public static final int ANTELOPE_ESCAPE_CHANCE = 51; // (in %)
public static final int ANTELOPE_PROPAGATION_CHANCE = 100; //in % 
public static final int ANTELOPE_DEATH_AGE = 190; //in % 

//HUMAN
public static final String HUMAN_NAME = "Human";
public static final int HUMAN_BASIC_STR = 5;
public static final int HUMAN_BASIC_INIT = 4;
public static final char HUMAN_SYMBOL = 'H';
public static final int HUMAN_MATURE_AGE = 18;
public static final int HUMAN_PROPAGATION_CHANCE = 0;
public static final int HUMAN_ALZUR_SHIELD_DURATION = 5; //5 rounds
public static final int HUMAN_ALZUR_SHIELD_BREAK = 5; //5 rounds
public static final int HUMAN_DEATH_AGE = 0; //in %  // 0 means infinite

//CYBER SHEEP
/*public static final int CSHEEP_BASIC_STR = 11;
public static final int CSHEEP_BASIC_INIT = 4;
public static final char CSHEEP_SYMBOL = 'c';*/

// PLANTS ////////////////////

//GENERAL
public static final int GENERAL_PLANT_RANGE = 1; //range of propagation
public static final int GENERAL_PLANT_INIT = 0;
public static final int GENERAL_PROPAGATION_AMOUNT = 1;

//INDIVIDUAL

//GRASS
public static final String GRASS_NAME = "Grass";
public static final int GRASS_BASIC_STR = 0;
public static final char GRASS_SYMBOL = '1';
public static final int GRASS_PROPAGATION_CHANCE = 10; //in %
public static final int GRASS_MATURE_AGE = 1;
public static final int GRASS_DEATH_AGE = 10; 

//SOWTHISTLE
public static final String SOWTHISTLE_NAME = "Sowthistle";
public static final int SOWTHISTLE_BASIC_STR = 0; // mlecz
public static final char SOWTHISTLE_SYMBOL = '2';
public static final int SOWTHISTLE_PROPAGATION_CHANCE = 5; //in %
public static final int SOWTHISTLE_MATURE_AGE = 2;
public static final int SOWTHISTLE_PROPAGATION_AMOUNT = 3; // how many times a sowthistle will try to propagate
public static final int SOWTHISTLE_DEATH_AGE = 6;

//GUARANA
public static final String GUARANA_NAME = "Guarana";
public static final int GUARANA_BASIC_STR = 0;
public static final char GUARANA_SYMBOL = '3';
public static final int GUARANA_PROPAGATION_CHANCE = 10; //in %
public static final int GUARANA_MATURE_AGE = 8;
public static final int GUARANA_POWER = 3;
public static final int GUARANA_DEATH_AGE = 20;

//BELLADONNA
public static final String BELLADONNA_NAME = "Belladonna";
public static final int BELLADONNA_BASIC_STR = 99; // "wolf berries"
public static final char BELLADONNA_SYMBOL = '4';
public static final int BELLADONNA_PROPAGATION_CHANCE = 2; //in %
public static final int BELLADONNA_MATURE_AGE = 5;
public static final int BELLADONNA_DEATH_AGE = 10;

//HOGWEED
public static final String HOGWEED_NAME = "Hogweed";
public static final int HOGWEED_BASIC_STR = 10; // barszcz sosnowskiego
public static final char HOGWEED_SYMBOL = '5';
public static final int HOGWEED_PROPAGATION_CHANCE = 4; //in %
public static final int HOGWEED_MATURE_AGE = 2;
public static final int HOGWEED_KILLING_ZONE = 1;
public static final int HOGWEED_DEATH_AGE = 8; 
}
