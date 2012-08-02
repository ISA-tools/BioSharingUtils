package org.biosharing.categoryloader;

import org.biosharing.bioportaltasks.OntologyCategoryLoader;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 11/07/2012
 *         Time: 16:39
 */
public class OntologyCategoryLoaderTest {

    @Test
    public void testCategoryLoader() {
        OntologyCategoryLoader categoryLoader = new OntologyCategoryLoader();
        Map<String, String> categoryIdToName = categoryLoader.getCategoryIdToCategoryNameMap();

        assertTrue("Should have had more than one category back but I don't", categoryIdToName.size() > 0);
    }
}
