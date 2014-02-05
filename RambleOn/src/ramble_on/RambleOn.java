package ramble_on;

import audio_manager.AudioManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;
import static ramble_on.RambleOnSettings.*;
import ramble_on.events.CancelHandler;
import ramble_on.events.CapitalHandler;
import ramble_on.events.CurrentAccountHandler;
import ramble_on.events.ExitHandler;
import ramble_on.events.FlagHandler;
import ramble_on.events.HotkeyHandler;
import ramble_on.events.LeaderHandler;
import ramble_on.events.MapClickHandler;
import ramble_on.events.MapNavigationHandler;
import ramble_on.events.MapViewHandler;
import ramble_on.events.NewHandler;
import ramble_on.events.OkHandler;
import ramble_on.events.SelectHandler;
import ramble_on.events.SubRegionHandler;
import ramble_on.events.WelcomeHandler;
import ramble_on.sprites.MapSprite;



public class RambleOn extends MiniGame {
    
     AudioManager audio;

    public RambleOn()
    {
       super(APP_NAME, FRAME_RATE);
       initAudio();

    }

    public void initAudio()
    {
        audio = new AudioManager();
        try
        {
            audio.loadAudio(DEFAULT_WIN, DEFAULT_WIN_FILE);
            
            audio.loadAudio(GAME_PLAY, GAME_PLAY_FILE);
            audio.loadAudio(CORRECT, CORRECT_FILE);
            audio.loadAudio(INCORRECT, INCORRECT_FILE);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(window, e.getStackTrace());
        }
    }

    public AudioManager getAudio() { return audio; }

    @Override
    public void initData() 
    {
        data = new RambleOnDataModel();
        data.setGameDimensions(WINDOW_WIDTH, WINDOW_HEIGHT);
        ((RambleOnDataModel) data).setGameMode(WELCOME_MODE_0);
        boundaryLeft = 0;
        boundaryRight = WINDOW_WIDTH;
        boundaryTop = 0;
        boundaryBottom = WINDOW_HEIGHT;
    }

