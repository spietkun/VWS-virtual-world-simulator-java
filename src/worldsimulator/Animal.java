package worldsimulator;

import constvalues.Org;
import constvalues.Move;
import constvalues.Raport;
import constvalues.Position;
import constvalues.Intwrap;
import java.util.Random;
/**
 *
 * @author salam
 */
public abstract class Animal extends Organism {
    //PROTECTED

    protected final int step_size;
    protected final int chance_of_moving;

    public int get_step_size() {
        return step_size;
    }

    //PUBLIC 
    public Animal(Position _pos, World _current_world, int _strength, int _initiative, char _symbol, int _maturity_age,
        String _species_name, int _propagation_chance, int _death_age, int _step_size, int _chance_of_moving) {
        super(_pos, _current_world, _strength, _initiative, _symbol, _maturity_age, _species_name, _propagation_chance, _death_age);
        step_size = _step_size;
        chance_of_moving = _chance_of_moving;
        Random rand = new Random();
        int draw_gender = rand.nextInt(2); // 2 genders
        switch (draw_gender) {
            case Org.MALE:
                gender = Org.MALE;
                break;
            case Org.FEMALE:
                gender = Org.FEMALE;
                break;
        }
        String born_communique = "An animal was born: " + return_organism_info_();
        current_world.write_to_file(born_communique, Raport.F_ROUND_RAPORT);
    }
    
    public Animal(Position _pos, World _current_world, int _strength, int _initiative, char _symbol, int _maturity_age,
        String _species_name, int _propagation_chance, int _death_age, int _step_size, int _chance_of_moving, int _gender) {
        super(_pos, _current_world, _strength, _initiative, _symbol, _maturity_age, _species_name, _propagation_chance, _death_age);
        step_size = _step_size;
        chance_of_moving = _chance_of_moving;
        gender = _gender;
        String born_communique = "An animal was born: " + return_organism_info_();
        current_world.write_to_file(born_communique, Raport.F_ROUND_RAPORT);
    }

