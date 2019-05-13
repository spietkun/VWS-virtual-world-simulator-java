package animals;
import constvalues.Pict;
import worldsimulator.Animal;
import worldsimulator.Organism;
import constvalues.Position;
import constvalues.Org;
import constvalues.Raport;
import constvalues.Move;
import worldsimulator.World;

/**
 *
 * @author salam
 */
public class Turtle extends Animal {

    public Turtle(Position _pos, World _current_world, int _strength, int _gender)  {
        super(_pos, _current_world, _strength, Org.TURTLE_BASIC_INIT, Org.TURTLE_SYMBOL, Org.TURTLE_MATURE_AGE, Org.TURTLE_NAME, Org.TURTLE_PROPAGATION_CHANCE, Org.TURTLE_DEATH_AGE,
            Org.GENERAL_STEP_SIZE, Org.TURTLE_CHANCE_OF_MOVING, _gender);
    }
    
    public Turtle(Position _pos, World _current_world)  {
        super(_pos, _current_world, Org.TURTLE_BASIC_STR, Org.TURTLE_BASIC_INIT, Org.TURTLE_SYMBOL, Org.TURTLE_MATURE_AGE, Org.TURTLE_NAME, Org.TURTLE_PROPAGATION_CHANCE, Org.TURTLE_DEATH_AGE,
            Org.GENERAL_STEP_SIZE, Org.TURTLE_CHANCE_OF_MOVING);
    }

    @Override
    public void create_a_descendant(Position new_pos)  {
        current_world.get_organisms()[new_pos.y][new_pos.x] = new Turtle(new_pos, current_world);
    }

    @Override
    public String draw() {
        return Pict.TURTLE_ICON;
    }
    @Override
    public int collision_course(Organism attacker)  {
        String communique;
        int attacker_str = attacker.get_strength();
        int result = Move.INDIVIDUAL;
        Animal attck_to_animal = null; // attacker is an animal (plants dont initialize attack)
        if (attacker instanceof Animal) {
            attck_to_animal = (Animal) attacker;
        }

        if (attck_to_animal != null) {
            communique = get_species_name() + " reflected an attack! ";
            if (attacker_str < Org.TURTLE_REFLECTION_CONDITION) {
                result = Move.REFLECTION;
            } else {
                communique = get_species_name() + " couldn't reflect an attack.";
                result = super.collision_course(attacker); // just compare strength
            }
            current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
        }

        return result;
    }
}