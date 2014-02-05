/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ramble_on.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import ramble_on.RambleOn;
import ramble_on.RambleOnDataModel;
import static ramble_on.RambleOnSettings.*;

public class HotkeyHandler implements KeyListener
{
    private RambleOn ro;
    
    public HotkeyHandler(RambleOn initRo)
    {
        ro = initRo;
    }

    @Override
    public void keyTyped(KeyEvent ke){}

    @Override
    public void keyReleased(KeyEvent ke){}
    
    @Override
    public void keyPressed(KeyEvent ke)
    {
        int keyCode = ke.getKeyCode();
        if (keyCode == KeyEvent.VK_C && ( (RambleOnDataModel) ro.getDataModel() ).getGameMode().equals(REGION_PICKER_MODE_5))
        {
            try
            {    
                ro.beginUsingData();
                RambleOnDataModel dataModel = (RambleOnDataModel)(ro.getDataModel());
                dataModel.removeAllButOneFromeStack(ro);         
            }
            finally
            {
                ro.endUsingData();
            }
        }
        if (keyCode == KeyEvent.VK_A)
        {
            if(ro.getAudio().isPlaying(GAME_PLAY))
            {
               ro.getAudio().stop(GAME_PLAY);

            }

            if( ( (RambleOnDataModel) ro.getDataModel() ).getGameMode().equals(WIN_MODE_6) )
            {
                if(ro.getAudio().isPlaying(DEFAULT_WIN))
                {
                    ro.getAudio().stop(DEFAULT_WIN);
                }
                else if(ro.getAudio().isPlaying("ANTHEM"))
                {
                     ro.getAudio().stop ("ANTHEM");
                }
            }

            ro.getDataModel().endGameAsWin();
          ( (RambleOnDataModel) ro.getDataModel() ).goToScoreTreeMode(ro);
        }
        
        
    }
    
    
}
