package org.biosharing.dao;

import org.biosharing.model.Standard;
import org.junit.Test;

import java.util.Map;

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

        System.out.println("We have " + defaultNodes.size() + " standards in the db.");
    }
}
