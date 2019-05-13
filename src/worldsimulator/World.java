package worldsimulator;

import constvalues.Position;
import constvalues.Org;
import constvalues.Raport;
import constvalues.Board;
import constvalues.Power;
import java.util.Random;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import javax.swing.*;
import java.awt.*;

import animals.Wolf;
import animals.Sheep;
import animals.Fox;
import animals.Turtle;
import animals.Antelope;
import animals.Human;
import constvalues.Pict;
import gui.Frame;
import gui.Butt;
import gui.Button_Org;

import plants.Belladonna;
import plants.Grass;
import plants.Guarana;
import plants.Hogweed;
import plants.Sowthistle;

/**
 *
 * @author salam
 */
public class World {

    //public
    private int HEIGHT;
    private int width;
    private int n_fields;
    private int round;
    private Organism[][] organisms = null;
    private Button_Org[][] buttons = null;
    private File raport_file = new File(Raport.ROUND_RAPORT_PATH);
    private File save_file = new File(Raport.SAVE_STATE_PATH);
    private FileWriter raport_file_fw = null;
    private FileWriter save_file_fw = null;
    private FileReader raport_file_fr = null;
    private FileReader save_file_fr = null;
    private BufferedWriter raport_file_bw = null;
    private BufferedWriter save_file_bw = null;
    private BufferedReader raport_file_br = null;
    private BufferedReader save_file_br = null;
    private int human_direction;
    private Power alzur_shield = new Power();
    private Organism[] sorted_organism;
    private static int number_of_organisms = 0; // initialize with 0
    private JTextArea raport_area_ref;
    Frame main_frame;

    //PUBLIC
    
    public World(int _HEIGHT, int _width, Frame frame, JTextArea raport_area) {
        main_frame = frame;
        //HEIGHT = _HEIGHT;
        //width = _width;
        raport_area_ref = raport_area;
        //n_fields = HEIGHT * width;

        init_world_arrays(_HEIGHT, _width);

        set_alzur_power(false, Org.HUMAN_ALZUR_SHIELD_DURATION, 0);
    };
    
    public void initialFirstRound() {
        round = 0;
    }
    public void initialWorldformation(World this_world) {
        int n_organisms;
        int n_single_species;
        Position pos = new Position(0, 0);
        if (n_fields > Org.N_SPECIES) // if there are more fields than spieces fill the board with organisms in the number equal to const muliplier * fields
        {
            n_organisms = (int)(Org.MULTIPLIER_FOR_ORGANISMS * n_fields);
            n_single_species = n_organisms / Org.N_SPECIES;
            int loop_length = n_single_species;
            
            if (loop_length != 0) {
                pos = randomPosition(HEIGHT, width); // ONE HUMAN
                organisms[pos.y][pos.x] = new Human(pos, this_world);

                for (int i = 0; i < loop_length; i++) {
                    pos = randomPosition(HEIGHT, width);
                    organisms[pos.y][pos.x] = new Wolf(pos, this_world);

                    pos = randomPosition(HEIGHT, width);
                    organisms[pos.y][pos.x] = new Sheep(pos, this_world);

                    pos = randomPosition(HEIGHT, width);
                    organisms[pos.y][pos.x] = new Fox(pos, this_world);

                    pos = randomPosition(HEIGHT, width);
                    organisms[pos.y][pos.x] = new Turtle(pos, this_world);

                    pos = randomPosition(HEIGHT, width);
                    organisms[pos.y][pos.x] = new Antelope(pos, this_world);

                    pos = randomPosition(HEIGHT, width);
                    organisms[pos.y][pos.x] = new Grass(pos, this_world);

                    pos = randomPosition(HEIGHT, width);
                    organisms[pos.y][pos.x] = new Sowthistle(pos, this_world);

                    pos = randomPosition(HEIGHT, width);
                    organisms[pos.y][pos.x] = new Guarana(pos, this_world);

                    pos = randomPosition(HEIGHT, width);
                    organisms[pos.y][pos.x] = new Belladonna(pos, this_world);

                    pos = randomPosition(HEIGHT, width);
                    organisms[pos.y][pos.x] = new Hogweed(pos, this_world);
                }
            } else n_fields = Org.N_SPECIES; // if multiplier for organisms is too small then create a single instance of each organism 
        }    
    }
  


