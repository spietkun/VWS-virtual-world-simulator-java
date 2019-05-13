package gui;
import java.awt.event.*;
import constvalues.Intwrap;
import constvalues.Move;
import constvalues.Raport;
import javax.swing.JTextField;
import worldsimulator.World;
/**
 *
 * @author salam
 */
public class Key_Step_Listener implements KeyListener {

    private World world_ref;
    public Key_Step_Listener(World world) {
        world_ref = world;
    }

    public void keyPressed(KeyEvent e) {

    }
    
    public void keyReleased(KeyEvent e) {
        boolean move_key = false; 
        if(e.getKeyCode()== KeyEvent.VK_RIGHT){
            world_ref.set_human_direction(Move.RIGHT);
            move_key = true;}
        else if(e.getKeyCode()== KeyEvent.VK_LEFT){
            world_ref.set_human_direction(Move.LEFT);
            move_key = true;}
        else if(e.getKeyCode()== KeyEvent.VK_DOWN){
            System.out.println(world_ref.get_HEIGHT());
            world_ref.set_human_direction(Move.DOWN);
            move_key = true;}
        else if(e.getKeyCode()== KeyEvent.VK_UP){
            world_ref.set_human_direction(Move.UP);
            move_key = true;}
        
        if (move_key == true){
            world_ref.open_and_clear_file(Raport.F_ROUND_RAPORT);
            world_ref.performRound();
            //System.out.println(world.human_direction);
            world_ref.drawWorld();
            world_ref.close_file(Raport.F_ROUND_RAPORT);
            world_ref.open_file_to_read(Raport.F_ROUND_RAPORT);
            world_ref.read_from_file(Raport.F_ROUND_RAPORT);
        }
    }
    
    public void keyTyped(KeyEvent e) {
    }
}