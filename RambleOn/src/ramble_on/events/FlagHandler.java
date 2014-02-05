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
public class FlagHandler implements ActionListener {
    
    private RambleOn ro;
    public FlagHandler(RambleOn initRo)
    {
        ro = initRo;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
      if( ( (RambleOnDataModel) ro.getDataModel() ).getGameMode().equals(MAP_VIEW_MODE_4) )
      {
          ( (RambleOnDataModel) ro.getDataModel() ).goToRegionPickerMode(ro, FLAG_TYPE);
      }
        
    }
    
}
