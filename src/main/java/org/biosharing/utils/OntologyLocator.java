package org.biosharing.utils;

import org.biosharing.model.Standard;
import org.biosharing.model.StandardFields;
import org.isatools.isacreator.configuration.Ontology;
import org.isatools.isacreator.ontologymanager.BioPortalClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 22/05/2012
 *         Time: 11:08
 */
public class OntologyLocator {

    public List<Standard> getAllOntologies() {
        BioPortalClient client = new BioPortalClient();
        
        List<Ontology> ontologies = client.getAllOntologies(true);
        
        List<Standard> standards = new ArrayList<Standard>();
        for(Ontology ontology : ontologies) {
            Standard standard = new Standard();
            standard.getFieldToValue().put(StandardFields.STANDARD_TITLE, ontology.getOntologyAbbreviation());
            standard.getFieldToValue().put(StandardFields.FULL_NAME, ontology.getOntologyDisplayLabel());
            standard.getFieldToValue().put(StandardFields.TYPE, "terminology artifact");
            standard.getFieldToValue().put(StandardFields.DOMAIN, "");
            standard.getFieldToValue().put(StandardFields.ORGANIZATION_URL, ontology.getHomepage());
            standard.getFieldToValue().put(StandardFields.CONTACT, ontology.getContactName());
            // Here we should check for available publications...
            standard.getFieldToValue().put(StandardFields.PUBLICATION_TITLE, "");
            standard.getFieldToValue().put(StandardFields.PUBLICATION_URL, "");
            standards.add(standard);
        }

        return standards;
    }
}
