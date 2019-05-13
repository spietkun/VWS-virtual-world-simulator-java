package plants;
import constvalues.Pict;
import worldsimulator.Plant;
import constvalues.Position;
import constvalues.Org;
import worldsimulator.World;

/**
 *
 * @author salam
 */
public class Grass extends Plant {

    public Grass(Position _pos, World _current_world, int _strength)  {
        super(_pos, _current_world, _strength, Org.GENERAL_PLANT_INIT, Org.GRASS_SYMBOL, Org.GRASS_MATURE_AGE, Org.GRASS_NAME, Org.GRASS_PROPAGATION_CHANCE, Org.GRASS_DEATH_AGE);
    }
    
    public Grass(Position _pos, World _current_world)  {
        super(_pos, _current_world, Org.GRASS_BASIC_STR, Org.GENERAL_PLANT_INIT, Org.GRASS_SYMBOL, Org.GRASS_MATURE_AGE, Org.GRASS_NAME, Org.GRASS_PROPAGATION_CHANCE, Org.GRASS_DEATH_AGE);
    }

    @Override
    public String draw() {
        return Pict.GRASS_ICON;
    }

    @Override
    public void create_a_descendant(Position new_pos)  {
        current_world.get_organisms()[new_pos.y][new_pos.x] = new Grass(new_pos, current_world, Org.GRASS_BASIC_STR);
    }
}