/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import game_io.GameIO;
import gameplay_data.GameDataManager;
import gameplay_data.SubRegion;
import java.io.File;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yv
 */
public class GameImportExport_TestBed {
    
    
    
    public GameImportExport_TestBed() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        

    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
                
    }
    
    @After
    public void tearDown() {
                System.out.println();
    }
    
    @Test
    public void testRegion()
    {
        System.out.println("--- TESTING REGION LOADING ---");

        GameDataManager data = new GameDataManager();
        
        File schema = new File("data/The World/RegionData.xsd");
        System.out.println("Schema "+schema.getName()+" Exists: "+schema.exists());
        Assert.assertTrue(schema.exists());

        GameIO io = new GameIO(schema);
        data.setDataImportExport(io);
        
        File file = new File("data/The World/Asia");
        System.out.println("Region directory "+file.getName()+" Exists: "+file.exists());
        Assert.assertTrue(file.exists());
        File fileXML = new File(file+"/"+file.getName()+" Data.xml");
        System.out.println("Region XML file "+fileXML.getName()+" Exists: "+fileXML.exists());
        Assert.assertTrue(fileXML.exists());
        
        data.load(file);
        System.out.println("Loaded GameDataManager with region data!");
        System.out.println("Region Name: "+data.getName());
        Assert.assertEquals(data.getName(), "Asia");
        System.out.println("SubRegion list size: "+data.getAllRegions().size());
        Assert.assertTrue(data.getAllRegions().size()>0);
        

    }

    @Test
    public void testSubRegions() 
    {   
        System.out.println("--- TESTING SUBREGIONS ---");
        GameDataManager data = new GameDataManager();
        File schema = new File("data/The World/RegionData.xsd");
        GameIO io = new GameIO(schema);
        data.setDataImportExport(io);
        File file = new File("data/The World/Asia");
        data.load(file);
        
        Iterator<SubRegion> subregions = data.getAllRegions().iterator();
        SubRegion temp;
        while(subregions.hasNext())
        {
          temp = subregions.next();
          
          System.out.println(temp.getName() +"has color: "+
                               "R"+temp.getColor().getRed()+
                                "/G"+temp.getColor().getGreen()+
                                "/B"+temp.getColor().getBlue());
          Assert.assertNotNull( temp.getName() );
          Assert.assertNotNull( temp.getColor() );
        }
            
            }
    
    @Test
    public void testCapitalMode()
    {
        System.out.println("--- TESTING CAPITAL MODE ---");
        GameDataManager data = new GameDataManager();
        File schema = new File("data/The World/RegionData.xsd");
        GameIO io = new GameIO(schema);
        data.setDataImportExport(io);
        File file = new File("data/The World/Asia");
        data.load(file);

         System.out.println("Capital mode availible: "+data.hasCapitalMode());

        Iterator<SubRegion> subregions = data.getAllRegions().iterator();
        SubRegion temp;

        while(subregions.hasNext())
        {
          temp = subregions.next();
          
          System.out.println(temp.getName()+": "+temp.getCapital());
          Assert.assertNotNull( temp.getCapital() );

        }
    }
        @Test
    public void testLeaderMode()
    {
        System.out.println("--- TESTING LEADER MODE ---");
        GameDataManager data = new GameDataManager();
        File schema = new File("data/The World/RegionData.xsd");
        GameIO io = new GameIO(schema);
        data.setDataImportExport(io);
        File file = new File("data/The World/Asia");
        data.load(file);
        System.out.println("Flag mode availible: "+data.hasLeaderMode());
        Iterator<SubRegion> subregions = data.getAllRegions().iterator();
        SubRegion temp;
        while(subregions.hasNext())
        {
          temp = subregions.next(); 
          System.out.println(temp.getName()+": "+temp.getLeader());
          Assert.assertNotNull( temp.getLeader());
          System.out.println(temp.getLeaderImage());
          Assert.assertNotNull( temp.getLeaderImage() );
        }
    }
    
    @Test
    public void testFlagMode()
    {
        System.out.println("--- TESTING FLAG MODE ---");
        GameDataManager data = new GameDataManager();
        File schema = new File("data/The World/RegionData.xsd");
        GameIO io = new GameIO(schema);
        data.setDataImportExport(io);
        File file = new File("data/The World/Asia");
        data.load(file);
        System.out.println("Flag mode availible: "+data.hasFlagMode());
        Iterator<SubRegion> subregions = data.getAllRegions().iterator();
        SubRegion temp;
        while(subregions.hasNext())
        {
          temp = subregions.next();
          System.out.println(temp.getName()+": "+temp.getFlag());
          Assert.assertNotNull( temp.getFlag() );
        }
    }
    
    @Test
    public void testSubRegionDirectories()
    {
        System.out.println("--- TESTING SUBREGION DIRECTORIES ---");
        GameDataManager data = new GameDataManager();
        File schema = new File("data/The World/RegionData.xsd");
        GameIO io = new GameIO(schema);
        data.setDataImportExport(io);
        File file = new File("data/The World/Asia");
        data.load(file);
        
        Iterator<SubRegion> subregions = data.getAllRegions().iterator();
        SubRegion temp;

        while(subregions.hasNext())
        {
          temp = subregions.next();
          System.out.println( temp.getName()+": "+temp.hasDirectory() );
          Assert.assertNotNull( temp.getSubDirectory() );


        }
    

}
    
    
}