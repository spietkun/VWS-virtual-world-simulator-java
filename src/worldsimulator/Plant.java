package worldsimulator;
import constvalues.Org;
import constvalues.Move;
import constvalues.Raport;
import constvalues.Position;

/**
 *
 * @author salam
 */
public abstract class Plant extends Organism {

    public Plant(Position _pos, World _current_world, int _strength, int _initiative, char _symbol, int _maturity_age,
        String _species_name, int _propagation_chance, int _death_age)  {
        super(_pos, _current_world, _strength, Org.GENERAL_PLANT_INIT, _symbol, _maturity_age, _species_name, _propagation_chance, _death_age);
        gender = Org.PERFECT;
        String born_communique = "A plant was sowed: " + return_organism_info_();
        current_world.write_to_file(born_communique, Raport.F_ROUND_RAPORT);
    }

    @Override
    public void action() {
        String communique;
        if (age >= death_age) {
            communique = "Plant (" + species_name + ")" + "[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) +
                "]" + " died of old age :(";
            (current_world.get_organisms())[pos.y][pos.x].annihilate();
            current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
        } else if (plant_propagation()) {
            //cout << "Roslina sie rozmnozyla." << endl;
        }
        //else cout << "Roslina nie dojrzala" << endl;
    }

    public boolean plant_propagation() {
        int number_of_reproduction = get_general_propagation_am();
        boolean if_mature = is_organism_mature();
        if (if_mature) {
            for (int i = 0; i < number_of_reproduction; i++) {
                multiplication();
            }
        }
        return if_mature;
    }

    public int get_general_propagation_am() {
        return Org.GENERAL_PROPAGATION_AMOUNT;
    }

    /*@Override
    public void collision(Organism attacker) {
        Animal attck_to_animal = null; // attacker is an animal (plants dont initialize attack)
        if (attacker instanceof Animal) {
            attck_to_animal = (Animal) attacker;
        } // attacker is an animal (plants dont initialize attack)
        Position att_pos = attacker.get_position();
        String communique;

        int fight_result = collision_course(attacker);
        switch (fight_result) {
            case Move.DEFENDER_WON:
                if (attck_to_animal != null) {
                    attck_to_animal.annihilate();
                    communique = "An animal (" + attck_to_animal.get_species_name() + ")" + " - " + "A[" + Integer.toString(att_pos.x) + "," + Integer.toString(att_pos.y) +
                        "]" + " stepped on the plant: (" + species_name + ")" + " D[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) + "]" + " - " + "and ANIMAL DIED becasue of eating it.";
                    current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
                }
                break;
            case Move.ATTACKER_WON:
                if (attck_to_animal != null) {
                    //annihilate(); zrobić destruktor
                    attck_to_animal.change_position(pos); // attacker takes place of the defender
                    communique = "An animal (" + attck_to_animal.get_species_name() + ")" + " - " + "A[" + Integer.toString(att_pos.x) + "," + Integer.toString(att_pos.y) +
                        "]" + " stepped on the plant: (" + species_name + ") D[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) + "]" + " - " + "and ANIMAL is HAPPY becasue of eating it.";
                    current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
                    //cout << "This plant was eaten and an animal is happy now." << endl;
                }
                break;
            case Move.DRAW:
                communique = "An animal (" + attck_to_animal.get_species_name() + ")" + " - " + "A[" + Integer.toString(att_pos.x) + "," + Integer.toString(att_pos.y) +
                    "]" + " stepped on the plant: (" + species_name + ") D[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) + "]" + " - " + "and they are both alive becasue of the same strength.";
                current_world.write_to_file(communique, Raport.F_ROUND_RAPORT);
                break;

        }
    }*/
    
    @Override
    protected String collision_communique(Organism attacker){
 
        Position att_pos = attacker.get_position();
        String communique;
        communique = communique = communique = "An animal (" + attacker.get_species_name() + ")" + " - " + "A[" + Integer.toString(att_pos.x) + "," + Integer.toString(att_pos.y) +
                        "]" + " stepped on the plant: (" + species_name + ")" + " D[" + Integer.toString(pos.x) + "," + Integer.toString(pos.y) + "]" + " - ";
        
        return communique;
    }
}