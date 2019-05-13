package gui;
import constvalues.Board;
import java.awt.event.*;
import constvalues.Intwrap;
import constvalues.Raport;
import javax.swing.JTextField;
import worldsimulator.World;
/**
 *
 * @author salam
 */
public class Action_Load implements ActionListener {

    private World world_ref;
    public Action_Load(World world) {
        world_ref = world;
    }

    public void actionPerformed(ActionEvent e) {

        world_ref.open_file(Raport.F_SAVE_STATE);
        if ((world_ref.get_save_file()).length() == 0) {
            world_ref.close_file(Raport.F_SAVE_STATE);
            
            world_ref.open_file(Raport.F_ROUND_RAPORT);
            world_ref.write_to_file("The save file is empty. Save a file first.", Raport.F_ROUND_RAPORT);
            world_ref.close_file(Raport.F_ROUND_RAPORT);
            world_ref.open_file_to_read(Raport.F_ROUND_RAPORT);
            world_ref.read_from_file(Raport.F_ROUND_RAPORT);
            world_ref.close_file(Raport.F_ROUND_RAPORT);

            return;
        }
        world_ref.close_file(Raport.F_SAVE_STATE);
        
        world_ref.open_and_clear_file(Raport.F_ROUND_RAPORT);
        world_ref.load_world_state(world_ref);
        
        world_ref.close_file(Raport.F_SAVE_STATE);
        world_ref.open_file_to_read(Raport.F_ROUND_RAPORT);
        world_ref.read_from_file(Raport.F_ROUND_RAPORT);
        world_ref.close_file(Raport.F_SAVE_STATE);
        
        world_ref.drawWorld();
        return;
    }
}