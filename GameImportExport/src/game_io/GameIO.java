package game_io;

import gameplay_data.DataImportExport;
import gameplay_data.GameDataManager;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import xml_utilities.XMLUtilities;
import static game_io.GameIOSettings.*;
import java.awt.Color;
import java.util.ArrayList;
import org.w3c.dom.NamedNodeMap;
import gameplay_data.SubRegion;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import xml_utilities.InvalidXMLFileFormatException;

/**
 * This class serves as a plugin for reading and writing game data to and from
 * XML files using RegionData.xsd.
 *
 * @author Yvonne DeSousa CSE 219 S13
 * @version 1.0
 */
public class GameIO implements DataImportExport {

    private XMLUtilities xmlUtil;
    private File regionDataSchema;

    /**
     * Constructor for making importer/exporter.
     */
    public GameIO(File initRegionDataSchema) {
        xmlUtil = new XMLUtilities();
        regionDataSchema = initRegionDataSchema;
    }

    /**
     * Reads the geographic data found in regionsFile into game.
     *
     * @param regionsFile The directory to load the data from.
     *
     * @param game The game data to fill with the data from the XML file.
     *
     * @return true if the game data loads successfully, false otherwise.
     */
    @Override
    public boolean loadWorld(File regionsFile, GameDataManager game) {


        try {
          game.setIsValid(true);

            Document doc = xmlUtil.loadXMLDocument(regionsFile.getAbsolutePath() + "/"
                    + regionsFile.getName() + " Data.xml",
                    regionDataSchema.getAbsolutePath());
            loadGameplayData(regionsFile, doc, game);

        } catch (InvalidXMLFileFormatException fileNotFound) {
            game.setIsValid(false);
            return false;

        }
        return true;
    }

    /**
     * Private helper method for loading game data. This method loads the
     * complete list of regions into the game data.
     *
     * @param regionsFile The directory to load the data from.
     *
     * @param doc The subregion data loaded from an XML file into a Document.
     *
     * @param game The data manager for all the regions.
     */
    private void loadGameplayData(File regionsFile, Document doc, GameDataManager game) {

        Node regionNode = doc.getElementsByTagName(REGION_NODE).item(0);
        game.setName(regionsFile.getName());
        
        try 
        {
            getRegionMap(regionsFile, game);
        }
        catch (DataFileNotFoundException e) 
        {
            game.setIsValid(false);
        }
        try 
        {
            getWinAnthem(regionsFile, game);
        }
        catch (DataFileNotFoundException e) 
        {
            
        }
             ArrayList<Node> subRegions = xmlUtil.getChildNodesWithName(regionNode, SUBREGION_NODE);
            getSubRegions(regionsFile, game, subRegions);






    }

    /**
     * Finds the subregions' data of a region and adds it to the game data.
     * @param regionsFile The directory to load the data from.
     * @param game The data manager for all the regions.
     * @param subRegions ArrayList of SubRegion values.
     */
    private void getSubRegions(File regionsFile, GameDataManager game, ArrayList<Node> subRegions) {
        SubRegion temp;
        Node subRegionNode;
        NamedNodeMap regionAttributeMap;
        game.setHasCapitalMode(true);
        game.setHasFlagMode(true);
        game.setHasLeaderMode(true);

        for (int i = 0; i < subRegions.size(); i++) {

            temp = new SubRegion();
            subRegionNode = subRegions.get(i);
            regionAttributeMap = subRegionNode.getAttributes();

            try {
                setSubRegionName(temp, regionAttributeMap);
                setSubRegionColor(temp, regionAttributeMap);
            } catch (InvalidDataException e)
              {
                game.setIsValid(false);
              }

            try {
                setSubRegionCapital(temp, regionAttributeMap);
            } catch (InvalidDataException e) {
                game.setHasCapitalMode(false);
            }

            try {
                setSubRegionLeader(temp, regionAttributeMap);
                setSubRegionLeaderImage(temp, regionsFile);
            } catch (DataFileNotFoundException | InvalidDataException e) {
                game.setHasLeaderMode(false);
            }


            try {
                setSubRegionFlagImage(temp, regionsFile);
            } catch (DataFileNotFoundException | InvalidDataException e) {
                game.setHasFlagMode(false);
            }

            try {
                setSubDirectory(temp, regionsFile);
            } catch (DataFileNotFoundException e) {
                temp.setHasDirectory(false);
            }

            game.addRegion(temp);


        }
    }

    private void getWinAnthem(File regionsFile, GameDataManager game)
            throws DataFileNotFoundException
    {
        try
        {
         File song = new File(regionsFile + "/" + regionsFile.getName()+ " National Anthem.mid");
          if(!song.exists())
              throw new DataFileNotFoundException("CAN NOT FIND MAP");
          
          else
              game.setAudioDir(song.getPath());
        }
        catch (Exception dataNotFound) {
            throw new DataFileNotFoundException("CAN NOT FIND NATIONAL ANTHEM");

        }
    }
    private void getRegionMap(File regionsFile, GameDataManager game)
            throws DataFileNotFoundException
    {
        try
        {
          File img = new File(regionsFile + "/" + regionsFile.getName()+ " Map.png");
          if(!img.exists())
              throw new DataFileNotFoundException("CAN NOT FIND MAP");
          
          BufferedImage map = ImageIO.read(img);
          
          
          game.setMap(map);
          
        }
        catch (Exception dataNotFound) {
            throw new DataFileNotFoundException("CAN NOT FIND MAP");

        }
        
    }
    /**
      * Finds the name of a region and adds it to the SubRegion object.
     * @param subRegion Subregion to find and add name.
     * @param subRegionMap Nodes of region to find value for.
     * @throws InvalidDataException Thrown if XML data not found or invalid.
     */
    private void setSubRegionName(SubRegion subRegion, NamedNodeMap subRegionMap)
            throws InvalidDataException {
        try {
            String name = subRegionMap.getNamedItem(NAME_TAG).getNodeValue();
            subRegion.setName(name);
        } catch (Exception dataNotFound) {
            throw new InvalidDataException("CAN NOT FIND NAME!");

        }


    }