    @Override
    public void action() {
        Random rand = new Random();
        boolean if_animal_moves = (rand.nextInt(100)) < chance_of_moving; // chance that animal will move
        //System.out.print(if_animal_moves);
        String communique;
        if (age >= death_age) {
            communique = "Animal (" + species_name + ")" + "[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) +
                "]" + " died of old age :(";
            (current_world.get_organisms())[pos.y][pos.x].annihilate();
            current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
        } else if (if_animal_moves) {
            Position new_pos = new Position(0, 0);
            int[] directions = new int[Move.NUMBER_OF_DIRECTIONS]; // all directions
            directions[0] = Org.FAILURE; //assume, that animal can go nowhere
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
                if (current_world.get_organisms()[new_pos.y][new_pos.x] == null) {
                    change_position(new_pos);
                } else {
                    (current_world.get_organisms()[new_pos.y][new_pos.x]).collision(this); // defender calls collision() 
                    //and attacker information (this) is sent
                }
            }
            //else //cout << "No place for moving!" << endl;
        } else {
            /*communique = "Animal (" + species_name + ")" + "[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) +
                "]" + " doesn't want to move";
            current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);*/
        }
    }

    @Override
    public void collision(Organism attacker) {
        Position att_pos = attacker.get_position();
        String communique;
        if (get_symbol() == attacker.get_symbol() && (get_gender() != attacker.get_gender())) // BREED when animals are of the same
        //type and of other genders
        {
            boolean is_defender_mature = is_organism_mature();
            boolean is_attacker_mature = attacker.is_organism_mature();
            if (is_defender_mature && is_attacker_mature) {
                communique = "Animals of the same species (" + species_name + ")" + " - " + "D[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) +
                    "]" + " A[" + Integer.toString(att_pos.x) + "," + Integer.toString(att_pos.y) + "]" + " - " + "met and try to BREED.";
                current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);

                breed_next_to_parent(attacker); // place a new animal next to mother or if there's no place next to its father
            } else {
                communique = "Animals of the same species (" + species_name + ")" + " - " + "D[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) +
                    "]" + " A[" + Integer.toString(att_pos.x) + "," + Integer.toString(att_pos.y) + "]" + " - " + "met but at least one of them is not mature.";
                current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
            }

        } else if (get_symbol() == attacker.get_symbol()) // DO NOTHING - two animals of the same sex met
        {
            communique = "Animals of the same species (" + species_name + ")" + " - " + "D[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) +
                "]" + " A[" + Integer.toString(att_pos.x) + "," + Integer.toString(att_pos.y) + "]" + " - " + "met but they are of the same SEX.";
            current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
        } else // FIGHT - two animals of different species met
        {
            /*communique = "Animals of different species: " + species_name + " D[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) +
                "]" + " str=" + Integer.toString(get_strength()) + " and " + attacker.get_species_name() + " A[" + Integer.toString(att_pos.x) + "," +
                Integer.toString(att_pos.y) + "]" + " str=" + Integer.toString(attacker.get_strength()) + " - " + "met and they started to FIGHT!";
            current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);*/

            // attacker is an animal (plants dont initialize attack) so instanceof is used
            Animal attck_to_animal = null;
            if (attacker instanceof Animal) {
                attck_to_animal = (Animal) attacker;

            }

            /*int fight_result = collision_course(attacker);
            switch (fight_result) {
                case Move.DEFENDER_WON:
                    if (attck_to_animal != null) {
                        attck_to_animal.annihilate();
                        communique = "The winner is: defender - " + species_name + "!";
                        current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
                    }
                    break;
                case Move.DRAW: // The attacking animal wins
                    communique = "DRAW! The same strength! But the attcker has an advantage";
                    current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
                    //do the next case
                case Move.ATTACKER_WON:
                    if (attck_to_animal != null) {
                        annihilate();
                        attck_to_animal.change_position(pos); // attacker takes place of the defender
                        communique = "The winner is: attacker - " + attacker.get_species_name() + "!";
                        current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
                    }
                    break;
            }*/
            super.collision(attck_to_animal);
        }
    }

    
    
    protected int get_chance_of_moving() {
        return chance_of_moving;
    }

    protected void breed_next_to_parent(Organism attacker) {
        String communique;
        boolean if_attacker_is_father = false;
        int breeding_result;
        // first, look for a place for a small animal next to its mother //
        if (get_gender() == Org.FEMALE) {
            breeding_result = multiplication(); // defender is a mother
            if_attacker_is_father = true;
        } else {
            breeding_result = attacker.multiplication(); // attacker is a mother
            if_attacker_is_father = false;
        }

        if (breeding_result == Org.NO_PLACE_FOR_A_DESCENDANT) // if there was no place next to small animal's mother, place it next to its father
        {
            if (if_attacker_is_father) breeding_result = attacker.multiplication();
            else breeding_result = multiplication();
        }

        //COMMUNIQUES
        if (breeding_result == Org.NO_PLACE_FOR_A_DESCENDANT) {
            communique = "Multiplication attemp of (" + get_species_name() + "s) wasn't succesful - no place for a descendant. \n";
            current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
        } else if (breeding_result == Org.NOT_ENOUGH_LUCK_FOR_BREEDING) {
            communique = "Multiplication attemp of (" + get_species_name() + "s) wasn't succesful - maybe next time. \n";
            current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
        }
    }
    
    @Override
    protected String collision_communique(Organism attacker){
 
        Position att_pos = attacker.get_position();
        String communique;
        communique = communique = "Organisms of different spieces met: " + species_name + " D[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) +
                "]" + " str=" + Integer.toString(get_strength()) + " and " + attacker.get_species_name() + " A[" + Integer.toString(att_pos.x) + "," +
                Integer.toString(att_pos.y) + "]" + " str=" + Integer.toString(attacker.get_strength()) + " - " + "met and they started to FIGHT!";
        
        return communique;
    }

}