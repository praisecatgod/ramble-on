package ramble_on.sprites;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;


public class MapSprite extends Sprite {
    
    private MouseListener mouseListener;
    
    public MapSprite(SpriteType initSpriteType,
			float initX, 	float initY,
			float initVx, 	float initVy,
			String initState)
    {
      super(initSpriteType, initX, initY, initVx, initVy, initState);

    }
    
    public void setMouseListener(MouseListener initListener)
    {
	mouseListener = initListener;
    }
    
    @Override
    public boolean testForClick(MiniGame game, int x, int y)
    {
	if (containsPoint(x,y) && (mouseListener != null))
	{
            MouseEvent me;
            me = new MouseEvent(game.getCanvas(), this.getID(), 0, 0, x, y, 0, false, MouseEvent.BUTTON1);
            game.beginUsingData();
            mouseListener.mousePressed(me);
            game.endUsingData();
            return true;
	}
	else
            return false;
    }
    
    public boolean testForRightClick(MiniGame game, int x, int y)
    {
	if (containsPoint(x,y) && (mouseListener != null))
	{
            MouseEvent me;
            me = new MouseEvent(game.getCanvas(), this.getID(), 0, 0, x, y, 0, false, MouseEvent.BUTTON3);
            game.beginUsingData();
            mouseListener.mousePressed(me);
            game.endUsingData();
            return true;
	}
	else
            return false;
    }
}
