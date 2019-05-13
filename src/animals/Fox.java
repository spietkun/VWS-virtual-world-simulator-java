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
public class Fox extends Animal {
    public Fox(Position _pos, World _current_world, int _strength, int _gender)  {
        super(_pos, _current_world, _strength, Org.FOX_BASIC_INIT, Org.FOX_SYMBOL, Org.FOX_MATURE_AGE, Org.FOX_NAME, Org.FOX_PROPAGATION_CHANCE, Org.FOX_DEATH_AGE,
            Org.GENERAL_STEP_SIZE, Org.GENERAL_CHANCE_OF_MOVING, _gender);
    }
    
    public Fox(Position _pos, World _current_world)  {
        super(_pos, _current_world, Org.FOX_BASIC_STR, Org.FOX_BASIC_INIT, Org.FOX_SYMBOL, Org.FOX_MATURE_AGE, Org.FOX_NAME, Org.FOX_PROPAGATION_CHANCE, Org.FOX_DEATH_AGE,
            Org.GENERAL_STEP_SIZE, Org.GENERAL_CHANCE_OF_MOVING);
    }

    @Override
    public void create_a_descendant(Position new_pos)  {
        current_world.get_organisms()[new_pos.y][new_pos.x] = new Fox(new_pos, current_world);
    }

    @Override
    public String draw() {
        //System.out.println(Colours.FOX_ICON);
        return Pict.FOX_ICON;
    }

    @Override
    public int get_smell_skill() {
        return Org.AVOID_STR_SMELL_SKILL;
    }
}