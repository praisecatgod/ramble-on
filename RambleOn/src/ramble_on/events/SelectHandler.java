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



public class SelectHandler implements ActionListener
{
    private RambleOn ro;
    public SelectHandler(RambleOn initRo)
    {
        ro = initRo;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {

       if( ( (RambleOnDataModel) ro.getDataModel() ).getGameMode().equals(ACCOUNT_LIST_MODE_1) )
       {
           if ( ( (RambleOnDataModel) ro.getDataModel() ).selectAccount(ro) )
           {
                 ( (RambleOnDataModel) ro.getDataModel() ).goToScoreTreeMode(ro);
           }
       }

    }
}
