/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ramble_on.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import ramble_on.RambleOn;
import ramble_on.RambleOnDataModel;
import static ramble_on.RambleOnSettings.*;


/**
 *
 * @author yv
 */
public class MapClickHandler implements MouseListener {
    
    private RambleOn ro;
    
    public MapClickHandler(RambleOn initRo)
    {
        ro = initRo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
      if( ( (RambleOnDataModel) ro.getDataModel() ).getGameMode().equals(MAP_VIEW_MODE_4) )
      {
        if(e.getButton() == MouseEvent.BUTTON1)
        {

           ( (RambleOnDataModel) ro.getDataModel() ).leftClickMapView(ro);
        }
        else if (e.getButton() == MouseEvent.BUTTON3)
        {

            ( (RambleOnDataModel) ro.getDataModel() ).rightClickMapView(ro);
        }
      }
      
      else if ( ( (RambleOnDataModel) ro.getDataModel() ).getGameMode().equals(REGION_PICKER_MODE_5) )
      {
          ( (RambleOnDataModel) ro.getDataModel() ).respondToMapSelection(ro);
      }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


    
}
