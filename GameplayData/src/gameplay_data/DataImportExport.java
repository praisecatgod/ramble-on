package gameplay_data;

import java.io.File;

/**
 * This interface provides the requirements for an importer/exporter plugin to
 * be used for loading and saving worlds to an XML file.
 *
 * @author Yvonne DeSousa CSE 219 S13
 * @version 1.0
 */
public interface DataImportExport {

    /**
     * Loads the geographic contents of regionsFile into worldToLoad.
     *
     * @param regionsFile The XML file from which the data will be extracted.
     *
     * @param gameToLoad The data read from the file will be loaded into this
     * world.
     *
     * @return true if the world loaded successfully, false otherwise.
     */
    public boolean loadWorld(File regionsFile, GameDataManager gameToLoad);
}