    public void drawWorld() {
        //placeAreaX(Board.SPACE_X_BETWEEN_AREAS);
        //for (int i = 0; i < width + 2; i++) System.out.print(Board.TOP_FENCE_SYMBOL); // DRAW FENCE, +2 because of 2 columns
        //System.out.print("\n");

        for (int i = 0; i < HEIGHT; i++) {
            //placeAreaX(Board.SPACE_X_BETWEEN_AREAS);
            //System.out.print(Board.SIDE_FENCE_SYMBOL); // DRAW FENCE
            for (int j = 0; j < width; j++) {
                if (organisms[i][j] == null) {

                    try {
                        ImageIcon img = new ImageIcon(Pict.FLOOR_ICON);
                        buttons[i][j].setIcon(img);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    buttons[i][j].set_value("");
                } else {
                    //buttons[i][j].setMargin(new Insets(0, 0, 0, 0));
                    //buttons[i][j].setContentAreaFilled(false);
                    buttons[i][j].set_value(Character.toString(organisms[i][j].symbol));

                    try {
                        ImageIcon img = new ImageIcon(organisms[i][j].draw());
                        buttons[i][j].setIcon(img);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

            }
        }
        //SwingUtilities.updateComponentTreeUI(main_frame);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                main_frame.revalidate();
                main_frame.repaint();
            }
        });


    }

    public int get_HEIGHT() {
        return HEIGHT;
    }

    public int get_width() {
        return width;
    }

    public void set_round(int _round) {
        round = _round;
    }

    public int get_round() {
        return round;
    }

    public Organism[][] get_organisms() {
        return organisms;
    }

    public Butt[][] get_buttons() {
        return buttons;
    }
    
    public int get_alzur_duration()
    {
        return alzur_shield.duration;
    }
    
    public int get_alzur_pause()
    {
        return alzur_shield.pause;
    }
    
    public boolean get_alzur_on_off()
    {
        return alzur_shield.is_power_on;
    }
    
    public void performRound() {
        copy_organisms_to_sorted();
        increment_round();
        sort_buff_organism();

        if (alzur_shield.is_power_on == true) {
            if (alzur_shield.duration > 1) // decrease till duration = 1 round
            {
                (alzur_shield.duration) --;
            } else // if super_power ended, decrease pause time
            {
                alzur_shield.is_power_on = false;
            }
        } else {
            if (alzur_shield.pause > 0) {
                (alzur_shield.pause) --;
            }
        }
        
        for (int i = 0; i < HEIGHT * width; i++) {
            if (sorted_organism[i] != null) {
                sorted_organism[i].grow_older();
                sorted_organism[i].action();
            }
        }
    }


    public void set_human_direction(int direction) {
        human_direction = direction;
    }

    public int get_human_direction() {
        return human_direction;
    }

    /*public int follow_command(int command) {
        int result = Opt.CONTINUE_ROUND;
        switch (command) {
            case Opt.MOVE_LEFT:
                set_human_direction(Move.LEFT);
                result = Opt.CONTINUE_ROUND;
                break;
            case Opt.MOVE_RIGHT:
                set_human_direction(Move.RIGHT);
                result = Opt.CONTINUE_ROUND;
                break;
            case Opt.MOVE_UP:
                set_human_direction(Move.UP);
                result = Opt.CONTINUE_ROUND;
                break;
            case Opt.MOVE_DOWN:
                set_human_direction(Move.DOWN);
                result = Opt.CONTINUE_ROUND;
                break;
            case Opt.ACTIVATE_ALZUR:
                Power buf_alzur = get_alzur_power();
                if (buf_alzur.is_power_on == false && buf_alzur.pause == 0) // When you can turn alzur on
                {
                    set_alzur_power(true, Org.HUMAN_ALZUR_SHIELD_DURATION, Org.HUMAN_ALZUR_SHIELD_BREAK);
                    System.out.print("Alzur shield has been activated. It will last for: " + alzur_shield.duration + " rounds .\n");
                    result = Opt.DOWNLOAD_KEY;
                } else if (buf_alzur.is_power_on == true) // when power has been already turned
                {
                    System.out.print("Alzur shield has already been picked!: " + alzur_shield.duration + " rounds left.\n");
                    result = Opt.DOWNLOAD_KEY;
                } else // when there is a pause from choosing power again
                {
                    System.out.print("Alzur shield has been chosen lately. Relax and wait " + alzur_shield.pause + " rounds.\n");
                    result = Opt.DOWNLOAD_KEY;
                }
                break;
            case Opt.OTHER_KEY:
                result = Opt.DOWNLOAD_KEY;
                System.out.print("Tap another key please. \n");
                break;
        }
        return result;
    }*/

