package animals;
import constvalues.Pict;
import worldsimulator.Animal;
import worldsimulator.Organism;
import constvalues.Position;
import constvalues.Org;
import constvalues.Raport;
import java.util.Random;
import constvalues.Move;
import constvalues.Intwrap;
import worldsimulator.World;
/**
 *
 * @author salam
 */
public class Antelope extends Animal {

    public Antelope(Position _pos, World _current_world, int _strength, int _gender)  {
        super(_pos, _current_world, _strength, Org.ANTELOPE_BASIC_INIT, Org.ANTELOPE_SYMBOL, Org.ANTELOPE_MATURE_AGE, Org.ANTELOPE_NAME, Org.ANTELOPE_PROPAGATION_CHANCE, Org.ANTELOPE_DEATH_AGE,
            Org.ANTELOPE_STEP_SIZE, Org.GENERAL_CHANCE_OF_MOVING, _gender);
    }
    
    public Antelope(Position _pos, World _current_world)  {
        super(_pos, _current_world, Org.ANTELOPE_BASIC_STR, Org.ANTELOPE_BASIC_INIT, Org.ANTELOPE_SYMBOL, Org.ANTELOPE_MATURE_AGE, Org.ANTELOPE_NAME, Org.ANTELOPE_PROPAGATION_CHANCE, Org.ANTELOPE_DEATH_AGE,
            Org.ANTELOPE_STEP_SIZE, Org.GENERAL_CHANCE_OF_MOVING);
    }

    @Override
    public void create_a_descendant(Position new_pos)  {
        current_world.get_organisms()[new_pos.y][new_pos.x] = new Antelope(new_pos, current_world);
    }
    @Override
    public String draw() {
        return Pict.ANTELOPE_ICON;
    }

    @Override
    public int collision_course(Organism attacker)  {
        String communique;
        Animal attck_to_animal = null; // attacker is an animal (plants dont initialize attack)
        if (attacker instanceof Animal) {
            attck_to_animal = (Animal) attacker;
        }
        Intwrap amount_of_poss_dir = new Intwrap(0);
        Random rand = new Random();

        boolean ant_escape_chance = rand.nextInt(100) < Org.ANTELOPE_ESCAPE_CHANCE; // 100 is the maximum chance
        //int attacker_str = attck_to_animal.get_strength();
        int result;
        if (ant_escape_chance) {
            Position new_pos = new Position(0,0);
            int[] directions = new int[Move.NUMBER_OF_DIRECTIONS]; // all directions
            directions[0] = Org.FAILURE; //assume, that every neighbouring field is taken
            directions[1] = Org.FAILURE;
            directions[2] = Org.FAILURE;
            directions[3] = Org.FAILURE;
            int drawn_direction;
            int step = Org.GENERAL_STEP_SIZE;

            check_free_fields(directions, amount_of_poss_dir, step_size);
            if (amount_of_poss_dir.value != 0) // if there is a possibility, escape making a big jump (2 fields)
            {
                step = step_size;
            } else // if not, try to escape with a smaller jump (1 field)
            {
                check_free_fields(directions, amount_of_poss_dir, Org.GENERAL_STEP_SIZE);
                step = Org.GENERAL_STEP_SIZE;
            }

            if (amount_of_poss_dir.value != 0) {
                drawn_direction = draw_direction(directions, amount_of_poss_dir);
                new_pos = return_position(drawn_direction, step);
                if (current_world.get_organisms()[new_pos.y][new_pos.x] == null) {
                    Position old_ant_pos = pos;
                    change_position(new_pos);
                    if (attck_to_animal != null) {
                        attck_to_animal.change_position(old_ant_pos); // attacker takes an old place of the antelope
                    }
                }
                result = Move.ESCAPE;
                communique = "Antelope managed to escape making a jump of size: " + Integer.toString(step) + "!";
            } // if there are no possible ways of moving, then don't escape 
            else //cout << "There was no place for escaping with moving range = 2 :(" << endl;
            {
                communique = "There was no room for antelope to escape :(";
                result = super.collision_course(attacker); // just compare strength
            }
        } else {
            result = super.collision_course(attacker); // just compare strength
            communique = "Antelope wasn't lucky enough to escape :(";
        }
        current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
        return result;
    }
}