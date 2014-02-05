package ramble_on;

import account_data.*;
import game_io.GameIO;
import gameplay_data.GameDataManager;
import gameplay_data.SubRegion;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import mini_game.MiniGame;
import mini_game.MiniGameDataModel;
import mini_game.Sprite;
import mini_game.SpriteType;
import static ramble_on.RambleOnSettings.*;
import ramble_on.events.ScoreTreeHandler;
import ramble_on.sprites.BoxSprite;



public class RambleOnDataModel extends MiniGameDataModel {
    
    private String gameMode;

    
    /// ACCOUNTS ///
    private AccountFileManager fileManager;
    private JList accountList;
    private DefaultListModel accountListModel;
    private JScrollPane accountListScrollPane;
    private Account currentAccount;

    /// MAKING ACCOUNTS ///
    private JTextField newAccountTextField;

    
    /// TREE DISPLAY ///
    private JTree scoreTree;
    private JScrollPane scoreTreeScrollPane;
    private RegionNode currentNode;
    
    
    /// MAP VIEW ///
    private File homeDirectory;
    private File schema;
    private GameDataManager gdm;
    private File currentDirectory;
    
    /// REGION PICKER ///
    private String regionPickerMode;
    private GregorianCalendar startTime;
    private GregorianCalendar winEndingTime;
    private int incorrectGuesses;
    private LinkedList<BoxSprite> subRegionStack;    

    
    /// MAP REFERENCE ///
    private HashMap<Color, String> colorToSubRegionMappings;
    private HashMap<String, Color> subRegionToColorMappings;
    private HashMap<String, ArrayList<Point2D.Double>> pixels;
    private LinkedList<String> redSubRegions;
    




    public RambleOnDataModel() 
    {
        colorToSubRegionMappings = new HashMap();
        subRegionToColorMappings = new HashMap();
        pixels = new HashMap();
        redSubRegions = new LinkedList();
        subRegionStack = new LinkedList();

    }
        
    public String getGameMode()
    {
        return gameMode;
    }
    
        public void setGameMode(String mode)
    {
       gameMode = mode;
    }
    
    public void goToWelcomeMode(RambleOn game)
    {
        //((RambleOn) game).getAudio().play(DEFAULT_WIN, false);
        
        gameMode = WELCOME_MODE_0;

    }
    
    public void goToAccountListMode(RambleOn game)
    {
        game.getCanvas().removeAll();
        gameMode = ACCOUNT_LIST_MODE_1;
        
        currentAccount = null;
        
        fileManager = new AccountFileManager(game);
        fileManager.loadAccounts();
        
        accountListModel = new DefaultListModel();
        accountList = new JList(accountListModel);
        accountList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        accountList.setLayoutOrientation(JList.VERTICAL);
        

        for (int i = 0; i < fileManager.getFiles().size(); i++) 
        {
             accountListModel.add(i, fileManager.getFiles().get(i).getName());
        }
        accountListScrollPane = new JScrollPane(accountList);
        accountList.setBounds(475, 200, 250, 300);
        accountListScrollPane.setBounds(475, 200, 250, 300);

        game.getCanvas().add(accountListScrollPane);

    }
    
    public void goToNewAccountMode(RambleOn game)
    {
        game.getCanvas().removeAll();
        
        gameMode = NEW_ACCOUNT_MODE_2;
        
        newAccountTextField = new JTextField();
        newAccountTextField.setBounds(500, 325, 200, 30);
        newAccountTextField.setEditable(true);
        game.getCanvas().add(newAccountTextField);
        
    }
    
