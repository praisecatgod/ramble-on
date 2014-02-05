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


public class MapViewHandler implements ActionListener
{
    private RambleOn ro;
    
    public MapViewHandler(RambleOn initRo)
    {
        ro = initRo;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
      if( ( (RambleOnDataModel) ro.getDataModel() ).getGameMode().equals(SCORE_TREE_MODE_3 ) )
      {
          ( (RambleOnDataModel) ro.getDataModel() ).goToMapViewMode(ro);
      }


    }
}
