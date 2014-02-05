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


public class WelcomeHandler implements ActionListener
{
    private RambleOn ro;
    public WelcomeHandler(RambleOn initRo)
    {
        ro = initRo;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
       if( ( (RambleOnDataModel) ro.getDataModel() ).getGameMode().equals(WELCOME_MODE_0) )
       {

          ( (RambleOnDataModel) ro.getDataModel() ).goToAccountListMode(ro);
       }
      else if( ( (RambleOnDataModel) ro.getDataModel() ).getGameMode().equals(WIN_MODE_6) )
      {

          ( (RambleOnDataModel) ro.getDataModel() ).goToAccountListMode(ro);
      }
    }
}