    /**
     * Finds the color of a region and adds it to the SubRegion object.
     * @param subRegion Subregion to find and add color.
     * @param subRegionMap Nodes of region to find value for.
     * @throws InvalidDataException Thrown if XML data not found or invalid.
     */
    private void setSubRegionColor(SubRegion subRegion, NamedNodeMap subRegionMap)
            throws InvalidDataException {

        try {
            int red = Integer.parseInt(subRegionMap.getNamedItem(RED_TAG).getNodeValue());
            int green = Integer.parseInt(subRegionMap.getNamedItem(GREEN_TAG).getNodeValue());
            int blue = Integer.parseInt(subRegionMap.getNamedItem(BLUE_TAG).getNodeValue());

            if ((red < 0 || red > 255)
                    || (green < 0 || green > 255)
                    || (blue < 0 || blue > 255)) {
                throw new InvalidDataException("COLOR INVALID!");
            } else {
                Color color = new Color(red, green, blue);
                subRegion.setColor(color);

            }
        } catch (Exception dataNotFound) {
            throw new InvalidDataException("CAN NOT FIND COLOR");

        }




    }

    /**
     * Finds the capital of a region and adds it to the SubRegion object.
     * @param subRegion Subregion to find and add capital.
     * @param subRegionMap Nodes of region to find value for.
     * @throws InvalidDataException Thrown if XML data not found or invalid.
     */
    private void setSubRegionCapital(SubRegion subRegion, NamedNodeMap subRegionMap)
            throws InvalidDataException {
        try {
            String capital = subRegionMap.getNamedItem(CAPITAL_TAG).getNodeValue();
            subRegion.setCapital(capital);
        } catch (Exception dataNotFound) {
            throw new InvalidDataException("CAN NOT FIND CAPITAL");

        }

    }

    /**
     * Finds the leader of a region and adds it to the SubRegion object.
     * @param subRegion Subregion to find and add leader.
     * @param subRegionMap Nodes of region to find value for.
     * @throws InvalidDataException Thrown if XML data not found or invalid.
     */
    private void setSubRegionLeader(SubRegion subRegion, NamedNodeMap subRegionMap)
            throws InvalidDataException {
        try {
            String leader = subRegionMap.getNamedItem(LEADER_TAG).getNodeValue();
            subRegion.setLeader(leader);
        } catch (Exception dataNotFound) {
            throw new InvalidDataException("CAN NOT FIND LEADER");

        }

    }

    /**
     * Finds the flag image of a region and adds it to the SubRegion object.
     * @param subRegion Subregion to find and add flag image.
     * @param regionsFile The directory to load the data from.
     * @throws DataFileNotFoundException Thrown if file data not found or invalid.
     * @throws InvalidDataException Thrown if XML data not found or invalid.
     */
    private void setSubRegionFlagImage(SubRegion subRegion, File regionsFile)
            throws DataFileNotFoundException, InvalidDataException {

        try {
            
          File img = new File(regionsFile + "/" + subRegion.getName() + " Flag.jpg");
          if(!img.exists())
              throw new DataFileNotFoundException("CAN NOT FIND FLAG");
          
          BufferedImage flag = ImageIO.read(img);

          if (flag.getWidth() != 200) 
            throw new InvalidDataException("FLAG FILE HAS INVALID SIZE");

            subRegion.setFlag(flag);
            
        } catch (Exception fileNotFound) {
            throw new DataFileNotFoundException("CAN NOT FIND FLAG FILE");
        }

    }

    /**
     * Finds the leader image of a region and adds it to the SubRegion object.
     * @param subRegion Subregion to find and add leader image.
     * @param regionsFile The directory to load the data from.
     * @throws DataFileNotFoundException Thrown if file data not found or invalid.
     */
    private void setSubRegionLeaderImage(SubRegion subRegion, File regionsFile)
            throws DataFileNotFoundException, InvalidDataException {
        try {
            
          File img = new File(regionsFile + "/" + subRegion.getName() + " Leader.jpg");
          if(!img.exists())
              throw new InvalidDataException("CAN NOT FIND LEADER IMAGE");
          
          BufferedImage leader = ImageIO.read(img);

          if (leader.getWidth() != 100 || leader.getHeight() != 150) 
               throw new InvalidDataException("LEADER FILE HAS INVALID SIZE");

            subRegion.setLeaderImage(leader);
  
                    } 
        catch (Exception fileNotFound) {
            throw new DataFileNotFoundException("CAN NOT FIND LEADER FILE");
        }

    }

    /**
     * Finds the subdirectory of a region and adds it to the SubRegion object.
     * @param subRegion Subregion to find and add subdirectory.
     * @param regionsFile The directory to load the data from.
     * @throws DataFileNotFoundException Thrown if file data not found or invalid.
     */
    private void setSubDirectory(SubRegion subRegion, File regionsFile)
            throws DataFileNotFoundException {
        try {
            File subDirectory = new File(regionsFile + "/" + subRegion.getName());
            if (!subDirectory.exists()) {
                throw new DataFileNotFoundException("CAN NOT FIND SUBDIRECTORY");
            }

            subRegion.setSubDirectory(subDirectory);
        } catch (Exception DataFileNotFoundException) {
            throw new DataFileNotFoundException("CAN NOT FIND LEADER FILE");
        }

    }
}
