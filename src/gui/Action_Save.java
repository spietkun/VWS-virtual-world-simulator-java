package gui;
import java.awt.event.*;
import constvalues.Intwrap;
import javax.swing.JTextField;
import worldsimulator.World;
/**
 *
 * @author salam
 */
public class Action_Save implements ActionListener {

    private World world_ref;
    public Action_Save(World world) {
        world_ref = world;
    }

    public void actionPerformed(ActionEvent e) {
        world_ref.save_world_state();
    }
}