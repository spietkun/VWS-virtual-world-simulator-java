package animals;
import constvalues.Pict;
import worldsimulator.Animal;
import worldsimulator.Organism;
import constvalues.Position;
import constvalues.Org;
import constvalues.Power;
import constvalues.Raport;
import java.util.Random;
import constvalues.Move;
import constvalues.Intwrap;
import worldsimulator.World;

/**
 *
 * @author salam
 */
public class Human extends Animal {

    public Human(Position _pos, World _current_world, int _strength, int _gender) {
        super(_pos, _current_world, _strength, Org.HUMAN_BASIC_INIT, Org.HUMAN_SYMBOL, Org.HUMAN_MATURE_AGE, Org.HUMAN_NAME, Org.HUMAN_PROPAGATION_CHANCE, Org.HUMAN_DEATH_AGE,
            Org.GENERAL_STEP_SIZE, Org.GENERAL_CHANCE_OF_MOVING, _gender);
    }

    public Human(Position _pos, World _current_world) {
        super(_pos, _current_world, Org.HUMAN_BASIC_STR, Org.HUMAN_BASIC_INIT, Org.HUMAN_SYMBOL, Org.HUMAN_MATURE_AGE, Org.HUMAN_NAME, Org.HUMAN_PROPAGATION_CHANCE, Org.HUMAN_DEATH_AGE,
            Org.GENERAL_STEP_SIZE, Org.GENERAL_CHANCE_OF_MOVING);
    }

    @Override
    public void create_a_descendant(Position new_pos) {
        current_world.get_organisms()[new_pos.y][new_pos.x] = new Human(new_pos, current_world);
    }

    @Override
    public String draw() {
        return Pict.HUMAN_ICON;
    }

    @Override
    public void action() {
        //System.out.println(current_world.get_human_direction());
        int step_size = get_step_size();
        Random rand = new Random();
        int chance_of_moving = get_chance_of_moving();
        boolean if_human_moves = (rand.nextInt(100)) < chance_of_moving; // chance that human will move
        if (if_human_moves) {
            Position new_pos = new Position(0, 0);
            int[] directions = new int[Move.NUMBER_OF_DIRECTIONS]; // all directions
            directions[0] = Org.FAILURE; //assume, that animal can go nowhere
            directions[1] = Org.FAILURE;
            directions[2] = Org.FAILURE;
            directions[3] = Org.FAILURE;
            Intwrap amount_of_poss_dir = new Intwrap(0);
            int human_direction = current_world.get_human_direction();
            //cout << human_direction << endl;
            boolean if_can_move = false;

            check_possible_directions(directions, amount_of_poss_dir, step_size);
            for (int i = 0; i < Move.NUMBER_OF_DIRECTIONS; i++) {
                if (directions[i] == human_direction) {
                    if_can_move = true;
                    break;
                }
            }

            //cout << human_direction << endl << "////////////////////" << endl;
            if (if_can_move) // if there are no possible ways of moving, then don't move a human
            {
                new_pos = return_position(human_direction, step_size);
                if (current_world.get_organisms()[new_pos.y][new_pos.x] == null) {
                    change_position(new_pos);
                } else {
                    ((current_world.get_organisms()[new_pos.y][new_pos.x])).collision(this); // defender calls collision() 
                    //and attacker information (this) is sent
                }
            }
            //else cout << "No place for moving!" << endl;
        }
    }
    @Override
    public int collision_course(Organism attacker) {
        int result = Move.INDIVIDUAL; //?
        Power alzur_buf = current_world.get_alzur_power();
        if (alzur_buf.is_power_on == true) {
            String communique = "Human uses super_power - ALZUR_SHIELD. " + Integer.toString(alzur_buf.duration) + " rounds left!";
            current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
            Animal attck_to_animal = null; // attacker is an animal (plants dont initialize attack)
            if (attacker instanceof Animal) {
                attck_to_animal = (Animal) attacker;
            } // attacker is an animal (plants dont initialize attack)

            if (attck_to_animal != null) {
                Position new_pos = new Position(0, 0);
                Position old_att_pos = attacker.get_position();
                int[] directions = new int[Move.NUMBER_OF_DIRECTIONS]; // all directions
                directions[0] = Org.FAILURE; //assume, that every neighbouring field is taken
                directions[1] = Org.FAILURE;
                directions[2] = Org.FAILURE;
                directions[3] = Org.FAILURE;
                Intwrap amount_of_poss_dir = new Intwrap(0);
                int drawn_direction;

                check_possible_directions(directions, amount_of_poss_dir, step_size);
                if (amount_of_poss_dir.value != 0) // if there are no possible ways of moving, then don't move an animal
                {
                    drawn_direction = draw_direction(directions, amount_of_poss_dir);
                    new_pos = return_position(drawn_direction, step_size);

                    attck_to_animal.change_position(new_pos);
                    communique = "ALZUR SHIELD: " + attacker.get_species_name() + " was pushed away to [" + Integer.toString(new_pos.x) +
                        "," + Integer.toString(new_pos.y) + "]!";
                    current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);

                } else // animals goes back (stays) to his original place
                {
                    communique = "ALZUR SHIELD: " + attacker.get_species_name() + " was pushed away to its original place![" + Integer.toString(old_att_pos.x) +
                        "," + Integer.toString(old_att_pos.y) + "]!";
                    current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
                }
            }
        } else // normal strength comparision
        {
            result = super.collision_course(attacker); // just compare strength
        }
        return result;
    }
}