    public void noAccountError(MiniGame game)
    {   
        ((RambleOn) game).getAudio().play(INCORRECT, false);
        JLabel error = new JLabel();
        error.setForeground(INCORRECT_COLOR);
        error.setFont(STANDARD_FONT);
        switch(gameMode)
        {
            case ACCOUNT_LIST_MODE_1:
                error.setText("PLEASE SELECT AN ACCOUNT!");
                error.setBounds(475, 160, 250, 50);
                break;
            case NEW_ACCOUNT_MODE_2:
                error.setText("PLEASE SELECT A UNIQUE NAME!");
                error.setBounds(475, 290, 400, 50);
                break;
            default:
                break;
        }
        game.getCanvas().add(error);

    }
            
    
    public boolean selectAccount(MiniGame game)
    {

        int index = accountList.getSelectedIndex();

        if(index == -1 || accountList.isSelectionEmpty())
        {
            noAccountError(game);
            return false;
        }
        try 
        {
            FileInputStream fileIn = new FileInputStream(fileManager.getFiles().get(index));
            ObjectInputStream in = new ObjectInputStream(fileIn);
            currentAccount = (Account) in.readObject();
            in.close();
            fileIn.close();
        } 
        catch (IOException i) 
        {
            i.printStackTrace();
            return false;
        } 
        catch (ClassNotFoundException c) 
        {
            c.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public boolean makeNewAccount(MiniGame game)
    {
        String name = newAccountTextField.getText();
        if(accountListModel.contains(name) || name.trim().isEmpty() )
        {
            noAccountError(game);
            return false;
        }
        else
        {
          currentAccount = fileManager.newFile(name);
          return true;
        }

    }
    
    public void goToScoreTreeMode(RambleOn game)
    {
        game.getCanvas().removeAll();
        ScoreTreeHandler sth = new ScoreTreeHandler(game);
        TreeModel model = new RegionTreeModel(currentAccount.getTreeRoot());
        scoreTree = new JTree(model);
        scoreTree.addTreeSelectionListener(sth);
        scoreTreeScrollPane = new JScrollPane(scoreTree);
        scoreTree.setSelectionRow(0);
        scoreTree.setBounds(970, 220, 200, 375);
        scoreTreeScrollPane.setBounds(970, 220, 200, 375);
        game.getCanvas().add(scoreTreeScrollPane);
        gameMode = SCORE_TREE_MODE_3;
        
        
    }
    
    public Account getCurrentAccount()
    { return currentAccount;}
    
    public void goToMapViewMode(RambleOn game)
    {
        game.getCanvas().removeAll();
        gdm = new GameDataManager();
        homeDirectory = new File(DIRECTORY_PATH);
        schema = new File(DIRECTORY_PATH + SCHEMA_FILE);
        currentDirectory = new File(DIRECTORY_PATH);
        GameIO io = new GameIO(schema);
        gdm.setDataImportExport(io);
        
        reloadMapView(game, homeDirectory);
        
        gameMode = MAP_VIEW_MODE_4;
 
    }
    
    public void reloadMapView(RambleOn game, File directory)
    {   
        colorToSubRegionMappings.clear();
        subRegionToColorMappings.clear();
        
        gdm.reset();
        
        currentDirectory = directory;
        gdm.load(currentDirectory);
        
        if(gdm.isValid())
        {
            game.loadMapSprite();
        
        Iterator<SubRegion> subregions = gdm.getAllRegions().iterator();
        SubRegion temp;
        while (subregions.hasNext()) 
        {
            temp = subregions.next();
            colorToSubRegionMappings.put(temp.getColor(), temp.getName());
            subRegionToColorMappings.put(temp.getName(), temp.getColor());
        }
        
        Sprite map = game.getGUIButtons().get(MAP_TYPE);
        SpriteType mapType = map.getSpriteType();
        BufferedImage img = mapType.getStateImage(map.getState());
        
        
         for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                int rgb = img.getRGB(i, j);
                Color c = new Color(rgb);
                if (colorToSubRegionMappings.containsKey(c)) {
                    String subRegion = colorToSubRegionMappings.get(c);
                    pixels.put(subRegion, new ArrayList());
                    ArrayList<Point2D.Double> subRegionPixels = pixels.get(subRegion);
                    subRegionPixels.add(new Point2D.Double(i, j));
                    if (!gdm.getSubRegion(subRegion).hasDirectory()) 
                    {
                        changeSubRegionColorOnMap(game, subRegion, INCORRECT_COLOR );
                        redSubRegions.add(subRegion);
                    }
                }
            }
        }
        
        }
        
        
    }
    
