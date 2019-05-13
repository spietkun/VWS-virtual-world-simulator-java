package animals;
import constvalues.Pict;
import worldsimulator.Animal;
import constvalues.Position;
import constvalues.Org;
import worldsimulator.World;
/**
 *
 * @author salam
 */
public class Sheep extends Animal {
    public Sheep(Position _pos, World _current_world, int _strength, int _gender)  {
        super(_pos, _current_world, _strength, Org.SHEEP_BASIC_INIT, Org.SHEEP_SYMBOL, Org.SHEEP_MATURE_AGE, Org.SHEEP_NAME, Org.SHEEP_PROPAGATION_CHANCE, Org.SHEEP_DEATH_AGE,
            Org.GENERAL_STEP_SIZE, Org.GENERAL_CHANCE_OF_MOVING, _gender);
    }
    
    public Sheep(Position _pos, World _current_world)  {
        super(_pos, _current_world, Org.SHEEP_BASIC_STR, Org.SHEEP_BASIC_INIT, Org.SHEEP_SYMBOL, Org.SHEEP_MATURE_AGE, Org.SHEEP_NAME, Org.SHEEP_PROPAGATION_CHANCE, Org.SHEEP_DEATH_AGE,
            Org.GENERAL_STEP_SIZE, Org.GENERAL_CHANCE_OF_MOVING);
    }
    
    @Override
    public void create_a_descendant(Position new_pos)  {
        current_world.get_organisms()[new_pos.y][new_pos.x] = new Sheep(new_pos, current_world);
    }
    @Override
    public String draw() {
        return Pict.SHEEP_ICON;
    }
}