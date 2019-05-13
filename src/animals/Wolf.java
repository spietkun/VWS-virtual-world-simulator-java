package animals;
import constvalues.Pict;
import worldsimulator.Animal;
import worldsimulator.World;
import constvalues.Position;
import constvalues.Org;

/**
 *
 * @author salam
 */
public class Wolf extends Animal {

    public Wolf(Position _pos, World _current_world, int _strength, int _gender)  {
        super(_pos, _current_world, _strength, Org.WOLF_BASIC_INIT, Org.WOLF_SYMBOL, Org.WOLF_MATURE_AGE, Org.WOLF_NAME, Org.WOLF_PROPAGATION_CHANCE, Org.WOLF_DEATH_AGE,
            Org.GENERAL_STEP_SIZE, Org.GENERAL_CHANCE_OF_MOVING, _gender);
    }
    
    public Wolf(Position _pos, World _current_world)  {
        super(_pos, _current_world, Org.WOLF_BASIC_STR, Org.WOLF_BASIC_INIT, Org.WOLF_SYMBOL, Org.WOLF_MATURE_AGE, Org.WOLF_NAME, Org.WOLF_PROPAGATION_CHANCE, Org.WOLF_DEATH_AGE,
            Org.GENERAL_STEP_SIZE, Org.GENERAL_CHANCE_OF_MOVING);
    }

    @Override
    public void create_a_descendant(Position new_pos)  {
        current_world.get_organisms()[new_pos.y][new_pos.x] = new Wolf(new_pos, current_world);
    }
    
    @Override
    public String draw() {
        return Pict.WOLF_ICON;
    }

}