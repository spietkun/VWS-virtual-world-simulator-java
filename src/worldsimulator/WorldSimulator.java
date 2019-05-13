package worldsimulator;
import constvalues.Position;
import constvalues.Intwrap;
import constvalues.Raport;
import constvalues.Board;
import constvalues.Pict;
import constvalues.Move;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.DefaultCaret;

import gui.Action_Load;
import gui.Action_Power;
import gui.Action_Save;
import gui.Action_Step;
import gui.Key_Step_Listener;
import gui.Butt;


import gui.Frame;

public class WorldSimulator {

    public int x;
    public int y;
    public Intwrap action = new Intwrap(1);
    
    public void change_dim(int y, int x){
        this.x = x;
        this.y = y;
    }
    public static void main(String[] args) {
        int x = 20;
        int y = 20;
        
        Position size = new Position(2,2); // used for size
        //Intwrap action = new Intwrap(1);
        Frame frame_1 = new Frame(Board.SCREEN_HEIGTH, Board.SCREEN_WIDTH, Board.AUTHOR_INFO + " " + Board.TITLE_INFO);
        frame_1.close_programme_attr();
        frame_1.setVisible(true);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame_1.setVisible(true);
            }
        });
        
       // TEXT AREA //
        JTextArea raport_area = new JTextArea(1,1);
        
        raport_area.setBounds(x*(Board.BUTTON_WIDTH + Board.BUTTON_GAP) + Board.BUTTON_WIDTH, Board.MENU_BUTTON_HEIGHT, 1, 1);
        raport_area.setSize(100, 100);
        raport_area.setText("Round raport: \n");
        
        DefaultCaret caret = (DefaultCaret)raport_area.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
       
        JScrollPane scrollPane = new JScrollPane(raport_area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
        raport_area.setLineWrap(true);
        raport_area.setWrapStyleWord(true);
        raport_area.setEditable(false);
        scrollPane.setSize(200, 200);
        scrollPane.setBounds(x*(Board.BUTTON_WIDTH + Board.BUTTON_GAP) + Board.BUTTON_WIDTH, Board.MENU_BUTTON_HEIGHT + Board.BUTTON_GAP, Board.RAPORT_WIDTH, Board.RAPORT_HEIGHT);
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame_1.add(scrollPane);
                scrollPane.setFocusable(false);
                raport_area.setFocusable(false);
            }
        });
        //
          
	World Koleczkowo = new World(y, x, frame_1, raport_area);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame_1.setFocusable(true);
                frame_1.addKeyListener(new Key_Step_Listener(Koleczkowo));
            }
        });
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            
        int button_pos_x = 0;
        Butt save_button = new Butt(frame_1, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, "ZAP", Koleczkowo, Pict.SAVE_ICON);
        save_button.addActionListener(new Action_Save(Koleczkowo));
        
        button_pos_x += Board.MENU_BUTTON_WIDTH + Board.BUTTON_GAP;
        Butt load_button = new Butt(frame_1, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, "WcZyTaJ", Koleczkowo, Pict.LOAD_ICON);
        load_button.addActionListener(new Action_Load(Koleczkowo));
        
        button_pos_x += Board.MENU_BUTTON_WIDTH + Board.BUTTON_GAP;
        Butt up_button = new Butt(frame_1, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, "up", Koleczkowo, Pict.UP_ICON);
        up_button.addActionListener(new Action_Step(Koleczkowo, Move.UP));
        
        button_pos_x += Board.MENU_BUTTON_WIDTH + Board.BUTTON_GAP;
        Butt down_button = new Butt(frame_1, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, "down", Koleczkowo, Pict.DOWN_ICON);
        down_button.addActionListener(new Action_Step(Koleczkowo, Move.DOWN));
        
        button_pos_x += Board.MENU_BUTTON_WIDTH + Board.BUTTON_GAP;
        Butt left_button = new Butt(frame_1, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, "left", Koleczkowo, Pict.LEFT_ICON);
        left_button.addActionListener(new Action_Step(Koleczkowo, Move.LEFT));
        
        button_pos_x += Board.MENU_BUTTON_WIDTH + Board.BUTTON_GAP;
        Butt right_button = new Butt(frame_1, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, "right", Koleczkowo, Pict.RIGHT_ICON);
        right_button.addActionListener(new Action_Step(Koleczkowo, Move.RIGHT));
        
        button_pos_x += Board.MENU_BUTTON_WIDTH + Board.BUTTON_GAP;
        Butt alzur_button = new Butt(frame_1, Board.MENU_BUTTON_HEIGHT, Board.MENU_BUTTON_WIDTH, 0, button_pos_x, "Alzur", Koleczkowo, Pict.ALZUR_ICON);
        alzur_button.addActionListener(new Action_Power(Koleczkowo));
        
        }
        });
	
        Koleczkowo.open_and_clear_file(Raport.F_ROUND_RAPORT);
	Koleczkowo.initialFirstRound();
	Koleczkowo.set_human_direction(0);
	Koleczkowo.initialWorldformation(Koleczkowo);
	Koleczkowo.drawWorld();
        
        Koleczkowo.close_file(Raport.F_ROUND_RAPORT);
        Koleczkowo.open_file_to_read(Raport.F_ROUND_RAPORT);
	Koleczkowo.read_from_file(Raport.F_ROUND_RAPORT);

	Koleczkowo.close_file(Raport.F_ROUND_RAPORT);

    }
    
}
