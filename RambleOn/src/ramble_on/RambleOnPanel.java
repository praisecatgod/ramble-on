
package ramble_on;

import account_data.Score;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JPanel;
import mini_game.Sprite;
import mini_game.SpriteType;
import static ramble_on.RambleOnSettings.*;
import ramble_on.sprites.BoxSprite;



public class RambleOnPanel extends JPanel {
    
    private RambleOn ro;
    private RambleOnDataModel rdm;
    
    public RambleOnPanel(RambleOn initRo, RambleOnDataModel initRdm)
    {
        ro = initRo;
        rdm = initRdm;
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        renderToGraphicsContext(g);
    }

    public void renderToGraphicsContext(Graphics g) 
    {
        renderBackground(g);
        
        switch ( rdm.getGameMode() )
        {
            case WELCOME_MODE_0: renderWelcomeMode(g);
                break;
            case ACCOUNT_LIST_MODE_1: renderAccountsListMode(g);
                break;
            case NEW_ACCOUNT_MODE_2: renderNewAccountMode(g);
                break;
            case SCORE_TREE_MODE_3: renderScoreTreeMode(g);
                break;
            case MAP_VIEW_MODE_4: renderMapViewMode(g);
                break;
            case REGION_PICKER_MODE_5: renderRegionPickerMode(g);
                break;
            case WIN_MODE_6: renderWinMode(g);
                break;
            default:
                break;
                    
        }

        renderNavigationBar(g);

    }
     
    public void renderWelcomeMode(Graphics g)
    {
        Sprite sprite = ro.getGUIButtons().get(WELCOME_DISPLAY_TYPE);
        renderSprite(g, sprite);
        
        g.setColor(INCORRECT_COLOR);
        g.setFont(TITLE_FONT);
        g.drawString("WELCOME TO RAMBLE ON!!", 400, 350);
        g.setColor(STANDARD_GRAY);
        g.setFont(HEADER_FONT);
        g.drawString("- click this panel to continue -", 450, 400);

    }
    
    public void renderAccountsListMode(Graphics g)
    {
        Sprite sprite = ro.getGUIDecor().get(ACCOUNTS_LIST_PANEL_TYPE);
        renderSprite(g, sprite);
        
        sprite = ro.getGUIButtons().get(SELECT_BUTTON_TYPE);
        renderSprite(g, sprite);
        
        sprite = ro.getGUIButtons().get(NEW_BUTTON_TYPE);
        renderSprite(g, sprite);
    }
    
    public void renderNewAccountMode(Graphics g)
    {
       Sprite sprite = ro.getGUIDecor().get(NEW_ACCOUNT_PANEL_TYPE);
       renderSprite(g, sprite);
       
       g.setColor(STANDARD_GRAY);
       g.setFont(STANDARD_FONT);
       g.drawString("Please enter a unique account name.", 450, 300);
       
       sprite = ro.getGUIButtons().get(OK_BUTTON_TYPE);
       renderSprite(g, sprite);
       
       sprite = ro.getGUIButtons().get(CANCEL_BUTTON_TYPE);
       renderSprite(g, sprite);

    }
    
    public void renderScoreTreeMode(Graphics g)
    {
      
        
      Sprite s = ro.getGUIDecor().get(LOGO_TYPE);
      renderSprite(g, s);
      
       s = ro.getGUIButtons().get(MAPVIEW_TYPE);
       renderSprite(g, s);
           
     if (rdm.getCurrentRegionNode()!=null)
     {
        g.setColor(INCORRECT_COLOR);
        g.setFont(TITLE_FONT);
        
        g.drawString(rdm.getCurrentRegionNode().getName(), 450, 100);
        Iterator<Score> scores = rdm.getCurrentRegionNode().getScoreIterator();
        int y = 150;
        int x = 100;
        g.setColor(STANDARD_GRAY);
        for(int i=0;i<4;i++)
        {
            if(scores.hasNext())
            {
                g.setFont(HEADER_FONT);
                Score temp = scores.next();
                g.drawString(temp.getType(), x, y);
                g.setFont(STANDARD_FONT);
                y += 20;
                g.drawString("Times Played: "+temp.getTimesPlayed(), x, y);
                y += 20;
                g.drawString("High Score: "+temp.getHighScore(), x, y);
                y += 20;
                g.drawString("Fastest Time: "+rdm.getSecondsAsTimeText(temp.getFastTime()), x, y);
                y = 150;
                x += 200;
            }

        }
        
      }
    }
     
    public void renderMapViewMode(Graphics g) 
    {
        
        Sprite s = ro.getGUIDecor().get(LOGO_TYPE);
        renderSprite(g, s);
        
        renderMap(g);    
        

        s = ro.getGUIButtons().get(SUBREGION_TYPE);
        renderSprite(g, s);
  
        s = ro.getGUIButtons().get(CAPITAL_TYPE);
        renderSprite(g, s);

        s = ro.getGUIButtons().get(LEADER_TYPE);
        renderSprite(g, s);
   
        s = ro.getGUIButtons().get(FLAG_TYPE);
        renderSprite(g, s);
   
    }

    public void renderRegionPickerMode(Graphics g) 
    {
        
        Sprite s;
        renderMap(g);    
       
        renderSubRegionStack(g);
        g.setColor(STANDARD_GRAY);
        g.setFont(TITLE_FONT);
        g.drawString(rdm.getGDM().getName(), 75, 640);
        
        
       
        g.setColor(STANDARD_GRAY);
        g.setFont(STANDARD_FONT);

         String statsText = rdm.getGameTimeText() 
            + "    Regions Found: " + rdm.getRegionsFound()
            + "    Regions Left: " + rdm.getRegionsNotFound()
            + "    Incorrect Guesses: " + rdm.getIncorrectGuesses();
            
         g.drawString(statsText, 75, 655);
         
         s = ro.getGUIDecor().get(LOGO_TYPE);
        renderSprite(g, s);
        
    }

