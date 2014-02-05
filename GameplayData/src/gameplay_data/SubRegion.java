package gameplay_data;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * This class represents a geographic territory that for a graphics-based
 * program. It contains information about the area for use in the game, as well
 * as data for sorting numerous territories of this type, and linking to
 * directories for SubRegions of its own, and/or other assorted data files.
 *
 * @author Yvonne DeSousa CSE 219 S13
 * @version 1.0
 */
public class SubRegion<T extends Comparable<T>> implements Comparable<SubRegion<T>> {

    private String name;
    private Color color;
    private String capital;
    private String leader;
    private BufferedImage leaderImage;
    private BufferedImage flag;
    private File subDirectory;
    private boolean hasDirectory;

    /**
     * Constructor for the object. Left blank due to the potential needs
     * depending on the game.
     */
    public SubRegion() {
    }

    /**
     * Accessor method for getting this region's name.
     *
     * @return The name of this region.
     */
    public String getName() {
        return name;
    }

    /**
     * Mutator method for changing this region's name.
     *
     * @param name Name value to be used for this region.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accessor method for getting this region's color.
     *
     * @return A color value for this region.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Mutator method for changing this region's color.
     *
     * @param color Color value to be used for this region.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Accessor method for getting this region's capital.
     *
     * @return The capital of this region.
     */
    public String getCapital() {
        return capital;
    }

    /**
     * Mutator method for changing this region's capital.
     *
     * @param capital Capital to be used for this region.
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }

    /**
     * Accessor method for getting this region's leader's name.
     *
     * @return The leader of this region.
     */
    public String getLeader() {
        return leader;
    }

    /**
     * Mutator method for changing this region's leader's name.
     *
     * @param leader Leader to be used for this region.
     */
    public void setLeader(String leader) {
        this.leader = leader;
    }

    /**
     * Accessor method for getting this region's leader's image.
     *
     * @return A BufferedImage of this region's leader.
     */
    public BufferedImage getLeaderImage() {
        return leaderImage;
    }

    /**
     * Mutator method for changing the leader's image of the region.
     *
     * @param leaderImage BufferedImage of the regions's leader's image.
     */
    public void setLeaderImage(BufferedImage leaderImage) {
        this.leaderImage = leaderImage;
    }

    /**
     * Accessor method for getting this region's flag.
     *
     * @return A BufferedImage of this region's flag.
     */
    public BufferedImage getFlag() {
        return flag;
    }

    /**
     * Mutator method for changing the flag image of the region.
     *
     * @param flag BufferedImage of the regions's flag.
     */
    public void setFlag(BufferedImage flag) {
        this.flag = flag;
    }

    /**
     * Accessor method for getting this region's subdirectory. The subdirectory
     * is a folder that holds additional data for this SubRegion.
     *
     * @return A File of the region's subdirectory.
     */
    public File getSubDirectory() {
        return subDirectory;
    }

    /**
     * Mutator method for changing the subdirectory of the region.
     *
     * @param subDirectory File to be associated with this region.
     */
    public void setSubDirectory(File subDirectory) {
        this.subDirectory = subDirectory;
        hasDirectory = true;
    }

    /**
     * Accessor method for getting this region's leader's image.
     *
     * @return true if this region has a subdirectory, false if it does not.
     */
    public boolean hasDirectory() {
        return hasDirectory;
    }

    /**
     * Mutator method for changing whether region has subdirectory.
     *
     * @param hasDirectory whether region has a subdirectory.
     */
    public void setHasDirectory(boolean hasDirectory) {
        this.hasDirectory = hasDirectory;
    }

    /**
     * Used for comparing SubRegions for the purpose of sorting them.
     *
     * @param region The Region to be compared to this one.
     *
     * @return 0 if they have the same name, -1 if this Region's name
     * alphabetically precedes it, and 1 if it follows it.
     */
    @Override
    public int compareTo(SubRegion<T> region) {
        return name.compareToIgnoreCase(region.name);
    }

    /**
     * Method for testing equivalence of this region with the regionAsObject
     * argument.
     *
     * @param regionAsObject The region to test for equivalence with this one.
     *
     * @return true if they have the same id, false otherwise.
     */
    public boolean equals(Object regionAsObject) {
        if (regionAsObject instanceof SubRegion) {
            SubRegion region = (SubRegion) regionAsObject;
            return name.equals(region.name);
        }
        return false;
    }

    /**
     * Generates a textual representation of this region.
     *
     * @return The textual representation of this region, which is the name.
     */
    @Override
    public String toString() {
        return name;
    }
}
