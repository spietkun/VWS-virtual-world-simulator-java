package plants;
import worldsimulator.Plant;
import constvalues.Position;
import constvalues.Org;
import constvalues.Pict;
import worldsimulator.World;
/**
 *
 * @author salam
 */
public class Belladonna extends Plant {

    public Belladonna(Position _pos, World _current_world, int _strength)  {
        super(_pos, _current_world, _strength, Org.GENERAL_PLANT_INIT, Org.BELLADONNA_SYMBOL, Org.BELLADONNA_MATURE_AGE, Org.BELLADONNA_NAME, Org.BELLADONNA_PROPAGATION_CHANCE, Org.BELLADONNA_DEATH_AGE);
    }
    
    public Belladonna(Position _pos, World _current_world)  {
        super(_pos, _current_world, Org.BELLADONNA_BASIC_STR, Org.GENERAL_PLANT_INIT, Org.BELLADONNA_SYMBOL, Org.BELLADONNA_MATURE_AGE, Org.BELLADONNA_NAME, Org.BELLADONNA_PROPAGATION_CHANCE, Org.BELLADONNA_DEATH_AGE);
    }

    @Override
    public String draw() {
        return Pict.BELLADONNA_ICON;
    }

    @Override
    public void create_a_descendant(Position new_pos)  {
        current_world.get_organisms()[new_pos.y][new_pos.x] = new Belladonna(new_pos, current_world, Org.BELLADONNA_BASIC_STR);
    }
}