    @Override
    public void initGUIControls() 
    {
        canvas = new RambleOnPanel(this, (RambleOnDataModel) data);
        
        
        SpriteType sT;
        BufferedImage img;
        int x, y, vX, vY;
        Sprite s;
        
        sT = new SpriteType(BACKGROUND_TYPE);
        img = loadImage(GUI_PATH + BACKGROUND_FILE);
        x = 0;
        y = 0;
        vX = 0;
        vY = 0;
        sT.addState(VISIBLE_STATE, img);
        s = new Sprite(sT, x, y, vX, vY, VISIBLE_STATE);
        guiDecor.put(BACKGROUND_TYPE, s);
        
        /// WELCOME DISPLAY ///
        sT = new SpriteType(WELCOME_DISPLAY_TYPE);
        img = loadImage(GUI_PATH + WELCOME_DISPLAY);
        x = 350;
        y = 100;
        vX = 0;
        vY = 0;
        sT.addState(VISIBLE_STATE, img);
        s = new Sprite(sT, x, y, vX, vY, VISIBLE_STATE);
        guiButtons.put(WELCOME_DISPLAY_TYPE, s);
        
        /// ACCOUNTS LIST ///
        sT = new SpriteType(ACCOUNTS_LIST_PANEL_TYPE);
        img = loadImage(GUI_PATH + ACCOUNTS_LIST_PANEL);
        x = 400;
        y = 100;
        vX = 0;
        vY = 0;
        sT.addState(VISIBLE_STATE, img);
        s = new Sprite(sT, x, y, vX, vY, VISIBLE_STATE);
        guiDecor.put(ACCOUNTS_LIST_PANEL_TYPE, s);
        
        sT = new SpriteType(SELECT_BUTTON_TYPE);
        img = loadImage(GUI_PATH + SELECT_BUTTON);
        x = 487;
        y = 525;
        vX = 0;
        vY = 0;
        sT.addState(VISIBLE_STATE, img);
        s = new Sprite(sT, x, y, vX, vY, VISIBLE_STATE);
        guiButtons.put(SELECT_BUTTON_TYPE, s);
        
        sT = new SpriteType(NEW_BUTTON_TYPE);
        img = loadImage(GUI_PATH + NEW_BUTTON);
        x = 612;
        y = 525;
        vX = 0;
        vY = 0;
        sT.addState(VISIBLE_STATE, img);
        s = new Sprite(sT, x, y, vX, vY, VISIBLE_STATE);
        guiButtons.put(NEW_BUTTON_TYPE, s);
        
        /// NEW ACCOUNT ///
        sT = new SpriteType(NEW_ACCOUNT_PANEL_TYPE);
        img = loadImage(GUI_PATH + NEW_ACCOUNT_PANEL);
        x = 400;
        y = 200;
        vX = 0;
        vY = 0;
        sT.addState(VISIBLE_STATE, img);
        s = new Sprite(sT, x, y, vX, vY, VISIBLE_STATE);
        guiDecor.put(NEW_ACCOUNT_PANEL_TYPE, s);
        
        sT = new SpriteType(OK_BUTTON_TYPE);
        img = loadImage(GUI_PATH + OK_BUTTON);
        x = 487;
        y = 375;
        vX = 0;
        vY = 0;
        sT.addState(VISIBLE_STATE, img);
        s = new Sprite(sT, x, y, vX, vY, VISIBLE_STATE);
        guiButtons.put(OK_BUTTON_TYPE, s);
        
        sT = new SpriteType(CANCEL_BUTTON_TYPE);
        img = loadImage(GUI_PATH + CANCEL_BUTTON);
        x = 612;
        y = 375;
        vX = 0;
        vY = 0;
        sT.addState(VISIBLE_STATE, img);
        s = new Sprite(sT, x, y, vX, vY, VISIBLE_STATE);
        guiButtons.put(CANCEL_BUTTON_TYPE, s);
        
        
        /// SCORE TREE ///
        sT = new SpriteType(LOGO_TYPE);
        img = loadImage(GUI_PATH + GAME_LOGO);
        x = 970;
        y = 10;
        vX = 0;
        vY = 0;
        sT.addState(VISIBLE_STATE, img);
        s = new Sprite(sT, x, y, vX, vY, VISIBLE_STATE);
        guiDecor.put(LOGO_TYPE, s);
        
        sT = new SpriteType(MAPVIEW_TYPE);
        img = loadImage(GUI_PATH + MAPVIEW_BUTTON);
        x = 970;
        y = 600;
        vX = 0;
        vY = 0;
        sT.addState(VISIBLE_STATE, img);
        s = new Sprite(sT, x, y, vX, vY, VISIBLE_STATE);
        guiButtons.put(MAPVIEW_TYPE, s);

        
        /// MAP VIEWER ///
        sT = new SpriteType(SUBREGION_TYPE);
        img = loadImage(GUI_PATH + SUBREGION_BUTTON);
        sT.addState(VISIBLE_STATE, img);
        img = loadImage(GUI_PATH + SUBREGION_HOVER_BUTTON);
        sT.addState(MOUSE_OVER_STATE, img);
        img = loadImage(GUI_PATH + SUBREGION_INACTIVE_BUTTON);
        sT.addState(INACTIVE_STATE, img);
        x = 970;
        y = 220;
        s = new Sprite(sT, x, y, 0, 0, VISIBLE_STATE);
        guiButtons.put(SUBREGION_TYPE, s);

        sT = new SpriteType(CAPITAL_TYPE);
        img = loadImage(GUI_PATH + CAPITAL_BUTTON);
        sT.addState(VISIBLE_STATE, img);
        img = loadImage(GUI_PATH + CAPITAL_HOVER_BUTTON);
        sT.addState(MOUSE_OVER_STATE, img);
        img = loadImage(GUI_PATH + CAPITAL_INACTIVE_BUTTON);
        sT.addState(INACTIVE_STATE, img);
        x = 1075;
        y = 220;
        s = new Sprite(sT, x, y, 0, 0, VISIBLE_STATE);
        guiButtons.put(CAPITAL_TYPE, s);

        sT = new SpriteType(LEADER_TYPE);
        img = loadImage(GUI_PATH + LEADER_BUTTON);
        sT.addState(VISIBLE_STATE, img);
        img = loadImage(GUI_PATH + LEADER_HOVER_BUTTON);
        sT.addState(MOUSE_OVER_STATE, img);
        img = loadImage(GUI_PATH + LEADER_INACTIVE_BUTTON);
        sT.addState(INACTIVE_STATE, img);
        x = 970;
        y = 280;
        s = new Sprite(sT, x, y, 0, 0, VISIBLE_STATE);
        guiButtons.put(LEADER_TYPE, s);

        sT = new SpriteType(FLAG_TYPE);
        img = loadImage(GUI_PATH + FLAG_BUTTON);
        sT.addState(VISIBLE_STATE, img);
        img = loadImage(GUI_PATH + FLAG_HOVER_BUTTON);
        sT.addState(MOUSE_OVER_STATE, img);
        img = loadImage(GUI_PATH + FLAG_INACTIVE_BUTTON);
        sT.addState(INACTIVE_STATE, img);
        x = 1075;
        y = 280;
        s = new Sprite(sT, x, y, 0, 0, VISIBLE_STATE);
        guiButtons.put(FLAG_TYPE, s);

        
        /// TOOLBAR ///
        sT = new SpriteType(EXIT_BUTTON_TYPE);
        img = loadImage(GUI_PATH + EXIT_BUTTON);
        sT.addState(VISIBLE_STATE, img);
        x = 45;
        y = 5;
        s = new Sprite(sT, x, y, 0, 0, VISIBLE_STATE);
        guiButtons.put(EXIT_BUTTON_TYPE, s);

       /* sT = new SpriteType(ACCOUNT_LIST_BUTTON_TYPE);
        img = loadImage(GUI_PATH + ACCOUNT_LIST_BUTTON);
        sT.addState(VISIBLE_STATE, img);
        x = 150;
        y = 8;
        s = new Sprite(sT, x, y, 0, 0, VISIBLE_STATE);
        guiButtons.put(ACCOUNT_LIST_BUTTON_TYPE, s);*/
        
        sT = new SpriteType(AAA_SCORE_TREE_BUTTON_TYPE);
        img = loadImage(GUI_PATH + SCORE_TREE_BUTTON);
        sT.addState(VISIBLE_STATE, img);
        x = 150;
        y = 5;
        s = new Sprite(sT, x, y, 0, 0, VISIBLE_STATE);
        guiButtons.put(AAA_SCORE_TREE_BUTTON_TYPE, s);
        
        sT = new SpriteType(AAA_MAP_NAVIGATION_BUTTON_TYPE);
        img = loadImage(GUI_PATH + MAP_NAVIGATION_BUTTON);
        sT.addState(VISIBLE_STATE, img);
        x = 255; //255
        y = 5;
        s = new Sprite(sT, x, y, 0, 0, VISIBLE_STATE);
        guiButtons.put(AAA_MAP_NAVIGATION_BUTTON_TYPE, s);
        
        centerWindow();

        reset();
    }

