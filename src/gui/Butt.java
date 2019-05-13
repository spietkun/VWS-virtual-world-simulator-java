package gui;
import java.awt.event.*;
import javax.swing.*;
import worldsimulator.World;
import worldsimulator.WorldSimulator;
import constvalues.Intwrap;
import constvalues.Position;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author salam
 */
public class Butt extends JButton {
    private int size_y;
    private int size_x;
    private int pos_y;
    private int pos_x;
    private Frame frame;
    protected World world;
    public String value;
    private String icon_path;

    public Butt(Frame _frame, int _size_y, int _size_x, int _pos_y, int _pos_x, String _value, World _world, String _icon_path) {
        size_y = _size_y;
        size_x = _size_x;
        pos_y = _pos_y;
        pos_x = _pos_x;
        frame = _frame;
        value = _value;
        world = _world;
        icon_path = _icon_path;
        init();
    }

    protected void init() {

        //setText(value);  
        setFocusable(false);
        setBounds(pos_x, pos_y, 1, 1);
        setSize(size_x, size_y);
        try {
            ImageIcon img = new ImageIcon(icon_path);
            setIcon(img);
        } catch (Exception e) {
            System.out.println(e);
        }
        frame.add(this);
    }

    public void set_value(String _new_value) {
        value = _new_value;
        //setText(value);
    }


}