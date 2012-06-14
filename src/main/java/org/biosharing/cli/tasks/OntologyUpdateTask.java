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
public class OntologyUpdateTask extends Task{

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

        int count= 0;
        for (Standard ontology : ontologies) {
            if (!standards.containsKey(ontology.getStandardTitle())) {
                ontology.getFieldToValue().put(StandardFields.SERIAL_ID, count);
                ontology.getFieldToValue().put(StandardFields.NID, count);
                ontology.getFieldToValue().put(StandardFields.VID, count);
                dao.insertStandard(ontology);
                count++;
            }
        }

        dao.closeConnection();
    }
}
