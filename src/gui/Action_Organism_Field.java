package gui;
import constvalues.Board;
import java.awt.event.*;
import constvalues.Intwrap;
import constvalues.Org;
import constvalues.Raport;
import javax.swing.JTextField;
import worldsimulator.World;
/**
 *
 * @author salam
 */
public class Action_Organism_Field implements ActionListener{

    private World world_ref;
    int org_y;
    int org_x;
    public Action_Organism_Field(World world, int _org_y, int _org_x) {
        world_ref = world;
        org_y = _org_y;
        org_x = _org_x;
        
    }
    public void actionPerformed(ActionEvent e) {  
         if (world_ref.get_organisms()[org_y][org_x] == null){
        Frame org_chooser_frame = new Frame(300, 600, "Choose and organism");
                    org_chooser_frame.setVisible(true);
                    
                     int button_pos_x = 0;
        /*Button_Org_chooser org_1 = new Button_Org_chooser(org_chooser_frame, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, Org.WOLF_SYMBOL, world_ref, org_y, org_x);
	button_pos_x += Board.MENU_BUTTON_WIDTH + Board.BUTTON_GAP;
        Button_Org_chooser org_2 = new Button_Org_chooser(org_chooser_frame, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, Org.SHEEP_SYMBOL, world_ref, org_y, org_x);
        button_pos_x += Board.MENU_BUTTON_WIDTH + Board.BUTTON_GAP;
        Button_Org_chooser org_3 = new Button_Org_chooser(org_chooser_frame, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, Org.FOX_SYMBOL, world_ref, org_y, org_x);
        button_pos_x += Board.MENU_BUTTON_WIDTH + Board.BUTTON_GAP;
        Button_Org_chooser org_4 = new Button_Org_chooser(org_chooser_frame, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, Org.TURTLE_SYMBOL, world_ref, org_y, org_x);
        button_pos_x += Board.MENU_BUTTON_WIDTH + Board.BUTTON_GAP;
        Button_Org_chooser org_5 = new Button_Org_chooser(org_chooser_frame, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, Org.ANTELOPE_SYMBOL, world_ref, org_y, org_x);
        button_pos_x += Board.MENU_BUTTON_WIDTH + Board.BUTTON_GAP;
        Button_Org_chooser org_6 = new Button_Org_chooser(org_chooser_frame, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, Org.GRASS_SYMBOL, world_ref, org_y, org_x);
        button_pos_x += Board.MENU_BUTTON_WIDTH + Board.BUTTON_GAP;
        Button_Org_chooser org_7 = new Button_Org_chooser(org_chooser_frame, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, Org.SOWTHISTLE_SYMBOL, world_ref, org_y, org_x);
        button_pos_x += Board.MENU_BUTTON_WIDTH + Board.BUTTON_GAP;
        Button_Org_chooser org_8 = new Button_Org_chooser(org_chooser_frame, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, Org.GUARANA_SYMBOL, world_ref, org_y, org_x);
        button_pos_x += Board.MENU_BUTTON_WIDTH + Board.BUTTON_GAP;
        Button_Org_chooser org_9 = new Button_Org_chooser(org_chooser_frame, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, Org.BELLADONNA_SYMBOL, world_ref, org_y, org_x);
        button_pos_x += Board.MENU_BUTTON_WIDTH + Board.BUTTON_GAP;
        Button_Org_chooser org_10 = new Button_Org_chooser(org_chooser_frame, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, Org.HOGWEED_SYMBOL, world_ref, org_y, org_x);
        button_pos_x += Board.MENU_BUTTON_WIDTH + Board.BUTTON_GAP;
        Button_Org_chooser org_11 = new Button_Org_chooser(org_chooser_frame, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, Org.HUMAN_SYMBOL, world_ref, org_y, org_x);*/
        world_ref.drawWorld();
    }}
}
