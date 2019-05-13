package worldsimulator;

import constvalues.Position;
import constvalues.Org;
import constvalues.Move;
import constvalues.Intwrap;
import constvalues.Raport;
import java.awt.Image;
import java.util.Random;

/**
 *
 * @author salam
 */
public abstract class Organism {
    //PROTECTED
    protected int strength;
    protected int initiative;
    protected boolean if_moved; //holds information if an organism moved in current round;
    protected Position pos = new Position(0, 0); // remember to pass a single Position to constructor (not one Position obejct instance to many organisms)
    protected World current_world = null;
    protected int gender;
    protected int age;
    protected int ID_number; // start from nr 0
    //default variables
    protected final char symbol;
    protected final int maturity_age;
    protected final String species_name;
    protected final int propagation_chance;
    protected final int death_age;

    //PUBLIC

    public Organism(Position _pos, World _current_world, int _strength, int _initiative, char _symbol, int _maturity_age,
        String _species_name, int _propagation_chance, int _death_age) {
        ID_number = _current_world.get_number_of_organisms();
        symbol = _symbol;
        maturity_age = _maturity_age;
        species_name = _species_name;
        propagation_chance = _propagation_chance;
        death_age = _death_age;
        strength = _strength;
        initiative = _initiative;
        pos = _pos;
        current_world = _current_world;
        age = 0;
        current_world.increase_number_of_organisms();
    }

    public String return_organism_info_() {
        String gender_string;
        int gender = get_gender();
        switch (gender) {
            case Org.MALE:
                gender_string = "M";
                break;
            case Org.FEMALE:
                gender_string = "F";
                break;
            case Org.PERFECT:
                gender_string = "P";
                break;
            default:
                gender_string = "undentified";
        }
        String info_communique = get_species_name() + " - its info is: str: " + Integer.toString(get_strength()) +
            ", age: " + Integer.toString(get_age()) + ", gender: " + gender_string + ", pos: [" + Integer.toString(pos.x) +
            ", " + Integer.toString(pos.y) + "]";

        return info_communique;
    }
    
    abstract public String draw();

    public void annihilate() {
        int HEIGHT = current_world.get_HEIGHT();
        int width = current_world.get_width();

        for (int i = 0; i < HEIGHT * width; i++) {
            if (current_world.get_sorted_organisms()[i] == current_world.get_organisms()[pos.y][pos.x]) // set sorted pointer to null
            {
                current_world.get_sorted_organisms()[i] = null;
                break;
            }
        }
        (current_world.get_organisms())[pos.y][pos.x] = null;
        //delete (current_world.get_organisms())[pos.y][pos.x];
    }

    public void grow_older() {
        age++;
    }

    public char get_symbol() {
        return symbol;
    }

    public int get_maturity_age() {
        return maturity_age;
    }

    public String get_species_name() {
        return species_name;
    }

    public int get_propagation_chance() {
        return propagation_chance;
    }

    public int get_death_age() {
        return death_age;
    }

    public void set_position(Position new_pos) {
        pos = new_pos;
    }

    public Position get_position() {
        return pos;
    }

    public boolean get_if_moved() {
        return if_moved;
    }

    public void set_if_moved(boolean value) {
        if_moved = value;
    }

    public int get_strength() {
        return strength;
    }

    public void set_strength(int _strength) {
        strength = _strength;
    }

    public int get_gender() {
        return gender;
    }

    public void set_gender(int _gender) {
        gender = _gender;
    }

    public int get_smell_skill() {
        return Org.NORMAL_SMELL_SKILL;
    }

    public int get_age() {
        return age;
    }

    public void set_age(int _age) {
        age = _age;
    }

    public int get_initiative() {
        return initiative;
    }

    public int get_ID_number() {
        return ID_number;
    }

    public void set_ID_number(int _ID_number) {
        ID_number = _ID_number;
    }

    public void set_world(World New_World) {
        current_world = New_World;
    }

    abstract public void action();

    public void collision(Organism attacker){
   
        Position att_pos = attacker.get_position();
        String communique;
        
        communique = collision_communique(attacker);
            current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
        
        int fight_result = collision_course(attacker);
            switch (fight_result) {
                case Move.DEFENDER_WON:
                        attacker.annihilate();
                        communique = "The winner is: defender - " + species_name + "!";
                        current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
                    break;
                case Move.DRAW: // The attacking animal wins
                    communique = "DRAW! The same strength! But the attcker has an advantage";
                    current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
                    //do the next case
                case Move.ATTACKER_WON:
                        annihilate();
                        attacker.change_position(pos); // attacker takes place of the defender
                        communique = "The winner is: attacker - " + attacker.get_species_name() + "!";
                        current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
                    break;
    
            }
    }

