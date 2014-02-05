/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ramble_on;

import java.awt.event.MouseEvent;
import mini_game.MiniGame;
import mini_game.MiniGameDataModel;
import mini_game.MiniGameEventRelayer;
import static ramble_on.RambleOnSettings.*;
import ramble_on.sprites.MapSprite;


/**
 *
 * @author yv
 */
public class RambleOnEventRelayer extends MiniGameEventRelayer {

    private MiniGame game;
    public RambleOnEventRelayer(MiniGame initGame) {
        super(initGame);
        game = initGame;
    }

    @Override
    public void mousePressed(MouseEvent me) {
        try {

            // LOCK THE DATA
            game.beginUsingData();

            // GET THE COORDINATES
            int x = me.getX();
            int y = me.getY();
            
            
            if(me.getButton() == MouseEvent.BUTTON3)
            {
                ((RambleOn) game).processRightButtonPress(x, y);

            }



        } finally 
        {
            // RELEASE THE DATA SO THAT THE TIMER THREAD MAY
            // APPROPRIATELY UPDATE AND RENDER THE GAME
            // WITHOUT INTERFERENCE
            game.endUsingData();
        }
    }
}
