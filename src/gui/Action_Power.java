package gui;
import java.awt.event.*;
import constvalues.Intwrap;
import constvalues.Org;
import constvalues.Power;
import constvalues.Raport;
import javax.swing.JTextField;
import worldsimulator.World;
/**
 *
 * @author salam
 */
public class Action_Power implements ActionListener {

    private World world_ref;
    public Action_Power(World world) {
        world_ref = world;
    }

    public void actionPerformed(ActionEvent e) {
        Power buf_alzur = world_ref.get_alzur_power();
        world_ref.open_file(Raport.F_ROUND_RAPORT);

        if (buf_alzur.is_power_on == false && buf_alzur.pause == 0) // When you can turn alzur on
        {
            world_ref.set_alzur_power(true, Org.HUMAN_ALZUR_SHIELD_DURATION, Org.HUMAN_ALZUR_SHIELD_BREAK);
            world_ref.write_to_file("Alzur shield has been activated. It will last for: " + world_ref.get_alzur_duration() + " rounds .", Raport.F_ROUND_RAPORT);
        } else if (buf_alzur.is_power_on == true) // when power has been already turned
        {
            world_ref.write_to_file("Alzur shield has already been picked!: " + world_ref.get_alzur_duration() + " rounds left.", Raport.F_ROUND_RAPORT);
        } else // when there is a pause from choosing power again
        {
            world_ref.write_to_file("Alzur shield has been chosen lately. Relax and wait " + world_ref.get_alzur_pause() + " rounds.", Raport.F_ROUND_RAPORT);
        }

        world_ref.close_file(Raport.F_ROUND_RAPORT);
        world_ref.open_file_to_read(Raport.F_ROUND_RAPORT);
        world_ref.read_from_file(Raport.F_ROUND_RAPORT);
        world_ref.close_file(Raport.F_SAVE_STATE);
    }
}