    public File get_raport_file() {
        return raport_file;
    }

    public File get_save_file() {
        return save_file;
    }

    public void open_and_clear_file(int choice) {
        if (choice == Raport.F_SAVE_STATE) {
            try {
                if (!save_file.exists()) {
                    save_file.createNewFile();
                }
                save_file_fw = new FileWriter(save_file.getAbsoluteFile());
                save_file_bw = new BufferedWriter(save_file_fw);
            } catch (Exception e) {System.out.println(e);}
        } else if (choice == Raport.F_ROUND_RAPORT) {
            try {
                if (!raport_file.exists()) {
                    raport_file.createNewFile();
                }
                raport_file_fw = new FileWriter(raport_file.getAbsoluteFile());
                raport_file_bw = new BufferedWriter(raport_file_fw);
            } catch (Exception e) {System.out.println(e);}
        }
    }

    public void open_file(int choice) { // append to a file
        if (choice == Raport.F_SAVE_STATE) {
            try {
                if (!save_file.exists()) {
                    save_file.createNewFile();
                }
                save_file_fw = new FileWriter(save_file.getAbsoluteFile(), true);
                save_file_bw = new BufferedWriter(save_file_fw);
            } catch (Exception e) {System.out.println(e);}
        } else if (choice == Raport.F_ROUND_RAPORT) {
            try {
                if (!raport_file.exists()) {
                    raport_file.createNewFile();
                }
                raport_file_fw = new FileWriter(raport_file.getAbsoluteFile(), true);
                raport_file_bw = new BufferedWriter(raport_file_fw);
            } catch (Exception e) {System.out.println(e);}
        }
    }

    public void open_file_to_read(int choice) { //
        if (choice == Raport.F_SAVE_STATE) {
            try {
                if (!save_file.exists()) {
                    save_file.createNewFile();
                }
                save_file_fr = new FileReader(save_file.getAbsoluteFile());
                save_file_br = new BufferedReader(save_file_fr);
            } catch (Exception e) {System.out.println(e);}
        } else if (choice == Raport.F_ROUND_RAPORT) {
            try {
                if (!raport_file.exists()) {
                    raport_file.createNewFile();
                }
                raport_file_fr = new FileReader(raport_file.getAbsoluteFile());
                raport_file_br = new BufferedReader(raport_file_fr);
            } catch (Exception e) {System.out.println(e);}
        }
    }

    public void close_file(int choice) {
        if (choice == Raport.F_SAVE_STATE) {
            try {
                if (save_file_bw != null) {
                    save_file_bw.close();
                }
                if (save_file_fw != null) {
                    save_file_fw.close();
                }
                if (save_file_br != null) {
                    save_file_br.close();
                }
                if (save_file_fr != null) {
                    save_file_fr.close();
                }
            } catch (Exception e) {System.out.println(e);}
        } else if (choice == Raport.F_ROUND_RAPORT) {
            try {
                if (raport_file_bw != null) {
                    raport_file_bw.close();
                }
                if (raport_file_fw != null) {
                    raport_file_fw.close();
                }
                if (raport_file_br != null) {
                    raport_file_br.close();
                }
                if (raport_file_fr != null) {
                    raport_file_fr.close();
                }
            } catch (Exception e) {System.out.println(e);}
        }
    }

