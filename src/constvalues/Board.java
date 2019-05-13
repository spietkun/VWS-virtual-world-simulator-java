package constvalues;

/**
 *
 * @author salam
 */
public class Board {
    // AREAS INFO //
    public static final int MIN_BOARD_HEIGHT = 1;
    public static final int MIN_BOARD_WIDTH = 1;
    
    //CONDITIONS - 1) only one thing in a line; 2) Order of areas is AUTHOR->TITLE->WORLD->STATEMENT
    public static final int AUTHOR_SX = 10; // author start coordinate X
    public static final int AUTHOR_SY = 0; // author start coordinate Y
    public static final int SPACE_Y_BETWEEN_AREAS = 1;
    public static final int SPACE_X_BETWEEN_AREAS = 2;

    // AUTHOR INFO //
    public static final String AUTHOR_INFO = "Szymon Pietkun 171653 projekt nr 2 - Java ";

    // TITLE INFO //
    public static final String TITLE_INFO = "VIRTUAL WORLD SIMULATOR";

    // WORLD INFO //
    public static final char TOP_FENCE_SYMBOL = '_';
    public static final char SIDE_FENCE_SYMBOL = '|';
    public static final char BOTTOM_FENCE_SYMBOL = '-';

    // STATEMENT INFO //
    public static final String STATEMENT_INFO = "This happened now: ";

    //SORT OPTIONS
    public static final int SORT_BY_INITIATIVE = 1;
    public static final int SORT_BY_AGE = 2;
    
    // FRAME //
    
    // BUTTON //
    public static final int BUTTON_WIDTH = 25; // min 50
    public static final int BUTTON_HEIGHT = 25; // min 50
    public static final int BUTTON_GAP = 5;
    
    // SCREEN //
    public static final int SCREEN_WIDTH = ((BUTTON_WIDTH + BUTTON_GAP) * 21)*2;
    public static final int SCREEN_HEIGTH = (BUTTON_HEIGHT + BUTTON_GAP) * 25;
    
    // RAPORT SIZE //
    public static final int RAPORT_WIDTH = ((BUTTON_WIDTH + BUTTON_GAP) * 18);
    public static final int RAPORT_HEIGHT = (BUTTON_HEIGHT + BUTTON_GAP) * 20;
    
    public static final int ORG_CHOOSER_SCREEN_WIDTH = (BUTTON_WIDTH + BUTTON_GAP) * 12 ;
    public static final int ORG_CHOOSER_SCREEN_HEIGHT = (BUTTON_HEIGHT*2 + BUTTON_GAP * 3);
    
    public static final int BOARD_VS_MENU_GAP = 50;
    public static final int MENU_BUTTON_WIDTH = 2*BUTTON_WIDTH + BUTTON_GAP;
    public static final int MENU_BUTTON_HEIGHT = 2*BUTTON_HEIGHT + BUTTON_GAP;
    public static final int MENU_BUTTON_GAP = 50;
    public static final int MAX_N_BUTTON_ROW = (SCREEN_WIDTH - BOARD_VS_MENU_GAP - MENU_BUTTON_WIDTH - BUTTON_WIDTH - BUTTON_GAP)/BUTTON_WIDTH;
    public static final int MAX_N_BUTTON_COLUMN = (SCREEN_HEIGTH - BUTTON_GAP - BUTTON_HEIGHT)/BUTTON_HEIGHT;
    
    //LABEL
}

