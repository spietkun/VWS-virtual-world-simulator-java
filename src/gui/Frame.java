package gui;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.event.*;
import constvalues.Position;

/**
 *
 * @author salam
 */
public class Frame extends JFrame {
    private int size_y;
    private int size_x;
    private String title;
    public Frame(int _size_y, int _size_x, String _title) {

        size_y = _size_y;
        size_x = _size_x;
        title = _title;
        init();
    }

    private void init() {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                setTitle(title);
                setSize(size_x, size_y);
                setLocationRelativeTo(null);
                setLayout(null);
                setVisible(true);
            }
        });
    }
    public void close_programme_attr() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}