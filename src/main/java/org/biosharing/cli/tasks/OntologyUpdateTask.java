package org.biosharing.cli.tasks;

import org.biosharing.dao.BioSharingDAO;
import org.biosharing.model.Standard;
import org.biosharing.model.StandardFields;
import org.biosharing.utils.OntologyLocator;

import java.util.List;
import java.util.Map;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 14/06/2012
 *         Time: 14:42
 */
public class OntologyUpdateTask extends Task {

    public OntologyUpdateTask() {
        super("Ontology update task");
    }

    @Override
    public void doTask() {
        BioSharingDAO dao = new BioSharingDAO();
        // this will let us know what hasn't already been added
        Map<String, Standard> standards = dao.getStandardNodeInformation();

        OntologyLocator ontologyLocator = new OntologyLocator();
        List<Standard> ontologies = ontologyLocator.getAllOntologies();

        int startingNodeId = getNodeIdStart(standards);
        int count = startingNodeId;
        for (Standard ontology : ontologies) {
            if (!standards.containsKey(ontology.getStandardTitle())) {
                ontology.getFieldToValue().put(StandardFields.SERIAL_ID, count);
                ontology.getFieldToValue().put(StandardFields.NID, count);
                ontology.getFieldToValue().put(StandardFields.VID, count);
                dao.insertStandard(ontology);
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Database is up to date.");
        } else {
            System.out.println((count - startingNodeId) +
                    " ontologies were not in the database before. They have now been added.");
        }

        dao.closeConnection();
    }

    public int getNodeIdStart(Map<String, Standard> standards) {
        int maxValue = 0;

        for (Standard standard : standards.values()) {
            try {
                int nodeId = Integer.valueOf(standard.getNodeId());
                if (nodeId > maxValue) {
                    maxValue = nodeId;
                }
            } catch (NumberFormatException nfe) {
                // skip this node
            }
        }

        return maxValue + 1;
    }
}
