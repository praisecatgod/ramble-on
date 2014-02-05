
package account_data;

import java.io.Serializable;
import java.util.Collection;
import java.util.TreeMap;

public class Account implements Serializable {

    private String name;
    private RegionNode treeRoot;
    private TreeMap<String, RegionNode> allRegions;


    public Account(String initName) {
        allRegions = new TreeMap();
        name = initName;
        
        treeRoot = new RegionNode("The World", "The World");
         allRegions.put(treeRoot.getId(), treeRoot);
        
    }
    
   public Collection<RegionNode> getAllRegions()
   {
       return allRegions.values();
   }
   
    public RegionNode getRegion(String regionId)
    {
        return allRegions.get(regionId);
    }
    
     public boolean hasRegion(RegionNode testRegion)
    {
        return (allRegions.containsKey(testRegion.getId()));
    }
     
    public void addRegion(RegionNode regionToAdd, RegionNode parentRegion)
    {
        allRegions.put(regionToAdd.getId(), regionToAdd);
        if (parentRegion != null)
        {
            parentRegion.addChild(regionToAdd);
            regionToAdd.setParent(parentRegion);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RegionNode getTreeRoot() {
        return treeRoot;
    }

    public void setTreeRoot(RegionNode treeRoot) {
        
         allRegions.put(treeRoot.getId(), treeRoot);

        this.treeRoot = treeRoot;
    }
}
