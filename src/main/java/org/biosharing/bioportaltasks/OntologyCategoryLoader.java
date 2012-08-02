package org.biosharing.bioportaltasks;

import org.isatools.isacreator.ontologymanager.BioPortalClient;
import org.w3c.dom.NodeList;
import uk.ac.ebi.utils.io.DownloadUtils;
import uk.ac.ebi.utils.xml.XPathReader;

import javax.xml.xpath.XPathConstants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 11/07/2012
 *         Time: 16:20
 */
public class OntologyCategoryLoader {

    private static final String URL = "http://rest.bioontology.org/bioportal/categories";

    public Map<String, String> getCategoryIdToCategoryNameMap() {

        downloadFile();

        Map<String, String> conceptIdToConcept = new HashMap<String, String>();
        XPathReader reader;
        try {
            reader = new XPathReader(new FileInputStream(downloadFile()));

            NodeList categoryBeans = (NodeList) reader.read("/success/data/list/categoryBean", XPathConstants.NODESET);

            if (categoryBeans.getLength() > 0) {
                for (int categoryIndex = 0; categoryIndex < categoryBeans.getLength(); categoryIndex++) {
                    String categoryId = (String) reader.read("/success/data/list/categoryBean[" + categoryIndex + "]/id", XPathConstants.STRING);
                    String categoryName = (String) reader.read("/success/data/list/categoryBean[" + categoryIndex + "]/name", XPathConstants.STRING);

                    if(!categoryId.isEmpty()) {
                        conceptIdToConcept.put(categoryId, categoryName);
                    }
                }
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        return conceptIdToConcept;
    }

    private String downloadFile() {
        String downloadLocation = DownloadUtils.DOWNLOAD_FILE_LOC + "ontology-categories" + DownloadUtils.XML_EXT;
        DownloadUtils.downloadFile(URL + "?" + BioPortalClient.API_KEY, downloadLocation);

        return downloadLocation;
    }
}
