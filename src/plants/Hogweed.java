package plants;
import constvalues.Pict;
import constvalues.Intwrap;
import constvalues.Move;
import worldsimulator.Plant;
import constvalues.Position;
import constvalues.Org;
import constvalues.Raport;
import worldsimulator.Animal;
import worldsimulator.Organism;
import worldsimulator.World;

/**
 *
 * @author salam
 */
public class Hogweed extends Plant {

    private void kill_surrounding_animals(int direction)  // conditions, if directions dont exceed outside the board, must be checked
// before using this function
{
	String communique;
        Organism[][] buf = current_world.get_organisms();
	
        Animal attck_to_animal = null; // attacker is an animal (plants dont initialize attack)
        
        
        
	Position animal_pos = new Position(0,0);
	switch (direction)
	{
	case Move.LEFT:
		if (buf[pos.y][pos.x - Org.HOGWEED_KILLING_ZONE] instanceof Animal) {
            attck_to_animal = (Animal) buf[pos.y][pos.x - Org.HOGWEED_KILLING_ZONE];
        } 
		if (attck_to_animal != null) // kill only animals
		{
			animal_pos = attck_to_animal.get_position();
			communique = "A plant (" + species_name + ")" + " A[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) +
				"]" + " killed animal on the left: " + "(" + attck_to_animal.get_species_name() + ")" + "-" + 
				 " D[" + Integer.toString(animal_pos.x) + "," + Integer.toString(animal_pos.y) + "]";
			current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
			buf[pos.y][pos.x - Org.HOGWEED_KILLING_ZONE].annihilate();
		}
		break;
	case Move.RIGHT:
                if (buf[pos.y][pos.x + Org.HOGWEED_KILLING_ZONE] instanceof Animal) {
            attck_to_animal = (Animal) buf[pos.y][pos.x + Org.HOGWEED_KILLING_ZONE];}
		if (attck_to_animal != null) // kill only animals
		{
			animal_pos = attck_to_animal.get_position();
			communique = "A plant (" + species_name + ")" + " A[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) +
				"]" + " killed animal on the right: " + "(" + attck_to_animal.get_species_name() + ")" + "-" +
				 " D[" + Integer.toString(animal_pos.x) + "," + Integer.toString(animal_pos.y) + "]";
			current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
			buf[pos.y][pos.x + Org.HOGWEED_KILLING_ZONE].annihilate();
		}
		break;
	case Move.UP:
            if (buf[pos.y - Org.HOGWEED_KILLING_ZONE][pos.x] instanceof Animal) {
            attck_to_animal = (Animal) buf[pos.y - Org.HOGWEED_KILLING_ZONE][pos.x];}
		if (attck_to_animal != null) // kill only animals
		{
			animal_pos = attck_to_animal.get_position();
			communique = "A plant (" + species_name + ")" + " A[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) +
				"]" + " killed animal on the top: " + "(" + attck_to_animal.get_species_name() + ")" + "-"
				+ " D[" + Integer.toString(animal_pos.x) + "," + Integer.toString(animal_pos.y) + "]";
			current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
			buf[pos.y - Org.HOGWEED_KILLING_ZONE][pos.x].annihilate();
		}
		break;
	case Move.DOWN:
                if (buf[pos.y + Org.HOGWEED_KILLING_ZONE][pos.x] instanceof Animal) {
            attck_to_animal = (Animal) buf[pos.y + Org.HOGWEED_KILLING_ZONE][pos.x];}
		if (attck_to_animal != null) // kill only animals
		{
			animal_pos = attck_to_animal.get_position();
			communique = "A plant (" + species_name + ")" + " A[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) +
				"]" + " killed animal on the bottom: " + "(" + attck_to_animal.get_species_name() + ")" + "-" +
				" D[" + Integer.toString(animal_pos.x) + "," + Integer.toString(animal_pos.y) + "]";
			current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
			buf[pos.y + Org.HOGWEED_KILLING_ZONE][pos.x].annihilate();
		}
		break;
	}
}
    
    public Hogweed(Position _pos, World _current_world, int _strength) {
        super(_pos, _current_world, _strength, Org.GENERAL_PLANT_INIT, Org.HOGWEED_SYMBOL, Org.HOGWEED_MATURE_AGE, Org.HOGWEED_NAME, Org.HOGWEED_PROPAGATION_CHANCE, Org.HOGWEED_DEATH_AGE);
    }
    
    public Hogweed(Position _pos, World _current_world)  {
        super(_pos, _current_world, Org.HOGWEED_BASIC_STR, Org.GENERAL_PLANT_INIT, Org.HOGWEED_SYMBOL, Org.HOGWEED_MATURE_AGE, Org.HOGWEED_NAME, Org.HOGWEED_PROPAGATION_CHANCE, Org.HOGWEED_DEATH_AGE);
    }

    @Override
    public String draw() {
        return Pict.HOGWEED_ICON;
    }

    @Override
    public void create_a_descendant(Position new_pos) {
        current_world.get_organisms()[new_pos.y][new_pos.x] = new Hogweed(new_pos, current_world, Org.HOGWEED_BASIC_STR);
    }
    
    @Override
    public void action()
{
	String communique;
	if (age >= death_age)
	{
		communique = "Plant (" + species_name + ")" + "[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) +
			"]" + " died of old age :(" + '\n';
		(current_world.get_organisms())[pos.y][pos.x].annihilate();
		current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
	}
	else // if Hogweed is alive
	{
		if (plant_propagation())
		{
			//cout << "Hogweed sie rozmnozyla." << endl;
		}
		//else cout << "Hogweed nie dojrzala" << endl;
                
                if (age > maturity_age){
		int[] directions = new int[Move.NUMBER_OF_DIRECTIONS]; // all directions
		directions[0] = Org.FAILURE; //assume, that animal can go nowhere
		directions[1] = Org.FAILURE;
		directions[2] = Org.FAILURE;
		directions[3] = Org.FAILURE;
		Intwrap amount_of_poss_dir = new Intwrap(0);

		check_possible_directions(directions, amount_of_poss_dir, Org.HOGWEED_KILLING_ZONE);
		for (int i = 0; i < Move.NUMBER_OF_DIRECTIONS; i++)
		{
			if (directions[i] != Org.FAILURE)
			{
				kill_surrounding_animals(directions[i]);
			}
		}}
                else {
                //System.out.println("Hogweed nie dojrzała do zabijania");
                }
	}
}
}