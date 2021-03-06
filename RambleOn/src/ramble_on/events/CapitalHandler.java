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
public class CapitalHandler implements ActionListener {
    
    private RambleOn ro;
    public CapitalHandler(RambleOn initRo)
    {
        ro = initRo;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {

      if( ( (RambleOnDataModel) ro.getDataModel() ).getGameMode().equals(MAP_VIEW_MODE_4) )
      {
          ( (RambleOnDataModel) ro.getDataModel() ).goToRegionPickerMode(ro, CAPITAL_TYPE);
      }
        
    }
    
}