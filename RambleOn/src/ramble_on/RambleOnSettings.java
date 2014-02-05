
package ramble_on;

import java.awt.Color;
import java.awt.Font;


public class RambleOnSettings {
    
        /// APP INFO ///
    public static final String APP_NAME = "Ramble On!";
    public static final int FRAME_RATE = 30;
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 700;
    public static final boolean WINDOW_IS_RESIZABLE = false;

    public static final int MAX_SCORE = 10000;
    public static final int SUB_STACK_VELOCITY = 2;


        /// GAME MODES ///
    public static final String WELCOME_MODE_0 = "WELCOME_MODE_0";
    public static final String ACCOUNT_LIST_MODE_1 = "ACCOUNT_LIST_MODE_1";
    public static final String NEW_ACCOUNT_MODE_2 = "NEW_ACCOUNT_MODE_2";
    public static final String SCORE_TREE_MODE_3 = "SCORE_TREE_MODE_3";
    public static final String MAP_VIEW_MODE_4 = "MAP_VIEW_MODE_4";
    public static final String REGION_PICKER_MODE_5 = "REGION_PICKER_MODE_5";
    public static final String WIN_MODE_6 = "WIN_MODE_6";

        /// ACCOUNT FILES ///
    public static final String ACCOUNTS_PATH = "data/accounts/";
    public static final String ACCOUNTS_TAIL = ".ser";
    
       /// GAME FILES ///
    public static final String DIRECTORY_PATH = "data/The World";
    public static final String SCHEMA_FILE = "/RegionData.xsd";
    
        /// GUI FILES ///
    public static final String GUI_PATH = "data/gui/";
    public static final String BACKGROUND_FILE = "background.png";
    public static final String WELCOME_DISPLAY = "welcomeDisplay.png";
    public static final String ACCOUNTS_LIST_PANEL = "accountsPanel.png";
    public static final String SELECT_BUTTON = "selectButton.png";
    public static final String NEW_BUTTON = "newButton.png";
    public static final String NEW_ACCOUNT_PANEL = "newAccountPanel.png";
    public static final String OK_BUTTON = "okButton.png";
    public static final String CANCEL_BUTTON = "cancelButton.png";
    public static final String GAME_LOGO = "gameLogo.png";
    public static final String MAPVIEW_BUTTON = "mapViewButton.png";
    public static final String MAP_BACKGROUND = "mapBackground.png";
    public static final String SUBREGION_BUTTON = "subregionButton.png";
    public static final String SUBREGION_HOVER_BUTTON = "subregionHoverButton.png";
    public static final String SUBREGION_INACTIVE_BUTTON = "subregionInactiveButton.png";
    public static final String CAPITAL_BUTTON = "capitalButton.png";
    public static final String CAPITAL_HOVER_BUTTON = "capitalHoverButton.png";
    public static final String CAPITAL_INACTIVE_BUTTON = "capitalInactiveButton.png";
    public static final String LEADER_BUTTON = "leaderButton.png";
    public static final String LEADER_HOVER_BUTTON = "leaderHoverButton.png";
    public static final String LEADER_INACTIVE_BUTTON = "leaderInactiveButton.png";
    public static final String FLAG_BUTTON = "flagButton.png";
    public static final String FLAG_HOVER_BUTTON = "flagHoverButton.png";
    public static final String FLAG_INACTIVE_BUTTON = "flagInactiveButton.png";
    public static final String BOX_IMAGE = "subRegionBox.png";
    public static final String LEADER_BOX_IMAGE = "leaderBox.png";
    public static final String FLAG_BOX_IMAGE = "flagBox.png";
    public static final String EXIT_BUTTON = "exitButton.png";
    public static final String ACCOUNT_LIST_BUTTON = "accountListButton.png";
    public static final String SCORE_TREE_BUTTON = "scoreTreeButton.png";
    public static final String MAP_NAVIGATION_BUTTON = "mapNavigationButton.png";






    
            /// GUI ID ///
    public static final String BACKGROUND_TYPE  = "BACKGROUND_TYPE";
    public static final String WELCOME_DISPLAY_TYPE = "WELCOME_DISPLAY_TYPE";
    public static final String ACCOUNTS_LIST_PANEL_TYPE = "ACCOUNTS_LIST_PANEL_TYPE";
    public static final String SELECT_BUTTON_TYPE = "SELECT_BUTTON_TYPE";
    public static final String NEW_BUTTON_TYPE = "NEW_BUTTON_TYPE";
    public static final String NEW_ACCOUNT_PANEL_TYPE = "NEW_ACCOUNT_PANEL_TYPE";
    public static final String OK_BUTTON_TYPE = "OK_BUTTON_TYPE";
    public static final String CANCEL_BUTTON_TYPE = "CANCEL_BUTTON_TYPE";
    public static final String MAPVIEW_TYPE  = "MAPVIEW_TYPE";
    public static final String LOGO_TYPE  = "LOGO_TYPE";
    public static final String MAP_BACKGROUND_TYPE = "MAP_BACKGROUND_TYPE";
    public static final String SUBREGION_TYPE = "SubRegion Mode";
    public static final String CAPITAL_TYPE = "Capital Mode";
    public static final String LEADER_TYPE = "Leader Mode";
    public static final String FLAG_TYPE = "Flag Mode";
    public static final String MAP_TYPE = "MAP_TYPE";
    public static final String BOX_TYPE = "BOX_TYPE";
    public static final String EXIT_BUTTON_TYPE = "EXIT_TYPE";
    public static final String ACCOUNT_LIST_BUTTON_TYPE = "ACCOUNT_LIST_BUTTON_TYPE";
    public static final String AAA_SCORE_TREE_BUTTON_TYPE = "SCORE_TREE_BUTTON_TYPE";
    public static final String AAA_MAP_NAVIGATION_BUTTON_TYPE = "MAP_NAVIGATION_BUTTON_TYPE";

    
            /// GUI STATES ///
    public static final String INVISIBLE_STATE = "INVISIBLE_STATE";
    public static final String VISIBLE_STATE = "VISIBLE_STATE";
    public static final String MOUSE_OVER_STATE = "MOUSE_OVER_STATE";
    public static final String INACTIVE_STATE = "INACTIVE_STATE";

    
            /// TEXT & COLOR INFO ///
    public static final Color COLOR_KEY = new Color(220, 110, 0);
    public static final Color CORRECT_COLOR = new Color(163, 255, 193);
    public static final Color INCORRECT_COLOR = new Color(242, 138, 128);
    public static final Color STANDARD_GRAY = new Color(88, 88, 88);
    public static final Font STANDARD_FONT = new Font("Sans Serif", Font.PLAIN, 16);
    public static final Font HEADER_FONT = new Font("Sans Serif", Font.BOLD, 18);
    public static final Font TITLE_FONT = new Font("Sans Serif", Font.BOLD, 26);


    
        /// AUDIO INFO ///
    public static final String AUDIO_DIR = "data/audio/";
    public static final String DEFAULT_WIN_FILE = AUDIO_DIR + "WonderfulWorld.mid";
    public static final String GAME_PLAY_FILE = AUDIO_DIR + "SmallWorld.mid";
    public static final String CORRECT_FILE = AUDIO_DIR + "correct.wav";
    public static final String INCORRECT_FILE = AUDIO_DIR + "incorrect.wav";
    public static final String DEFAULT_WIN = "DEFAULT_WIN";
    public static final String GAME_PLAY = "GAME_PLAY";
    public static final String CORRECT = "CORRECT";
    public static final String INCORRECT = "INCORRECT";

    
}
