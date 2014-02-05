package gameplay_data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;

/**
 * This is a data management class for SubRegions, providing a means to add,
 * remove, and find regions. It can be used to provide a set of data for
 * gameplay across multiple modes. It represents a main region, composed of
 * numerous SubRegions.
 *
 * @author Yvonne DeSousa CSE 219 S13
 * @version 1.0
 */
public class GameDataManager {

    private String name;
    private HashMap<String, SubRegion> subRegions;
    private DataImportExport dataImportExport;
    private BufferedImage map;
    private String audioDir;
    private boolean hasCapitalMode;
    private boolean hasLeaderMode;
    private boolean hasFlagMode;
    private boolean isValid;
    private File currentFile;

    /**
     * Default constructor, it will initialize all necessary data structures
     * such that xml files may be loaded.
     */
    public GameDataManager() {
        subRegions = new HashMap();
    }
    
    public File getFile()
    { return currentFile; }

    /**
     * Mutator method for changing this GameDataManager's Region's name.
     *
     * @param name Name value to be used for this game's region.
     */
    public void setName(String initName) {
        name = initName;
    }

    /**
     * Accessor method for getting this GameDataManager's Region's name.
     *
     * @return The name of this game's region.
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method for getting this GameDataManager's Region's map.
     *
     * @return The BufferedImage of this game's map.
     */
    public BufferedImage getMap() {
        return map;
    }

    /**
     * Mutator method for changing this GameDataManager's Region's map.
     *
     * @param map BufferedImage to be used for this game's map.
     */
    public void setMap(BufferedImage map) {
        this.map = map;
    }

    /**
     * Accessor method for getting this GameDataManager's Region's audio file's
     * path.
     *
     * @return The string of the desired audio file's path.
     */
    public String getAudioDir() {
        return audioDir;
    }

    /**
     * Mutator method for changing this GameDataManager's Region's audio file's
     * path.
     *
     * @param audioDir String of path to be used for this game's audio.
     */
    public void setAudioDir(String audioDir) {
        this.audioDir = audioDir;
    }

    /**
     * Accessor method for getting this GameDataManager's valid data boolean
     * value.
     *
     * @return true if main data (subregions, color, etc) is valid.
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * Mutator method for changing this GameDataManager's valid data boolean value.
     *
     * @param hasCapitalMode Boolean value of valid game data.
     */
    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * Accessor method for getting this GameDataManager's capital mode boolean
     * value.
     *
     * @return true if mode is availible, false if not.
     */
    public boolean hasCapitalMode() {
        return hasCapitalMode;
    }

    /**
     * Mutator method for changing this GameDataManager's Region's capital
     * mode's boolean value.
     *
     * @param hasCapitalMode Boolean value of game mode.
     */
    public void setHasCapitalMode(boolean hasCapitalMode) {
        this.hasCapitalMode = hasCapitalMode;
    }

    /**
     * Accessor method for getting this GameDataManager's leader mode boolean
     * value.
     *
     * @return true if mode is availible, false if not.
     */
    public boolean hasLeaderMode() {
        return hasLeaderMode;
    }

    /**
     * Mutator method for changing this GameDataManager's Region's leader mode's
     * boolean value.
     *
     * @param hasLeaderMode Boolean value of game mode.
     */
    public void setHasLeaderMode(boolean hasLeaderMode) {
        this.hasLeaderMode = hasLeaderMode;
    }

    /**
     * Accessor method for getting this GameDataManager's flag mode boolean
     * value.
     *
     * @return true if mode is availible, false if not.
     */
    public boolean hasFlagMode() {
        return hasFlagMode;
    }

    /**
     * Mutator method for changing this GameDataManager's Region's flag mode's
     * boolean value.
     *
     * @param hasFlagMode Boolean value of game mode.
     */
    public void setHasFlagMode(boolean hasFlagMode) {
        this.hasFlagMode = hasFlagMode;
    }

    /**
     * Accessor method for getting all the regions currently in the world.
     *
     * @return A Collection containing all the regions currently in the world.
     */
    public Collection<SubRegion> getAllRegions() {
        return subRegions.values();
    }

    /**
     * Accessor method for getting one of the regions in the current world.
     *
     * @param regionName The name for the game data to retrieve.
     *
     * @return The SubRegion that corresponds to the regionName.
     */
    public SubRegion getSubRegion(String regionName) {
        return subRegions.get(regionName);
    }

    /**
     * Tests to see if the testRegion argument is part of the current game data
     * or not.
     *
     * @param testRegion The region to test.
     *
     * @return true if testRegion is in the current game data, false otherwise.
     */
    public boolean hasRegion(SubRegion testRegion) {
        return (subRegions.containsKey(testRegion.getName()));
    }

    /**
     * Adds a region to this game data to the list of all regions
     *
     * @param regionToAdd SubRegion to add to the list of all regions.
     */
    public void addRegion(SubRegion regionToAdd) {
        subRegions.put(regionToAdd.getName(), regionToAdd);
    }

    /**
     * Removes the regionToRemove argument from this game data.
     *
     * @param regionToRemove SubRegion to remove from the game data.
     */
    public void removeRegion(SubRegion regionToRemove) {
        subRegions.remove(regionToRemove.getName());
    }

    /**
     * Removes all of the regions from the game data.
     */
    public void clearRegions() {
        subRegions.clear();
    }

    /**
     * Empties the data of all SubRegions and clears the region data.
     *
     */
    public void reset() {
        clearRegions();
        name = "";
        map = null;
        audioDir = "";
    }

    /**
     * Mutator method for setting the file reader/writer.
     *
     * @param die The XML region file reader/writer object.
     */
    public void setDataImportExport(DataImportExport die) {
        dataImportExport = die;
    }

    /**
     * Loads the contents of fileToLoad into this game.
     *
     * @param fileToLoad XML file that describes a world.
     *
     * @return true if the game data loaded successfully, false otherwise.
     */
    public boolean load(File fileToLoad) {
        currentFile = fileToLoad;
        return dataImportExport.loadWorld(fileToLoad, this);
    }
}
