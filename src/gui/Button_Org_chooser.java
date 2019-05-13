package gui;
import animals.Antelope;
import animals.Fox;
import animals.Human;
import animals.Sheep;
import animals.Turtle;
import animals.Wolf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import worldsimulator.World;
import constvalues.Org;
import constvalues.Position;
import plants.Belladonna;
import plants.Grass;
import plants.Guarana;
import plants.Hogweed;
import plants.Sowthistle;
import java.awt.event.*;

/**
 *
 * @author salam
 */
public class Button_Org_chooser extends Butt {
    private int org_y; // coord in array
    private int org_x;
    public Button_Org_chooser(Frame _frame, int _size_y, int _size_x, int _pos_y, int _pos_x, char animal_symbol, World _world, int org_y, int org_x, String _icon_path) {
        super(_frame, _size_y, _size_x, _pos_y, _pos_x, Character.toString(animal_symbol), _world, _icon_path);
        this.org_y = org_y;
        this.org_x = org_x;
        Position org_pos = new Position(org_y, org_x);
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (animal_symbol == Org.WOLF_SYMBOL) world.get_organisms()[org_y][org_x] = new Wolf(org_pos, world);
                else if (animal_symbol == Org.SHEEP_SYMBOL) world.get_organisms()[org_y][org_x] = new Sheep(org_pos, world);
                else if (animal_symbol == Org.FOX_SYMBOL) world.get_organisms()[org_y][org_x] = new Fox(org_pos, world);
                else if (animal_symbol == Org.TURTLE_SYMBOL) world.get_organisms()[org_y][org_x] = new Turtle(org_pos, world);
                else if (animal_symbol == Org.ANTELOPE_SYMBOL) world.get_organisms()[org_y][org_x] = new Antelope(org_pos, world);
                else if (animal_symbol == Org.HUMAN_SYMBOL) world.get_organisms()[org_y][org_x] = new Human(org_pos, world);
                else if (animal_symbol == Org.GRASS_SYMBOL) world.get_organisms()[org_y][org_x] = new Grass(org_pos, world, Org.GRASS_BASIC_STR);
                else if (animal_symbol == Org.SOWTHISTLE_SYMBOL) world.get_organisms()[org_y][org_x] = new Sowthistle(org_pos, world, Org.SOWTHISTLE_BASIC_STR);
                else if (animal_symbol == Org.GUARANA_SYMBOL) world.get_organisms()[org_y][org_x] = new Guarana(org_pos, world, Org.GUARANA_BASIC_STR);
                else if (animal_symbol == Org.HOGWEED_SYMBOL) world.get_organisms()[org_y][org_x] = new Hogweed(org_pos, world, Org.HOGWEED_BASIC_STR);
                else if (animal_symbol == Org.BELLADONNA_SYMBOL) world.get_organisms()[org_y][org_x] = new Belladonna(org_pos, world, Org.BELLADONNA_BASIC_STR);
                else if (animal_symbol == Org.HUMAN_SYMBOL) world.get_organisms()[org_y][org_x] = new Human(org_pos, world);
                world.drawWorld();
                _frame.dispatchEvent(new WindowEvent(_frame, WindowEvent.WINDOW_CLOSING));
            }
        });

    }
}