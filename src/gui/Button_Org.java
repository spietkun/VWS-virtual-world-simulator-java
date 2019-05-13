package gui;
import constvalues.Board;
import constvalues.Pict;
import java.awt.event.*;
import worldsimulator.World;
import worldsimulator.WorldSimulator;
import constvalues.Intwrap;
import constvalues.Move;
import constvalues.Org;
import constvalues.Position;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
/**
 *
 * @author salam
 */
public class Button_Org extends Butt {
    private int org_y;
    private int org_x;

    public Button_Org(Frame _frame, int _size_y, int _size_x, int _pos_y, int _pos_x, String _value, World _world, String first_icon) {
        super(_frame, _size_y, _size_x, _pos_y, _pos_x, _value, _world, first_icon);

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (world.get_organisms()[org_y][org_x] == null) {
                    Frame org_chooser_frame = new Frame(Board.ORG_CHOOSER_SCREEN_HEIGHT, Board.ORG_CHOOSER_SCREEN_WIDTH, "Choose and organism");

                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            org_chooser_frame.setVisible(true);
                        }
                    });
                    int button_pos_x = 0;
                    Button_Org_chooser org_1 = new Button_Org_chooser(org_chooser_frame, Board.BUTTON_HEIGHT, Board.BUTTON_WIDTH, 0, button_pos_x, Org.WOLF_SYMBOL, world, org_y, org_x, Pict.WOLF_ICON);
                    button_pos_x += Board.BUTTON_WIDTH + Board.BUTTON_GAP;
                    Button_Org_chooser org_2 = new Button_Org_chooser(org_chooser_frame, Board.BUTTON_HEIGHT, Board.BUTTON_WIDTH, 0, button_pos_x, Org.SHEEP_SYMBOL, world, org_y, org_x, Pict.SHEEP_ICON);
                    button_pos_x += Board.BUTTON_WIDTH + Board.BUTTON_GAP;
                    Button_Org_chooser org_3 = new Button_Org_chooser(org_chooser_frame, Board.BUTTON_HEIGHT, Board.BUTTON_WIDTH, 0, button_pos_x, Org.FOX_SYMBOL, world, org_y, org_x, Pict.FOX_ICON);
                    button_pos_x += Board.BUTTON_WIDTH + Board.BUTTON_GAP;
                    Button_Org_chooser org_4 = new Button_Org_chooser(org_chooser_frame, Board.BUTTON_HEIGHT, Board.BUTTON_WIDTH, 0, button_pos_x, Org.TURTLE_SYMBOL, world, org_y, org_x, Pict.TURTLE_ICON);
                    button_pos_x += Board.BUTTON_WIDTH + Board.BUTTON_GAP;
                    Button_Org_chooser org_5 = new Button_Org_chooser(org_chooser_frame, Board.BUTTON_HEIGHT, Board.BUTTON_WIDTH, 0, button_pos_x, Org.ANTELOPE_SYMBOL, world, org_y, org_x, Pict.ANTELOPE_ICON);
                    button_pos_x += Board.BUTTON_WIDTH + Board.BUTTON_GAP;
                    Button_Org_chooser org_6 = new Button_Org_chooser(org_chooser_frame, Board.BUTTON_HEIGHT, Board.BUTTON_WIDTH, 0, button_pos_x, Org.GRASS_SYMBOL, world, org_y, org_x, Pict.GRASS_ICON);
                    button_pos_x += Board.BUTTON_WIDTH + Board.BUTTON_GAP;
                    Button_Org_chooser org_7 = new Button_Org_chooser(org_chooser_frame, Board.BUTTON_HEIGHT, Board.BUTTON_WIDTH, 0, button_pos_x, Org.SOWTHISTLE_SYMBOL, world, org_y, org_x, Pict.SOWTHISTLE_ICON);
                    button_pos_x += Board.BUTTON_WIDTH + Board.BUTTON_GAP;
                    Button_Org_chooser org_8 = new Button_Org_chooser(org_chooser_frame, Board.BUTTON_HEIGHT, Board.BUTTON_WIDTH, 0, button_pos_x, Org.GUARANA_SYMBOL, world, org_y, org_x, Pict.GUARANA_ICON);
                    button_pos_x += Board.BUTTON_WIDTH + Board.BUTTON_GAP;
                    Button_Org_chooser org_9 = new Button_Org_chooser(org_chooser_frame, Board.BUTTON_HEIGHT, Board.BUTTON_WIDTH, 0, button_pos_x, Org.BELLADONNA_SYMBOL, world, org_y, org_x, Pict.BELLADONNA_ICON);
                    button_pos_x += Board.BUTTON_WIDTH + Board.BUTTON_GAP;
                    Button_Org_chooser org_10 = new Button_Org_chooser(org_chooser_frame, Board.BUTTON_HEIGHT, Board.BUTTON_WIDTH, 0, button_pos_x, Org.HOGWEED_SYMBOL, world, org_y, org_x, Pict.HOGWEED_ICON);
                    button_pos_x += Board.BUTTON_WIDTH + Board.BUTTON_GAP;
                    Button_Org_chooser org_11 = new Button_Org_chooser(org_chooser_frame, Board.BUTTON_HEIGHT, Board.BUTTON_WIDTH, 0, button_pos_x, Org.HUMAN_SYMBOL, world, org_y, org_x, Pict.HUMAN_ICON);
                    world.drawWorld();
                }
            }
        });
        //init();
    }

    public void set_org(int _new_y, int _new_x) {

        org_y = _new_y;
        org_x = _new_x;
    }

    public int get_org_y() {

        return org_y;
    }
    public int get_org_x() {

        return org_x;
    }



}