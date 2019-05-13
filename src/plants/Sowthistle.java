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
public class Sowthistle extends Plant {

    public Sowthistle(Position _pos, World _current_world, int _strength)  {
        super(_pos, _current_world, _strength, Org.GENERAL_PLANT_INIT, Org.SOWTHISTLE_SYMBOL, Org.SOWTHISTLE_MATURE_AGE, Org.SOWTHISTLE_NAME, Org.SOWTHISTLE_PROPAGATION_CHANCE, Org.SOWTHISTLE_DEATH_AGE);
    }
    
    public Sowthistle(Position _pos, World _current_world)  {
        super(_pos, _current_world, Org.SOWTHISTLE_BASIC_STR, Org.GENERAL_PLANT_INIT, Org.SOWTHISTLE_SYMBOL, Org.SOWTHISTLE_MATURE_AGE, Org.SOWTHISTLE_NAME, Org.SOWTHISTLE_PROPAGATION_CHANCE, Org.SOWTHISTLE_DEATH_AGE);
    }

    @Override
    public String draw() {
        return Pict.SOWTHISTLE_ICON;
    }

    @Override
    public void create_a_descendant(Position new_pos)  {
        current_world.get_organisms()[new_pos.y][new_pos.x] = new Sowthistle(new_pos, current_world, Org.SOWTHISTLE_BASIC_STR);
    }

    @Override
    public int get_general_propagation_am() {
        return Org.SOWTHISTLE_PROPAGATION_AMOUNT;
    }
}