    public boolean is_organism_mature() {
        boolean result = false;
        int mature = get_maturity_age();
        if (age >= mature) // ktory mature_age?
        {
            result = true;
        }
        return result;
    }

    public void increase_strength(int power) {
        strength += power;
    }

    public int multiplication() {
        Random rand = new Random();
        int result = Org.NOT_ENOUGH_LUCK_FOR_BREEDING;
        int chance_of_propagating = get_propagation_chance();
        boolean if_propagate = (rand.nextInt(100)) < chance_of_propagating; // chance that organism will propagate
        if (if_propagate) {
            Position new_pos = new Position(0, 0);
            int[] directions = new int[Move.NUMBER_OF_DIRECTIONS]; // all directions
            directions[0] = Org.FAILURE; //assume, that every neighbouring field is taken
            directions[1] = Org.FAILURE;
            directions[2] = Org.FAILURE;
            directions[3] = Org.FAILURE;
            Intwrap amount_of_poss_dir = new Intwrap(0);
            int drawn_direction;

            check_free_fields(directions, amount_of_poss_dir, Org.BREEDING_RANGE);
            if (amount_of_poss_dir.value != 0) {
                drawn_direction = draw_direction(directions, amount_of_poss_dir);
                new_pos = return_position(drawn_direction, Org.BREEDING_RANGE);
                create_a_descendant(new_pos);
                result = Org.BREEDING_SUCCESS; // multiplication was a success
            } else // if there are no possible fields, then don't multiplicate
            {
                result = Org.NO_PLACE_FOR_A_DESCENDANT; // multiplication didnt happen
            }
        } else {
            result = Org.NOT_ENOUGH_LUCK_FOR_BREEDING;
        }
        return result;
    }
    
    protected void check_free_fields(int[] directions, Intwrap amount_of_possible_directions, int range) { // array with only 1 element!
        Organism[][] buf = current_world.get_organisms();
        if ((pos.x - range) >= 0 && buf[pos.y][pos.x - range] == null) {
            directions[0] = Move.LEFT; // e.g. there's a free place on the left
            amount_of_possible_directions.value++;
        }
        if ((pos.x + range) < current_world.get_width() && buf[pos.y][pos.x + range] == null) {
            directions[1] = Move.RIGHT;
            amount_of_possible_directions.value++;
        }
        if ((pos.y - range) >= 0 && buf[pos.y - range][pos.x] == null) {
            directions[2] = Move.UP;
            amount_of_possible_directions.value++;
        }
        if ((pos.y + range) < current_world.get_HEIGHT() && buf[pos.y + range][pos.x] == null) {
            directions[3] = Move.DOWN;
            amount_of_possible_directions.value++;
        }
    }

    protected void check_possible_directions(int[] directions, Intwrap amount_of_possible_directions, int step_size) { // array with one element
        int smell_skill = get_smell_skill();
        Organism[][] buf = current_world.get_organisms();
        if ((pos.x - step_size) >= 0) {
            if (smell_skill == Org.AVOID_STR_SMELL_SKILL) {
                if (buf[pos.y][pos.x - step_size] == null || (buf != null && buf[pos.y][pos.x - step_size].get_strength() <= strength)) // if there's an animal next to the moving organism
                //it won't go there if an organism has more strength
                {
                    directions[0] = Move.LEFT; // going left, an animal is still in the "board"; let it go
                    amount_of_possible_directions.value++;
                }
            } else {
                directions[0] = Move.LEFT;
                amount_of_possible_directions.value++;
            }
        }
        if ((pos.x + step_size) < current_world.get_width()) {
            if (smell_skill == Org.AVOID_STR_SMELL_SKILL) {
                if (buf[pos.y][pos.x + step_size] == null || (buf != null && buf[pos.y][pos.x + step_size].get_strength() <= strength)) {
                    directions[1] = Move.RIGHT;
                    amount_of_possible_directions.value++;
                }
            } else {
                directions[1] = Move.RIGHT;
                amount_of_possible_directions.value++;
            }
        }
        if ((pos.y - step_size) >= 0) {
            if (smell_skill == Org.AVOID_STR_SMELL_SKILL) {
                if (buf[pos.y - step_size][pos.x] == null || (buf != null && buf[pos.y - step_size][pos.x].get_strength() <= strength)) {
                    directions[2] = Move.UP;
                    amount_of_possible_directions.value++;
                }
            } else {
                directions[2] = Move.UP;
                amount_of_possible_directions.value++;
            }
        }
        if ((pos.y + step_size) < current_world.get_HEIGHT()) {
            if (smell_skill == Org.AVOID_STR_SMELL_SKILL) {
                if (buf[pos.y + step_size][pos.x] == null || (buf != null && buf[pos.y + step_size][pos.x].get_strength() <= strength)) {
                    directions[3] = Move.DOWN;
                    amount_of_possible_directions.value++;
                }
            } else {
                directions[3] = Move.DOWN;
                amount_of_possible_directions.value++;
            }
        }
    }

