/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ramble_on.events;

import account_data.RegionNode;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import ramble_on.RambleOn;
import ramble_on.RambleOnDataModel;

/**
 *
 * @author yv
 */
public class ScoreTreeHandler implements TreeSelectionListener {
    
    RambleOn ro;
        
    
    public ScoreTreeHandler(RambleOn initRo)
    {
       ro = initRo;
    }
    @Override
    public void valueChanged(TreeSelectionEvent tse) {
       RegionNode selectedNode = 
               (RegionNode)tse.getPath().getLastPathComponent();
       
      ( (RambleOnDataModel) ro.getDataModel() ).respondToTreeSelection(ro);
    }
    
}
