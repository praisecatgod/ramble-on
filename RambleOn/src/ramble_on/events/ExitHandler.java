/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ramble_on.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ramble_on.RambleOn;

public class ExitHandler implements ActionListener
{
    private RambleOn game;
    
    public ExitHandler(RambleOn initGame)
    {
        game = initGame;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        game.killApplication();
    }
}