    public void goToRegionPickerMode(RambleOn game, String newRegionPickerMode)
    {
        if(game.getGUIButtons().get(newRegionPickerMode).getState().equals(INACTIVE_STATE))
            return;
        
        game.getCanvas().removeAll();

        colorToSubRegionMappings.clear();
        subRegionToColorMappings.clear();
        redSubRegions.clear();
        subRegionStack.clear();

        
        gdm.reset();
        
        gdm.load(currentDirectory);
        
        if(gdm.isValid())
        {
            regionPickerMode = newRegionPickerMode;
            ((RambleOn) game).loadBoxSpriteType(regionPickerMode);
            game.loadMapSprite();
        
            Iterator<SubRegion> subregions = gdm.getAllRegions().iterator();
            SubRegion temp;
            
            SpriteType sT = this.getSpriteType(BOX_TYPE);
            int x = 975;
            
            
            while (subregions.hasNext()) 
            {
                temp = subregions.next();
                colorToSubRegionMappings.put(temp.getColor(), temp.getName());
                subRegionToColorMappings.put(temp.getName(), temp.getColor());
                
                BoxSprite subRegionSprite = new BoxSprite(temp.getName(), sT, x, 0, 0, 0, VISIBLE_STATE);
                switch (regionPickerMode)
                {
                    case SUBREGION_TYPE:
                        subRegionSprite.setTextToRender(temp.getName());
                        break;
                    case CAPITAL_TYPE:
                        subRegionSprite.setTextToRender(temp.getCapital());
                        break;
                    case LEADER_TYPE:
                        subRegionSprite.setTextToRender(temp.getLeader());
                        subRegionSprite.setImageToRender(temp.getLeaderImage());
                        break;
                    case FLAG_TYPE:
                        subRegionSprite.setImageToRender(temp.getFlag());
                        break;
                    default:
                        break;
                }
                subRegionStack.add(subRegionSprite);
            } 
            

        Collections.shuffle(subRegionStack);
        
        float y = 0;
        float yInc = 0;
        
        switch(regionPickerMode)
         {
           case SUBREGION_TYPE:
           case CAPITAL_TYPE:
                y = 600;
                yInc = 50;
                break;
           case LEADER_TYPE:
                y = 340;
                yInc = 180;
                break;
           case FLAG_TYPE:
                y = 400;
                yInc = 150;
                break;
           default:
                break;
          }

        int count = 0;
        // NOW FIX THEIR Y LOCATIONS
        for (BoxSprite ts : subRegionStack)
        {
            float tY = y+yInc;            
            ts.setY(tY);

            count++;
            switch(regionPickerMode)
            {
                case SUBREGION_TYPE:
                case CAPITAL_TYPE:
                    yInc -=50;
                    break;
                case LEADER_TYPE:
                    yInc -= 180;
                    break;
                case FLAG_TYPE:
                    yInc -= 150;
                    break;
                default:
                    break;
                    
            }        
        
        }
        
        pixels = new HashMap();
        for (BoxSprite tS : subRegionStack)
        {
            pixels.put(tS.getNameID(), new ArrayList());
        }
        
        Sprite map = game.getGUIButtons().get(MAP_TYPE);
        SpriteType mapType = map.getSpriteType();
        BufferedImage img = mapType.getStateImage(map.getState());
        for (int i = 0; i < img.getWidth(); i++) 
        {
            for (int j = 0; j < img.getHeight(); j++) 
            {
                int rgb = img.getRGB(i, j);
                Color c = new Color(rgb);
                if (colorToSubRegionMappings.containsKey(c)) 
                {
                    String subRegion = colorToSubRegionMappings.get(c);
                    ArrayList<Point2D.Double> subRegionPixels = pixels.get(subRegion);
                    subRegionPixels.add(new Point2D.Double(i, j));

                }
            }
        }
            
            startTime = new GregorianCalendar();
            winEndingTime = null;

            incorrectGuesses = 0;

            //game.getAudio().play(GAME_PLAY, true);
            gameMode = REGION_PICKER_MODE_5;
            
            //updateAll(game);
            beginGame();
        }
    }
    
