package org.biosharing.cli.tasks;

import org.junit.Test;

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
    public void testOntologyUpdate() {
        Task publicationTask = new OntologyUpdateTask();
        publicationTask.performTask();
    }
}
