package org.biosharing.dao;

import org.biosharing.model.Standard;
import org.biosharing.utils.Annotator;
import org.biosharing.utils.OntologyLocator;
import org.biosharing.utils.PublicationLocator;
import org.isatools.isacreator.ontologymanager.bioportal.model.AnnotatorResult;
import org.isatools.isacreator.publicationlocator.CiteExploreResult;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 22/05/2012
 *         Time: 14:15
 */
public class DAOTest {

    @Ignore
    public void testQuery() {
        BioSharingDAO dao = new BioSharingDAO();
        Map<String, Standard> defaultNodes = dao.getStandardNodeInformation();

        for (String standard : defaultNodes.keySet()) {
            System.out.println(defaultNodes.get(standard));
        }
    }

    @Ignore
    public void loadAllOntologiesIntoDB() {
        BioSharingDAO dao = new BioSharingDAO();
        // this will let us know what hasn't already been added
        Map<String, Standard> standards = dao.getStandardNodeInformation();

        OntologyLocator ontologyLocator = new OntologyLocator();
        List<Standard> ontologies = ontologyLocator.getAllOntologies();

        for (Standard ontology : ontologies) {
            if (!standards.containsKey(ontology.getStandard())) {
                dao.insertStandard(ontology);
            }
        }

        dao.closeConnection();
    }

    @Ignore
    public void getDomainsAndAnnotate() {
        BioSharingDAO dao = new BioSharingDAO();
        // this will let us know what hasn't already been added
        Map<String, Standard> standards = dao.getStandardNodeInformation();

        Set<String> domains = new HashSet<String>();

        for (Standard standard : standards.values()) {
            System.out.println("Processing " + standard + ", domain is " + standard.getDomain());
            domains.add(standard.getDomain());
        }

        Annotator annotator = new Annotator();
        Map<String, Map<String, AnnotatorResult>> matches = annotator.searchForMatches(domains);

        for (String domain : matches.keySet()) {
            System.out.println(domain);
            for (String match : matches.get(domain).keySet()) {
                System.out.println("\t" + match);
            }
        }
    }

    @Test
    public void getStandardsAndFindPublications() {
        BioSharingDAO dao = new BioSharingDAO();
        // this will let us know what hasn't already been added
        Map<String, Standard> standards = dao.getStandardNodeInformation();

        PublicationLocator publicationLocator = new PublicationLocator();

        for (Standard standard : standards.values()) {
            if (standard.getPublication().isEmpty()) {
                System.out.println("Locating publications for " + standard.getStandard());

                List<CiteExploreResult> publications = publicationLocator.searchForPublication(standard.getStandard(), standard.getFullName());

                if (publications.size() > 0) {
                    System.out.println("We have " + publications.size() + " publications for " + standard.getStandard());

                    dao.updateStandard(standard, publications.get(0));
                }
            }
        }


    }

}
