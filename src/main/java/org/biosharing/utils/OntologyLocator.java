package org.biosharing.utils;

import org.biosharing.bioportaltasks.OntologyCategoryLoader;
import org.biosharing.model.Standard;
import org.biosharing.model.StandardFields;
import org.isatools.isacreator.configuration.Ontology;
import org.isatools.isacreator.ontologymanager.BioPortalClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 22/05/2012
 *         Time: 11:08
 */
public class OntologyLocator {

    public static final String BIOPORTAL_ONTOLOGY_URL = "http://bioportal.bioontology.org/ontologies/";

    public List<Standard> getAllOntologies() {
        BioPortalClient client = new BioPortalClient();
        OntologyCategoryLoader categoryLoader = new OntologyCategoryLoader();

        List<Ontology> ontologies = client.getAllOntologies(true);
        // load category names
        Map<String, String> categoryIdToName = categoryLoader.getCategoryIdToCategoryNameMap();

        List<Standard> standards = new ArrayList<Standard>();
        if (ontologies != null) {
            System.out.println("Found " + ontologies.size() + " in BioPortal.");
            for (Ontology ontology : ontologies) {

                if (!ontology.getOntologyAbbreviation().contains("test")) {
                    Standard standard = new Standard();

                    standard.initialiseStandard();

                    standard.getFieldToValue().put(StandardFields.STANDARD_TITLE, ontology.getOntologyAbbreviation());
                    standard.getFieldToValue().put(StandardFields.FULL_NAME, ontology.getOntologyDisplayLabel());
                    standard.getFieldToValue().put(StandardFields.STANDARD_URL, BIOPORTAL_ONTOLOGY_URL + ontology.getOntologyID());
                    standard.getFieldToValue().put(StandardFields.MIBBI, "no");
                    standard.getFieldToValue().put(StandardFields.OBO, "no");
                    standard.getFieldToValue().put(StandardFields.TYPE, "terminology artifact");
                    standard.getFieldToValue().put(StandardFields.ORGANIZATION_URL, ontology.getHomepage());
                    standard.getFieldToValue().put(StandardFields.CONTACT, ontology.getContactName());

                    String categoriesForOntology = processOntologyCategories(ontology, categoryIdToName);

                    System.out.printf("Categories for %s are %s\n", ontology.getOntologyAbbreviation(), categoriesForOntology);
                    standard.getFieldToValue().put(StandardFields.DOMAIN, categoriesForOntology);

                    // Here we should check for available publications...
                    standards.add(standard);
                }
            }
        } else {
            System.out.println("Problem encountered with BioPortal. Please try again later.");
        }
        System.out.println("After filtering, we have " + standards.size() + " ontologies in BioPortal.");
        return standards;
    }

    private String processOntologyCategories(Ontology ontology, Map<String, String> categoryIdToName) {
        StringBuilder categoryNames = new StringBuilder();

        int count = 0;
        for (String category : ontology.getCategories()) {
            if (categoryIdToName.containsKey(category)) {
                categoryNames.append(categoryIdToName.get(category).trim());

                if (count != ontology.getCategories().size() - 1 && !categoryNames.toString().isEmpty()) {
                    categoryNames.append(",");
                }

                count++;
            }

        }

        return categoryNames.toString();
    }


}
