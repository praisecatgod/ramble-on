/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ramble_on.sprites;

import java.awt.image.BufferedImage;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;

/**
 *
 * @author yv
 */
public class BoxSprite extends Sprite {
    
   private String nameID;
   private String textToRender;
   private BufferedImage imageToRender;

    
  public BoxSprite(
                 String initID,
                 SpriteType initSpriteType,
		 float initX, 	float initY,
		 float initVx, 	float initVy,
		 String initState)
    {   
        super(initSpriteType, initX, initY, initVx, initVy, initState);
        nameID = initID;
    }
  
    public String getNameID() { return nameID; }
  
    public String getTextToRender() { return textToRender; }
    
    public void setTextToRender(String initTextToRender)
    {
        textToRender = initTextToRender;
    }
    
    public BufferedImage getImageToRender() { return imageToRender; }
    
    public void setImageToRender(BufferedImage initImageToRender)
    {
        imageToRender = initImageToRender;
    }
    
    @Override
    	public void update(MiniGame game)
	{
		// MOVE THE SPRITE USING ITS VELOCITY
		x += vX;
		y += vY;
		
		// AND CLAMP IT IF IT WENT OUT OF BOUNDS FIRST ON X-AXIS
		if (x < game.getBoundaryLeft())
			x = game.getBoundaryLeft();
		else if ((x + spriteType.getWidth()) > game.getBoundaryRight())
			x = game.getBoundaryRight() - spriteType.getWidth();


	}
    
}