    abstract protected void create_a_descendant(Position new_pos);

    protected int draw_direction(int[] directions, Intwrap amount_of_poss_dir) {
        Random rand = new Random();
        int[] possible_directions;
        possible_directions = new int[amount_of_poss_dir.value];
        int counter = 0;
        for (int i = 0; i < Move.NUMBER_OF_DIRECTIONS; i++) {
            if (directions[i] != Org.FAILURE) {
                possible_directions[counter] = directions[i];
                //cout << "possible directions: " << possible_directions[counter] << endl;
                counter++;
            }
        }

        int random_index = rand.nextInt(amount_of_poss_dir.value);
        //cout << "random index: " << random_index << endl;
        //cout << "ilosc kierunkow: " << amount_of_poss_dir << endl;
        int drawn_direction = possible_directions[random_index];
        //cout << "drawn direction: " << drawn_direction << endl;
        return drawn_direction;
    }

    protected Position return_position(int direction, int step_size) // based on direction
    {
        Position new_pos = new Position(0, 0);
        switch (direction) {
            case Move.LEFT:
                //cout << "LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL" << endl;
                new_pos.x = pos.x - step_size;
                new_pos.y = pos.y; // stays the same
                break;
            case Move.RIGHT:
                //cout << "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR" << endl;
                new_pos.x = pos.x + step_size;
                new_pos.y = pos.y;
                break;
            case Move.UP:
                //	cout << "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu" << endl;
                new_pos.y = pos.y - step_size;
                new_pos.x = pos.x;
                break;
            case Move.DOWN:
                //cout << "DDDDDDDDDDDDDDDDDDDDDDDDDD" << endl;
                new_pos.y = pos.y + step_size;
                new_pos.x = pos.x;
                break;
            default:
                new_pos.y = pos.y;
                new_pos.x = pos.x;
        }
        //cout << "strength: " << strength << endl;
        //cout << "OLD_X: " << pos.x << " . " << new_x << endl;
        //cout << "OLD_Y: " << pos.y << " . " << new_y << endl << endl;
        return new_pos;
    }

    protected int random_next_field() {
        Random rand = new Random();
        int buf = rand.nextInt(Move.NUMBER_OF_DIRECTIONS);
        return buf;
    }

    protected int collision_course(Organism attacker) {
        int attacker_str = attacker.get_strength();
        int result;

        if (strength > attacker_str) result = Move.DEFENDER_WON;
        else if (strength == attacker_str) result = Move.DRAW;
        else result = Move.ATTACKER_WON;

        return result;
    }
    
    public void change_position(Position new_pos) {
        int HEIGHT = current_world.get_HEIGHT();
        int width = current_world.get_width();

        for (int i = 0; i < HEIGHT * width; i++) {
            if (current_world.get_sorted_organisms()[i] == current_world.get_organisms()[new_pos.y][new_pos.x]) // set sorted pointer to null - this new field will be taken so it musn't call action in sorted board
            {
                current_world.get_sorted_organisms()[i] = null;
                break;
            }
        }
        (current_world.get_organisms())[new_pos.y][new_pos.x] = (current_world.get_organisms())[pos.y][pos.x]; //change pointers
        (current_world.get_organisms())[pos.y][pos.x] = null;

        set_position(new_pos); // change position (move an animal)
    }
    
    protected String collision_communique(Organism attacker){
 
        Position att_pos = attacker.get_position();
        String communique;
        communique = communique = "Organisms met: " + species_name + " D[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) +
                "]" + " str=" + Integer.toString(get_strength()) + " and " + attacker.get_species_name() + " A[" + Integer.toString(att_pos.x) + "," +
                Integer.toString(att_pos.y) + "]" + " str=" + Integer.toString(attacker.get_strength()) + " - " + "met and they started to FIGHT!";
        
        return communique;
    }
}