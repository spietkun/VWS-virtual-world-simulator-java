package gui;
import java.awt.event.*;
import constvalues.Intwrap;
import constvalues.Move;
import constvalues.Opt;
import constvalues.Org;
import constvalues.Power;
import constvalues.Raport;
import javax.swing.JTextField;
import worldsimulator.World;
/**
 *
 * @author salam
 */
public class Action_Step implements ActionListener {

    private World world_ref;
    private int direction;
    public Action_Step(World world, int direction) {
        this.world_ref = world;
        this.direction = direction;
    }

    public void actionPerformed(ActionEvent e) {
        world_ref.open_and_clear_file(Raport.F_ROUND_RAPORT);
        switch (direction) {
            case Opt.MOVE_LEFT:
                world_ref.set_human_direction(Move.LEFT);
                //System.out.println(world.human_direction);
                break;
            case Opt.MOVE_RIGHT:
                world_ref.set_human_direction(Move.RIGHT);
                //System.out.println(world.human_direction);
                break;
            case Opt.MOVE_UP:
                world_ref.set_human_direction(Move.UP);
                //System.out.println(world.human_direction);
                break;
            case Opt.MOVE_DOWN:
                world_ref.set_human_direction(Move.DOWN);
                //System.out.println(world.human_direction);
                break;
        }
        world_ref.performRound();
        //System.out.println(world.human_direction);
        world_ref.drawWorld();
        world_ref.close_file(Raport.F_ROUND_RAPORT);
        world_ref.open_file_to_read(Raport.F_ROUND_RAPORT);
        world_ref.read_from_file(Raport.F_ROUND_RAPORT);
    }
}