    @Override
    public void initGUIHandlers() 
    {
        WelcomeHandler wh = new WelcomeHandler(this);
        guiButtons.get(WELCOME_DISPLAY_TYPE).setActionListener(wh);
        
        SelectHandler sh = new SelectHandler(this);
        guiButtons.get(SELECT_BUTTON_TYPE).setActionListener(sh);
        
        NewHandler nh = new NewHandler(this);
        guiButtons.get(NEW_BUTTON_TYPE).setActionListener(nh);
        
        OkHandler okh = new OkHandler(this);
        guiButtons.get(OK_BUTTON_TYPE).setActionListener(okh);
        
        CancelHandler ch = new CancelHandler(this);
        guiButtons.get(CANCEL_BUTTON_TYPE).setActionListener(ch);
        
        MapViewHandler mvh = new MapViewHandler(this);
        guiButtons.get(MAPVIEW_TYPE).setActionListener(mvh);
        
        SubRegionHandler srh = new SubRegionHandler(this);
        guiButtons.get(SUBREGION_TYPE).setActionListener(srh);

        CapitalHandler ch2 = new CapitalHandler(this);
        guiButtons.get(CAPITAL_TYPE).setActionListener(ch2);

        LeaderHandler lh = new LeaderHandler(this);
        guiButtons.get(LEADER_TYPE).setActionListener(lh);

        FlagHandler fh = new FlagHandler(this);
        guiButtons.get(FLAG_TYPE).setActionListener(fh);
        
        ExitHandler eh = new ExitHandler(this);
        guiButtons.get(EXIT_BUTTON_TYPE).setActionListener(eh);
        
        /*AccountListHandler alh = new AccountListHandler(this);
        guiButtons.get(ACCOUNT_LIST_BUTTON_TYPE).setActionListener(alh); */
        
        CurrentAccountHandler cah = new CurrentAccountHandler(this);
        guiButtons.get(AAA_SCORE_TREE_BUTTON_TYPE).setActionListener(cah); 
        
        MapNavigationHandler mnh = new MapNavigationHandler(this);
        guiButtons.get(AAA_MAP_NAVIGATION_BUTTON_TYPE).setActionListener(mnh); 


        
        
        
        
        RambleOnEventRelayer roeh = new RambleOnEventRelayer(this);
        canvas.addMouseListener(roeh);
        canvas.addMouseMotionListener(roeh);
	window.setFocusable(true);
        window.addKeyListener(roeh);
	canvas.addKeyListener(roeh);
        
        HotkeyHandler hh = new HotkeyHandler(this);
        canvas.addKeyListener(hh);
        window.addKeyListener(hh);
        

        
        
    }
    
