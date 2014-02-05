/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ramble_on.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ramble_on.RambleOn;
import ramble_on.RambleOnDataModel;
import static ramble_on.RambleOnSettings.*;


/**
 *
 * @author yv
 */
public class MapNavigationHandler implements ActionListener
{
    private RambleOn ro;
    
    public MapNavigationHandler(RambleOn initRo)
    {
        ro = initRo;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {

            //if(ro.getAudio().isPlaying(GAME_PLAY))
            //{
               ro.getAudio().stop(GAME_PLAY);

            //}

            if( ( (RambleOnDataModel) ro.getDataModel() ).getGameMode().equals(WIN_MODE_6) )
            {
                if(ro.getAudio().isPlaying(DEFAULT_WIN))
                {
                    ro.getAudio().stop(DEFAULT_WIN);
                }
                else if(ro.getAudio().isPlaying("ANTHEM"))
                {
                    ro.getAudio().stop("ANTHEM");
                }
            }

            ro.getDataModel().endGameAsWin();
          ( (RambleOnDataModel) ro.getDataModel() ).goToMapViewMode(ro);
    }
}