    public void goToWinMode(RambleOn game)
    {
        ((RambleOn) game).getAudio().stop(GAME_PLAY);
        winEndingTime = new GregorianCalendar();
        this.endGameAsWin();
        if(!gdm.getAudioDir().equals(""))
        {
           try
            {
                ((RambleOn) game).getAudio().loadAudio("ANTHEM", gdm.getAudioDir());
                ((RambleOn) game).getAudio().play("ANTHEM", false);
            }
        catch(Exception e)
            {
            }
        }
        else
        {
          ((RambleOn) game).getAudio().play(DEFAULT_WIN, false);
        }


        
        if(currentAccount.getRegion(gdm.getName())!=null)
        {
           tryScore( currentAccount.getRegion(gdm.getName()) );

        }
        else
        {
            RegionNode newAccount = new RegionNode(gdm.getName(), gdm.getName());
            tryScore(newAccount);
            File directoryPath = gdm.getFile();
            String paths[] = directoryPath.toString().split("/");
            RegionNode parent;
            RegionNode child;
            for(int i=2;i<paths.length-1;i++)
            {
                if(currentAccount.getRegion(paths[i])==null)
                 {
                    child = new RegionNode(paths[i], paths[i]);
                    parent = currentAccount.getRegion(paths[i-1]);
                    currentAccount.addRegion(child, parent);
                 }
            }
            parent = currentAccount.getRegion(paths[paths.length-2]);
            currentAccount.addRegion(newAccount, parent);

        }
        File file = new File(ACCOUNTS_PATH+currentAccount.getName());
        fileManager.saveFile(file, currentAccount);
        
        gameMode = WIN_MODE_6;
    }
    
    public void tryScore(RegionNode s)
    {
        long numMilliseconds = winEndingTime.getTimeInMillis() - startTime.getTimeInMillis();
        long numSeconds = numMilliseconds/1000L;
        
        if(s.hasScore(regionPickerMode))
           {
              s.getScore(regionPickerMode).setTimesPlayed(
                  s.getScore(regionPickerMode).getTimesPlayed() + 1);
                
              if( s.getScore(regionPickerMode).getHighScore() <= this.calculateScore())
                 {
                    s.getScore(regionPickerMode).setHighScore(this.calculateScore());
                 }
              if( s.getScore(regionPickerMode).getFastTime() > numSeconds)
                 {
                    s.getScore(regionPickerMode).setFastTime(numSeconds);
                 }
           }
           else
            {
                Score temp = new Score(regionPickerMode, 1, 
                this.calculateScore(), numSeconds);
                
                  s.addScore(temp);
                  
            }
    }
    
