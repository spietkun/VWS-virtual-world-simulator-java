package plants;
import constvalues.Pict;
import worldsimulator.Organism;
import worldsimulator.Plant;
import constvalues.Position;
import constvalues.Move;
import constvalues.Org;
import worldsimulator.Animal;
import worldsimulator.World;

/**
 *
 * @author salam
 */
public class Guarana extends Plant {

    private int power;
    public Guarana(Position _pos, World _current_world, int _strength)  {
        super(_pos, _current_world, _strength, Org.GENERAL_PLANT_INIT, Org.GUARANA_SYMBOL, Org.GUARANA_MATURE_AGE, Org.GUARANA_NAME, Org.GUARANA_PROPAGATION_CHANCE, Org.GUARANA_DEATH_AGE);
        power = Org.GUARANA_POWER;
    }
    
    public Guarana(Position _pos, World _current_world)  {
        super(_pos, _current_world, Org.GUARANA_BASIC_STR, Org.GENERAL_PLANT_INIT, Org.GUARANA_SYMBOL, Org.GUARANA_MATURE_AGE, Org.GUARANA_NAME, Org.GUARANA_PROPAGATION_CHANCE, Org.GUARANA_DEATH_AGE);
        power = Org.GUARANA_POWER;
    }

    @Override
    public String draw() {
        return Pict.GUARANA_ICON;
    }

    @Override
    public void create_a_descendant(Position new_pos)  {
        current_world.get_organisms()[new_pos.y][new_pos.x] = new Guarana(new_pos, current_world, Org.GUARANA_BASIC_STR);
    }

    @Override
    public int collision_course(Organism attacker)  {
        Animal attck_to_animal = null; // attacker is an animal (plants dont initialize attack)
        if (attacker instanceof Animal) {
            attck_to_animal = (Animal) attacker;
        } // attacker is an animal (plants dont initialize attack)
        if (attck_to_animal != null) {
            attck_to_animal.increase_strength(power);
        }
        return Move.ATTACKER_WON;
    }
}