    public void write_to_file(String text, int choice) {
        if (choice == Raport.F_SAVE_STATE) {
            if (save_file.exists() && save_file_bw != null) {
                try {
                    save_file_bw.write(text);
                    save_file_bw.newLine();
                } catch (Exception e) {System.out.println(e);}
            }
        } else if (choice == Raport.F_ROUND_RAPORT) {
            if (raport_file.exists() && raport_file_bw != null) {
                try {
                    raport_file_bw.write(text);
                    raport_file_bw.newLine();
                } catch (Exception e) {System.out.println(e);}
            }
        }
    }

    public void read_from_file(int choice) {
        String text;
        if (choice == Raport.F_ROUND_RAPORT) {
            if (raport_file.exists() == true) {
                try {
                    raport_area_ref.setText("");
                    raport_area_ref.append("\n NEW ROUND nr: " + get_round() + "\n\n");
                    while ((text = raport_file_br.readLine()) != null) {
                        //text = reader_r.nextLine();
                        raport_area_ref.append(text + '\n');
                        //System.out.print(text + '\n');
                    }
                } catch (Exception e) {System.out.println(e);}
            }
        } else if (choice == Raport.F_SAVE_STATE) { // not used
            if (save_file.exists() == true) {
                //save_file.clear();
                //save_file.seekg(0, ios::beg);
                try {
                    Scanner reader_s = new Scanner(save_file);
                    while (reader_s.hasNextLine()) {
                        text = reader_s.nextLine();
                        //cout << text << endl;
                    }
                } catch (Exception e) {System.out.println(e);}
            }
        }
    }

    public void set_alzur_power(boolean if_activated, int duration, int pause) {
        alzur_shield.is_power_on = if_activated;
        alzur_shield.duration = duration;
        alzur_shield.pause = pause;

    }

    public Power get_alzur_power() {
        return alzur_shield;
    }


    public void increase_number_of_organisms() {
        number_of_organisms++;
    }

    /*public void decrease_number_of_organisms() {
        number_of_organisms--;
    }*/
    public int get_number_of_organisms() {
        return number_of_organisms;
    }

    public void set_number_of_organisms(int _number_of_organisms) {
        number_of_organisms = _number_of_organisms;
    }

    public Organism[] get_sorted_organisms() {
        return sorted_organism;
    }