    public void loadMapSprite()
    {
        SpriteType mapSpriteType = new SpriteType(MAP_TYPE);
        BufferedImage imageToLoad = loadKeyedImage( ((RambleOnDataModel) data).getGDM().getMap(), COLOR_KEY);
        mapSpriteType.addState(VISIBLE_STATE, imageToLoad);
        data.addSpriteType(mapSpriteType);
        MapSprite mapSprite = new MapSprite(mapSpriteType, 0, 0, 0, 0, VISIBLE_STATE);
        guiButtons.put(MAP_TYPE, mapSprite);
        
        mapSpriteType = new SpriteType(MAP_BACKGROUND_TYPE);
        imageToLoad = loadImage(GUI_PATH + MAP_BACKGROUND);
        mapSpriteType.addState(VISIBLE_STATE, imageToLoad);
        data.addSpriteType(mapSpriteType);
        Sprite backgroundSprite = new Sprite(mapSpriteType, 45, 60, 0, 0, VISIBLE_STATE);
        guiDecor.put(MAP_BACKGROUND_TYPE, backgroundSprite);
        
        MapClickHandler mch = new MapClickHandler(this);
        ( (MapSprite) guiButtons.get(MAP_TYPE) ).setMouseListener(mch);
        
        reloadGameModeButtons();
    }