    public void respondToMapSelection(RambleOn game)
    {
        Sprite map = game.getGUIButtons().get(MAP_TYPE);
        SpriteType mapType = map.getSpriteType();
        BufferedImage img = mapType.getStateImage(map.getState());
        int rgb = img.getRGB(lastMouseX, lastMouseY);
        Color pixelColor = new Color(rgb);
        String clickedSubRegion = colorToSubRegionMappings.get(pixelColor); 
        
        if ((clickedSubRegion == null) || (subRegionStack.isEmpty()))
        {
            return;
        }
        
        if (clickedSubRegion.equals(subRegionStack.get(0).getNameID()))
        {
            // YAY, CORRECT ANSWER
            ((RambleOn) game).getAudio().play(CORRECT, false);
            
            // TURN THE TERRITORY GREEN
            changeSubRegionColorOnMap(((RambleOn) game), clickedSubRegion, CORRECT_COLOR);
            
            // REMOVE THE BOTTOM ELEMENT FROM THE STACK
            subRegionStack.removeFirst();
            
            // AND LET'S CHANGE THE RED ONES BACK TO THEIR PROPER COLORS
            for (String s : redSubRegions)
            {
                if(s!=null)
                {
                  Color subRegionColor = subRegionToColorMappings.get(s);
                  changeSubRegionColorOnMap(((RambleOn) game), s, subRegionColor);
                }

            }
            redSubRegions.clear();
            
            startStackSpritesMovingDown();
            
            if (subRegionStack.isEmpty())
            {
                goToWinMode(game);

            }
        }
        else
        {
            if (!redSubRegions.contains(clickedSubRegion))
            {
                // BOO WRONG ANSWER
                ((RambleOn) game).getAudio().play(INCORRECT, false);
                incorrectGuesses++;
            
                // TURN THE TERRITORY TEMPORARILY RED
                changeSubRegionColorOnMap(((RambleOn) game), clickedSubRegion, INCORRECT_COLOR);
                redSubRegions.add(clickedSubRegion);
            }
        }
    }
    
    public String getRegionPickerMode()
    {
        return regionPickerMode;
    }
    
     public void changeSubRegionColorOnMap(RambleOn game, String subRegion, Color color) 
     {
        Sprite map = game.getGUIButtons().get(MAP_TYPE);
        SpriteType mapType = map.getSpriteType();
        BufferedImage img = mapType.getStateImage(map.getState());
        ArrayList<Point2D.Double> subRegionPixels = pixels.get(subRegion);
        for (Point2D.Double p : subRegionPixels)
        {
            int rgb= color.getRGB();
            img.setRGB((int)(p.x), (int)(p.y), rgb);
        }

    }
     

    
    
        public void startStackSpritesMovingDown()
    {
            for (Sprite s : subRegionStack)
            {
                s.setVy(SUB_STACK_VELOCITY);
            }        
    }
        public String getSecondsAsTimeText(long numSeconds)
    {
        long numHours = numSeconds/3600;
        numSeconds = numSeconds - (numHours * 3600);
        long numMinutes = numSeconds/60;
        numSeconds = numSeconds - (numMinutes * 60);
        
        String timeText = "";
        if (numHours > 0)
        {
            timeText += numHours + ":";
        }
        timeText += numMinutes + ":";
        if (numSeconds < 10)
        {
            timeText += "0" + numSeconds;
        }
        else
        {
            timeText += numSeconds;
        }
        return timeText;
    }
      
    public String getGameTimeText()
    {
        if (startTime == null)
        {
            return "";
        }
        GregorianCalendar now = new GregorianCalendar();
        long diff = now.getTimeInMillis() - startTime.getTimeInMillis();
        long numSeconds = diff/1000L;

        return getSecondsAsTimeText(numSeconds);
    }
    
    public int getIncorrectGuesses() { return incorrectGuesses; }

    
    public int calculateScore()
    {
        int points = MAX_SCORE;
        long numMilliseconds = winEndingTime.getTimeInMillis() - startTime.getTimeInMillis();
        long numSeconds = numMilliseconds/1000L;
        points -= numSeconds;
        points -= (100 * incorrectGuesses);
        return points;
    }
    
    public String getGameWinDurationText()
    {
        long numMilliseconds = winEndingTime.getTimeInMillis() - startTime.getTimeInMillis();
        long numSeconds = numMilliseconds/1000L;
        return getSecondsAsTimeText(numSeconds);
    }
    
    public int getRegionsFound()
    {
        return colorToSubRegionMappings.keySet().size() - subRegionStack.size();
    }
    
    public int getRegionsNotFound()
    {
        return subRegionStack.size();
    }
    
    public LinkedList<BoxSprite> getSubRegionStack() { return subRegionStack; }
	
