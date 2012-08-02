package org.biosharing.cli.tasks;

import org.biosharing.model.Standard;
import org.biosharing.utils.OntologyLocator;
import org.isatools.isacreator.configuration.Ontology;
import org.isatools.isacreator.ontologymanager.BioPortalClient;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 14/06/2012
 *         Time: 15:24
 */
public class OntologyUpdateTest {

    @Test
    public void testGetOntologies() {
        BioPortalClient client = new BioPortalClient();

        List<Ontology> ontologies = client.getAllOntologies(true);
        assertTrue("Oh, no ontologies have been retrieved", ontologies != null);
        assertTrue("Oh, no ontologies have been retrieved", ontologies.size() > 0);

        System.out.println("We have " + ontologies.size() + " ontologies in BioPortal.");
    }

    @Ignore
    public void testFilterOntologies() {
        // we want to filter out all ontologies which are 'test' ontologies. These explicitly have test in the id, so it
        // makes them easy to identify.

        OntologyLocator locator = new OntologyLocator();

        List<Standard> standards = locator.getAllOntologies();
        System.out.println("We have " + standards.size() + " filtered ontologies.");
        assertTrue("Oh, no ontologies have been retrieved", standards.size() > 0);
    }

    @Test
    public void testOntologyLoadTask() {
        // we want to filter out all ontologies which are 'test' ontologies. These explicitly have test in the id, so it
        // makes them easy to identify.

        Task task = new OntologyUpdateTask();
        task.performTask();
    }
}
