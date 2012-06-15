package org.biosharing.dao;

import org.biosharing.model.Standard;
import org.biosharing.model.StandardFields;
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

import static junit.framework.Assert.assertTrue;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 22/05/2012
 *         Time: 14:15
 */
public class DAOTest {

    @Test
    public void testQuery() {
        BioSharingDAO dao = new BioSharingDAO();
        Map<String, Standard> defaultNodes = dao.getStandardNodeInformation();

        assertTrue("Oh, we have no standards. I was expecting some.", defaultNodes.size() > 0);
    }
}