    public void renderWinMode(Graphics g) 
    {
        Sprite s = ro.getGUIButtons().get(WELCOME_DISPLAY_TYPE);
        renderSprite(g, s);
        
        g.setColor(INCORRECT_COLOR);
        g.setFont(TITLE_FONT);
        g.drawString("CONGRATULATIONS, "+rdm.getCurrentAccount().getName().toUpperCase()+"!!!", 400, 350);
        g.setColor(STANDARD_GRAY);
        g.drawString("Time: "+rdm.getGameWinDurationText(), 400, 400);
        g.drawString("Score: " + rdm.calculateScore(), 400, 450);

    }
    
    public void renderBackground(Graphics g) 
    {
        Sprite sprite = ro.getGUIDecor().get(BACKGROUND_TYPE);
        renderSprite(g, sprite);
    }
    
    public void renderNavigationBar(Graphics g)
    {
        Sprite sprite;
        switch ( rdm.getGameMode() )
        {
         case REGION_PICKER_MODE_5: 
             /// game pause///
             /// music pause ///
         case WIN_MODE_6: 
            sprite = ro.getGUIButtons().get(AAA_MAP_NAVIGATION_BUTTON_TYPE);
            renderSprite(g, sprite);
         case MAP_VIEW_MODE_4: 
            sprite = ro.getGUIButtons().get(AAA_SCORE_TREE_BUTTON_TYPE);
            renderSprite(g, sprite);
         /*case SCORE_TREE_MODE_3: 
             sprite = ro.getGUIButtons().get(ACCOUNT_LIST_BUTTON_TYPE);
            renderSprite(g, sprite);*/
         default:
            sprite = ro.getGUIButtons().get(EXIT_BUTTON_TYPE);
            renderSprite(g, sprite);
            break;      
        }
    }
    
    public void renderMap(Graphics g)
    {
        Sprite s = ro.getGUIDecor().get(MAP_BACKGROUND_TYPE);
        
        if(s!=null)
        {
           renderSprite(g, s);
           s = ro.getGUIButtons().get(MAP_TYPE);
           renderSprite(g, s);

        }
         

    }
    
    public void renderSubRegionStack(Graphics g)
    {
        switch( rdm.getRegionPickerMode())
        {
            case SUBREGION_TYPE:
            case CAPITAL_TYPE:
                renderTextStack(g);
                break;
            case LEADER_TYPE:
                renderTextImageStack(g);
                break;
            case FLAG_TYPE:
                renderImageStack(g);
                break;
        }
    }
    
    public void renderTextStack(Graphics g)
    {
        
        LinkedList<BoxSprite> list = rdm.getSubRegionStack();
        Iterator<BoxSprite> it = list.iterator();
        String text;
        int counter = 0;
        while (it.hasNext() && (counter < 10) )
         {
            BoxSprite bs = it.next();
            text = bs.getTextToRender();
                int x = (int)(bs.getX());
                int y = (int)(bs.getY());
                Color fillColor = STANDARD_GRAY;
                Color textColor = Color.WHITE;
                if (counter == 0)
                {
                    fillColor = Color.PINK;
                    textColor = CORRECT_COLOR;
                }
                g.setColor(fillColor);
                g.fill3DRect(x, y, 150, 50, true);
                g.setColor(textColor);
                g.drawString(text, x, y + 40);   
                
               
                counter++;
                
            
         }
    }
    
    public void renderTextImageStack(Graphics g)
    {
        LinkedList<BoxSprite> list = rdm.getSubRegionStack();
        Iterator<BoxSprite> it = list.iterator();
        String text;
        int counter = 0;
        while (it.hasNext() && (counter < 10))
         {
            BoxSprite bs = it.next();
            text = bs.getTextToRender();
            BufferedImage img = bs.getImageToRender();
            int x = (int)(bs.getX());
            int y = (int)(bs.getY());
            Color textColor = Color.WHITE;
            Color fillColor = STANDARD_GRAY;
            if (counter == 0)
            {
              textColor = CORRECT_COLOR;

            }
            g.setColor(fillColor);
            g.fill3DRect(x, y+150, 200, 25, true);
            g.drawImage(img, x, y, this);
            g.setColor(textColor);
            g.drawString(text, x, y + 170);



            counter++;
         }
    }
        
    public void renderImageStack(Graphics g)
    {
        LinkedList<BoxSprite> list = rdm.getSubRegionStack();
        Iterator<BoxSprite> it = list.iterator();
        String text;
        int counter = 0;
        while (it.hasNext() && (counter < 4))
         {
            BoxSprite bs = it.next();
            BufferedImage img = bs.getImageToRender();
            int x = (int)(bs.getX());
            int y = (int)(bs.getY());
            
            g.drawImage(img, x, y, this);              
            counter++;
         }
        
    }
    public void renderSprite(Graphics g, Sprite s) 
    {
        if (!s.getState().equals(INVISIBLE_STATE)) 
        {
            SpriteType bgST = s.getSpriteType();
            Image img = bgST.getStateImage(s.getState());
            g.drawImage(img, (int) s.getX(), (int) s.getY(), bgST.getWidth(), bgST.getHeight(), null);
        }
    }


}
