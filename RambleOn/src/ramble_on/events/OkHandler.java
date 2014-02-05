
package ramble_on.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ramble_on.RambleOn;
import ramble_on.RambleOnDataModel;
import static ramble_on.RambleOnSettings.*;


public class OkHandler implements ActionListener {

    private RambleOn ro;
    
    public OkHandler(RambleOn initRo)
    {
        ro = initRo;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
      if( ( (RambleOnDataModel) ro.getDataModel() ).getGameMode().equals(NEW_ACCOUNT_MODE_2) )
      {
         if( ( (RambleOnDataModel) ro.getDataModel() ).makeNewAccount(ro) )
         {
             ( (RambleOnDataModel) ro.getDataModel() ).goToScoreTreeMode(ro);
         }
      }

    }
    
}