    public void loadBoxSpriteType(String gameType)
    {
        BufferedImage imageToLoad;     
        SpriteType subRegionSpriteType = new SpriteType(BOX_TYPE);
        if(gameType.equals(SUBREGION_TYPE) || gameType.equals(CAPITAL_TYPE))
        {
            imageToLoad = loadImage(GUI_PATH + BOX_IMAGE);
            subRegionSpriteType.addState(VISIBLE_STATE, imageToLoad);
        }
        else if(gameType.equals(LEADER_TYPE))
        {
            imageToLoad = loadImage(GUI_PATH + LEADER_BOX_IMAGE);
            subRegionSpriteType.addState(VISIBLE_STATE, imageToLoad);
        }
        else if(gameType.equals(FLAG_TYPE))
        {
            imageToLoad = loadImage(GUI_PATH + FLAG_BOX_IMAGE);
            subRegionSpriteType.addState(VISIBLE_STATE, imageToLoad);
        }
        data.addSpriteType(subRegionSpriteType);
    }
    public void reloadGameModeButtons()
    {
        if( !((RambleOnDataModel) data).getGDM().isValid() )
        {
            guiButtons.get(SUBREGION_TYPE).setState(INACTIVE_STATE);
        }
        else
        {
            guiButtons.get(SUBREGION_TYPE).setState(VISIBLE_STATE);
        }
        
        if( !((RambleOnDataModel) data).getGDM().hasCapitalMode() )
        {
            guiButtons.get(CAPITAL_TYPE).setState(INACTIVE_STATE);

        }
        else
        {
            guiButtons.get(CAPITAL_TYPE).setState(VISIBLE_STATE);
        }
        
        if( !((RambleOnDataModel) data).getGDM().hasLeaderMode() )
        {
            guiButtons.get(LEADER_TYPE).setState(INACTIVE_STATE);

        }
        else
        {
            guiButtons.get(LEADER_TYPE).setState(VISIBLE_STATE);
        }

        if( !((RambleOnDataModel) data).getGDM().hasFlagMode() )
        {
            guiButtons.get(FLAG_TYPE).setState(INACTIVE_STATE);

        }
        else
        {
            guiButtons.get(FLAG_TYPE).setState(VISIBLE_STATE);
        }
    }
    
   public boolean processRightButtonPress(int x, int y)
    {
      if( ((RambleOnDataModel) data).getGameMode().equals(MAP_VIEW_MODE_4))
      {
        boolean buttonClickPerformed = false;
		
	MapSprite s = (MapSprite) guiButtons.get(MAP_TYPE);
	
        buttonClickPerformed = s.testForRightClick(this, x, y);

	return buttonClickPerformed;
      }

	return false;
}
    @Override
    public void reset() 
    {
        data.reset(this);
    }

    @Override
    public void updateGUI() 
    {

    }
    
    private BufferedImage loadKeyedImage(BufferedImage img, Color colorKey)
	{		
		

	BufferedImage imageToLoad = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	Graphics g = imageToLoad.getGraphics();
	g.drawImage(img, 0,	0,	null);
		
	// NOW MAKE ALL PIXELS WITH COLOR (64, 224, 224) TRANSPARENT
	WritableRaster raster = imageToLoad.getRaster();
	int[] dummy = null;
	for (int i = 0; i < raster.getWidth(); i++)
	{
            for (int j = 0; j < raster.getHeight(); j++)
		{
                    int[] pixel = raster.getPixel(i, j, dummy);
                    if ((pixel[0] == colorKey.getRed())
                            && (pixel[1] == colorKey.getGreen())
                            && (pixel[2] == colorKey.getBlue()))
				{
					pixel[3] = 0;
					raster.setPixel(i, j, pixel);
				}
			}
		}		
		return imageToLoad;
	}
    
    private void centerWindow() 
    {
        window.setExtendedState(JFrame.NORMAL);
        Toolkit singletonToolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = singletonToolkit.getScreenSize();
        int halfWayX = screenSize.width / 2;
        int halfWayY = screenSize.height / 2;
        int halfWindowWidth = window.getWidth() / 2;
        int halfWindowHeight = window.getHeight() / 2;
        int windowX = halfWayX - halfWindowWidth;
        int windowY = halfWayY - halfWindowHeight;
        window.setLocation(windowX, windowY);
        window.setResizable(false);

    }
    
    public static void main(String[] args) 
    {
        RambleOn app = new RambleOn();
        app.startGame();
    }
}