    public void addColorToSubRegionMappings(Color colorKey, String subRegionName)
    {
        colorToSubRegionMappings.put(colorKey, subRegionName);
    }
    
    public String getSubRegionMappedToColor(Color colorKey)
    {
        return colorToSubRegionMappings.get(colorKey);
    }
    
    public void addSubRegionToColorMappings(String subRegionName, Color colorKey)
    {
        subRegionToColorMappings.put(subRegionName, colorKey);
    }
    
    public Color getColorMappedToSubRegion(String subRegion)
    {
        return subRegionToColorMappings.get(subRegion);
    }
    
    /// RESPOND TO CLICKS ///
    public void rightClickMapView(RambleOn game)
    {
        if ( !currentDirectory.getName().equals(homeDirectory.getName()) ) 
        {
            reloadMapView(game, currentDirectory.getParentFile());
        }
    }
    
    public void leftClickMapView(RambleOn game)
    {
        Sprite map = game.getGUIButtons().get(MAP_TYPE);
        SpriteType mapType = map.getSpriteType();
        BufferedImage img = mapType.getStateImage(map.getState());
        int rgb = img.getRGB(lastMouseX, lastMouseY);
        Color pixelColor = new Color(rgb);
        String clickedSubRegion = colorToSubRegionMappings.get(pixelColor);        
        if ((clickedSubRegion != null) && gdm.getSubRegion(clickedSubRegion).hasDirectory()) 
        {
            reloadMapView(game, gdm.getSubRegion(clickedSubRegion).getSubDirectory());
        }
    }
    
    public GameDataManager getGDM()
    {
        return gdm;
    }
    
    public void respondToTreeSelection(RambleOn game)
    {
        TreePath selectedPath = scoreTree.getSelectionPath();
         if (selectedPath != null) 
         {
             currentNode = (RegionNode) selectedPath.getLastPathComponent();
         }
    }
    
    public RegionNode getCurrentRegionNode()
    {
        return currentNode;
    }
    @Override
    public void checkMousePressOnSprites(MiniGame game, int x, int y) 
    {

    }

    @Override
    public void reset(MiniGame game) 
    {
       //gameMode = WELCOME_MODE_0;

    }

    @Override
    public void updateAll(MiniGame game) 
    {
        
        if (!((RambleOn) game).getAudio().isPlaying(GAME_PLAY))
        {
            ((RambleOn) game).getAudio().play(GAME_PLAY, true);
        }
          
       for (Sprite s : subRegionStack)
       {
         s.update(game);
       }
         if (!subRegionStack.isEmpty())
            {
                Sprite bottomOfStack = subRegionStack.get(0);
                int boxHeight = 0;
                switch(regionPickerMode)
                {
                    case SUBREGION_TYPE:
                    case CAPITAL_TYPE:
                        boxHeight = 650;
                        break;
                    case LEADER_TYPE:
                        boxHeight = 520;
                        break;
                    case FLAG_TYPE:
                        boxHeight = 550;
                        break;
                    default:
                        break;
                }
                if (bottomOfStack.getY() == boxHeight)
                {
                    for (Sprite s : subRegionStack)
                    {
                        s.setVy(0);
                    }
                }
            }
        
    }

    @Override
    public void updateDebugText(MiniGame game) 
    {
      debugText.clear();
    }
    
    public void removeAllButOneFromeStack(RambleOn game)
    {
         for (String s : redSubRegions)
            {
                if(s!=null)
                {
                  Color subRegionColor = subRegionToColorMappings.get(s);
                  changeSubRegionColorOnMap(((RambleOn) game), s, subRegionColor);
                }

            }
       redSubRegions.clear();
        while (subRegionStack.size() > 1)
        {
            BoxSprite tS = subRegionStack.removeFirst();
            String subRegionName = tS.getNameID();
            
            // TURN THE TERRITORY GREEN
            changeSubRegionColorOnMap(game, subRegionName, CORRECT_COLOR);            
        }
        startStackSpritesMovingDown();
    }
    
}
