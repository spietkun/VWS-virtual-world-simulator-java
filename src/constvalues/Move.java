package constvalues;

/**
 *
 * @author salam
 */
public class Move {
//MOVING
    public static final int NUMBER_OF_DIRECTIONS = 4;

//DIRECTIONS
// use FAILURE if organism can't make action in this direction
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;

//FIGHTING
    public static final int ATTACKER_WON = 0;
    public static final int DEFENDER_WON = 1;
    public static final int DRAW = 2;
    public static final int ESCAPE = 3;
    public static final int REFLECTION = 4;
    public static final int INDIVIDUAL = 5;
}
