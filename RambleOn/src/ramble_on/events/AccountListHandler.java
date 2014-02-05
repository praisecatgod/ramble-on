/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ramble_on.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ramble_on.RambleOn;
import ramble_on.RambleOnDataModel;

/**
 *
 * @author yv
 */
public class AccountListHandler implements ActionListener
{
    private RambleOn ro;
    
    public AccountListHandler(RambleOn initRo)
    {
        ro = initRo;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {

          ( (RambleOnDataModel) ro.getDataModel() ).goToAccountListMode(ro);
    }
}