    public void save_world_state() {
        int is_alz_on = alzur_shield.is_power_on ? 1 : 0;
        String world_info = Raport.WORLD_INFO;
        String s_HEIGHT = Integer.toString(HEIGHT);
        String s_width = Integer.toString(width);
        String s_round = Integer.toString(round);
        String s_alzur_shield_on = Integer.toString(is_alz_on);
        String s_alzur_shield_dur = Integer.toString(alzur_shield.duration);
        String s_alzur_shield_pause = Integer.toString(alzur_shield.pause);
        String s_number_of_organisms = Integer.toString(number_of_organisms);

        //String all_world_info = world_info + s_HEIGHT + s_width + s_round + s_alzur_shield + s_number_of_organisms;
        open_and_clear_file(Raport.F_SAVE_STATE);

        write_to_file(world_info, Raport.F_SAVE_STATE);
        write_to_file(s_HEIGHT, Raport.F_SAVE_STATE);
        write_to_file(s_width, Raport.F_SAVE_STATE);
        write_to_file(s_round, Raport.F_SAVE_STATE);
        write_to_file(s_alzur_shield_on, Raport.F_SAVE_STATE);
        write_to_file(s_alzur_shield_dur, Raport.F_SAVE_STATE);
        write_to_file(s_alzur_shield_pause, Raport.F_SAVE_STATE);
        write_to_file(s_number_of_organisms, Raport.F_SAVE_STATE);

        String s_organism_name;
        String s_organism_position_x;
        String s_organism_position_y;
        String s_organism_strength;
        String s_organism_gender;
        String s_organism_age;
        String s_organism_ID_number;

        //String all_organism_info;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < width; j++) {
                if (organisms[i][j] != null) {
                    Position pos = organisms[i][j].get_position();

                    s_organism_name = "" + organisms[i][j].get_symbol();
                    s_organism_position_x = Integer.toString(pos.x);
                    s_organism_position_y = Integer.toString(pos.y);
                    s_organism_strength = Integer.toString(organisms[i][j].get_strength());
                    s_organism_gender = Integer.toString(organisms[i][j].get_gender());
                    s_organism_age = Integer.toString(organisms[i][j].get_age());
                    s_organism_ID_number = Integer.toString(organisms[i][j].get_ID_number());

                    //all_organism_info = s_organism_name + s_organism_position + s_organism_strength + s_organism_gender + s_organism_age + s_organism_ID_number;
                    write_to_file(s_organism_name, Raport.F_SAVE_STATE);
                    write_to_file(s_organism_position_x, Raport.F_SAVE_STATE);
                    write_to_file(s_organism_position_y, Raport.F_SAVE_STATE);
                    write_to_file(s_organism_strength, Raport.F_SAVE_STATE);
                    write_to_file(s_organism_gender, Raport.F_SAVE_STATE);
                    write_to_file(s_organism_age, Raport.F_SAVE_STATE);
                    write_to_file(s_organism_ID_number, Raport.F_SAVE_STATE);
                }
            }
        }

        close_file(Raport.F_SAVE_STATE);
    }

    public void load_world_state(World old_world) {
        //World New_world = null;
        try {
            open_file_to_read(Raport.F_SAVE_STATE);

            int f_HEIGHT;
            int f_width;
            int f_round;
            Power f_alzur_shield = new Power();
            int f_number_of_organisms;
            String text;


            if (save_file.exists() == true) {
                //Scanner reader_s = new Scanner(save_file);
                //save_file.clear();
                //save_file.seekg(0, ios::beg);

                text = save_file_br.readLine(); //World info
                //System.out.print("text save" + text);
                //READ NUMERIC DATA
                text = save_file_br.readLine();
                //System.out.print("text save" + text);
                f_HEIGHT = Integer.parseInt(text);
                text = save_file_br.readLine();
                //System.out.print("text save" + text);
                f_width = Integer.parseInt(text);
                text = save_file_br.readLine();
                //System.out.print("text save" + text);
                f_round = Integer.parseInt(text);
                text = save_file_br.readLine();
                //System.out.print("text save" + text);
                f_alzur_shield.is_power_on = (Integer.parseInt(text) != 0);
                text = save_file_br.readLine();
                //System.out.print("text save" + text);
                f_alzur_shield.duration = Integer.parseInt(text);
                text = save_file_br.readLine();
                //System.out.print("text save" + text);
                f_alzur_shield.pause = Integer.parseInt(text);
                text = save_file_br.readLine();
                //System.out.print("text save" + text);
                f_number_of_organisms = Integer.parseInt(text);

                remove_org_buttons();
                //New_world = new World(f_HEIGHT, f_width, main_frame, raport_area_ref);
                set_round(f_round);
                set_alzur_power(f_alzur_shield.is_power_on, f_alzur_shield.duration, f_alzur_shield.pause);
                set_number_of_organisms(f_number_of_organisms);
                init_world_arrays(f_HEIGHT, f_width);
                //System.out.println(f_HEIGHT);

                //INITIALIZE ORGANISMS

                char f_organism_name;
                Position f_organism_position;
                int f_organism_strength;
                int f_organism_gender;
                int f_organism_age;
                int f_organism_ID_number;

                while ((text = save_file_br.readLine()) != null && text.length() != 0) {
                    f_organism_name = text.charAt(0);
                    //System.out.print("animal save" + text);
                    if (text != "") // there is a "" at the end of the save file
                    {
                        f_organism_position = new Position(0, 0);
                        text = save_file_br.readLine();
                        //System.out.print("text save" + text);
                        f_organism_position.x = Integer.parseInt(text);
                        text = save_file_br.readLine();
                        //System.out.print("text save" + text);
                        f_organism_position.y = Integer.parseInt(text);
                        text = save_file_br.readLine();
                        //System.out.print("text save" + text);
                        f_organism_strength = Integer.parseInt(text);
                        text = save_file_br.readLine();
                        //System.out.print("text save" + text);
                        f_organism_gender = Integer.parseInt(text);
                        text = save_file_br.readLine();
                        //System.out.print("text save" + text);
                        f_organism_age = Integer.parseInt(text);
                        text = save_file_br.readLine();
                        //System.out.print("text save" + text);
                        f_organism_ID_number = Integer.parseInt(text);

                        //if (text.equals("")) {break;}
                        //System.out.print(f_organism_name + " " + f_organism_position.y + " " + f_organism_position.x + "  ");
                        if (f_organism_name == Org.WOLF_SYMBOL) get_organisms()[f_organism_position.y][f_organism_position.x] = new Wolf(f_organism_position, this, f_organism_strength, f_organism_gender);
                        else if (f_organism_name == Org.SHEEP_SYMBOL) get_organisms()[f_organism_position.y][f_organism_position.x] = new Sheep(f_organism_position, this, f_organism_strength, f_organism_gender);
                        else if (f_organism_name == Org.FOX_SYMBOL) get_organisms()[f_organism_position.y][f_organism_position.x] = new Fox(f_organism_position, this, f_organism_strength, f_organism_gender);
                        else if (f_organism_name == Org.TURTLE_SYMBOL) get_organisms()[f_organism_position.y][f_organism_position.x] = new Turtle(f_organism_position, this, f_organism_strength, f_organism_gender);
                        else if (f_organism_name == Org.ANTELOPE_SYMBOL) get_organisms()[f_organism_position.y][f_organism_position.x] = new Antelope(f_organism_position, this, f_organism_strength, f_organism_gender);
                        else if (f_organism_name == Org.HUMAN_SYMBOL) get_organisms()[f_organism_position.y][f_organism_position.x] = new Human(f_organism_position, this, f_organism_strength, f_organism_gender);
                        else if (f_organism_name == Org.GRASS_SYMBOL) get_organisms()[f_organism_position.y][f_organism_position.x] = new Grass(f_organism_position, this, f_organism_strength);
                        else if (f_organism_name == Org.SOWTHISTLE_SYMBOL) get_organisms()[f_organism_position.y][f_organism_position.x] = new Sowthistle(f_organism_position, this, f_organism_strength);
                        else if (f_organism_name == Org.GUARANA_SYMBOL) get_organisms()[f_organism_position.y][f_organism_position.x] = new Guarana(f_organism_position, this, f_organism_strength);
                        else if (f_organism_name == Org.HOGWEED_SYMBOL) get_organisms()[f_organism_position.y][f_organism_position.x] = new Hogweed(f_organism_position, this, f_organism_strength);
                        else if (f_organism_name == Org.BELLADONNA_SYMBOL) get_organisms()[f_organism_position.y][f_organism_position.x] = new Belladonna(f_organism_position, this, f_organism_strength);

                        (get_organisms()[f_organism_position.y][f_organism_position.x]).set_gender(f_organism_gender); // plants
                        (get_organisms()[f_organism_position.y][f_organism_position.x]).set_age(f_organism_age);
                        (get_organisms()[f_organism_position.y][f_organism_position.x]).set_ID_number(f_organism_ID_number);
                        //System.out.println("test 1");
                        /*for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < width; j++) {
                if (New_world.get_organisms()[i][j] != null)
                { System.out.println(New_world.get_organisms()[i][j].pos.y + " " + New_world.get_organisms()[i][j].pos.x + "  ");}
                }
            }*/

                    }
                }
                //System.out.println("test 2");
                /*for (int i = 0; i < HEIGHT; i++) {
                            for (int j = 0; j < width; j++) {
                                if (New_world.get_organisms()[i][j] != null)
                                { System.out.println(New_world.get_organisms()[i][j].pos.x + " " + New_world.get_organisms()[i][j].pos.x + "  ");}
                                }
                            }*/
            }

            close_file(Raport.F_SAVE_STATE);
        } catch (Exception e) {System.out.println(e);}
        close_file(Raport.F_ROUND_RAPORT);
        //////////////////////
 
        /////////////////////
   
    }

    private void remove_org_buttons() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < width; j++) {
                main_frame.remove(buttons[i][j]);
            }
        }
    }
    
    private void init_world_arrays(int new_height, int new_width){
    
        HEIGHT = new_height;
        width = new_width;
        n_fields = HEIGHT * width;
        
        organisms = new Organism[new_height][];
        for (int i = 0; i < new_height; i++) {
            organisms[i] = new Organism[new_width];
            for (int j = 0; j < new_width; j++) {
                organisms[i][j] = null;
            }
        }

        
        buttons = new Button_Org[new_height][];
        int y_value = Board.MENU_BUTTON_HEIGHT + Board.BUTTON_GAP;
        int x_value = 0;
        for (int i = 0; i < new_height; i++) {
            buttons[i] = new Button_Org[new_width];
            for (int j = 0; j < new_width; j++) {
                buttons[i][j] = new Button_Org(main_frame, Board.BUTTON_HEIGHT, Board.BUTTON_WIDTH, y_value, x_value, "", this, Pict.FLOOR_ICON);
                buttons[i][j].set_org(i, j);
                x_value += Board.BUTTON_WIDTH + Board.BUTTON_GAP;
            }
            y_value += Board.BUTTON_HEIGHT + Board.BUTTON_GAP;
            x_value = 0;
        }
        
        sorted_organism = new Organism[new_height * new_width];
        for (int i = 0; i < new_height * new_width; i++) {
            sorted_organism[i] = null;
        }
    
    }
    
    private Position randomPosition(int y, int x) {
        Position pos = new Position(0, 0);
        Random rand = new Random();
        boolean already_occ;

        do {
            already_occ = false;
            pos.x = rand.nextInt(x);
            pos.y = rand.nextInt(y);

            if (organisms[pos.y][pos.x] != null) already_occ = true;
            else already_occ = false;

        } while (already_occ);

        return pos;
    }
    
    private void increment_round() {
        round++;
    }
    
     private void copy_organisms_to_sorted() {
        int k = 0;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < width; j++) {
                sorted_organism[k] = organisms[i][j];
                k++;
            }
        }
    }
     
     private void sort_buff_organism() // sort by initiative and if it is equal than compaere ID number
    {
        for (int i = 0; i < HEIGHT * width - 1; i++) {
            for (int j = 0; j < HEIGHT * width - 1; j++) {
                if (sorted_organism[j] == null) {
                    Organism buf = sorted_organism[j];
                    sorted_organism[j] = sorted_organism[j + 1];
                    sorted_organism[j + 1] = buf;
                    //swap(sorted_organism[j], sorted_organism[j + 1]);
                } // push all nulls to the end
                else if (sorted_organism[j + 1] == null); // dont change if next is null
                else if (sorted_organism[j].get_initiative() < sorted_organism[j + 1].get_initiative()) {                
//swap(sorted_organism[j], sorted_organism[j + 1]);
                    Organism buf = sorted_organism[j];
                    sorted_organism[j] = sorted_organism[j + 1];
                    sorted_organism[j + 1] = buf;
                } else if (sorted_organism[j].get_initiative() == sorted_organism[j + 1].get_initiative()) {
                    if (sorted_organism[j].get_ID_number() > sorted_organism[j + 1].get_ID_number()) {
                        //swap(sorted_organism[j], sorted_organism[j + 1]);
                        Organism buf = sorted_organism[j];
                        sorted_organism[j] = sorted_organism[j + 1];
                        sorted_organism[j + 1] = buf;
                    }
                }
            }
        }
    }
    /*public Intwrap action = new Intwrap(1);
    
    public void change_action(int value){
        action.value = value;
    }*/
    /*public Position get_world_dimensions(){
    
    
    
    }*/

    /*public ~World() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < width; j++) {
                if (organisms[i][j] != null) {
                    //delete organisms[i][j];
                }
            }
            //delete[] organisms[i];
        }
        //delete[] organisms;

        //delete[] sorted_organism;
    }*/
}