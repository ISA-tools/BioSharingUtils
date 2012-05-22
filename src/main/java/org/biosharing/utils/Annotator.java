package org.biosharing.utils;

import org.isatools.isacreator.ontologiser.logic.impl.AnnotatorSearchClient;
import org.isatools.isacreator.ontologymanager.bioportal.model.AnnotatorResult;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 22/05/2012
 *         Time: 11:47
 */
public class Annotator {

    public  Map<String, Map<String, AnnotatorResult>> searchForMatches(Set<String> freeText) {
        AnnotatorSearchClient sc = new AnnotatorSearchClient();

        Map<String, Map<String, AnnotatorResult>> result = sc.searchForTerms(freeText, "", true);

        int termsWithMatches = 0;
        for (String key : result.keySet()) {
            if(result.get(key).size() > 0) {
                termsWithMatches++;
            }
        }

        System.out.println(termsWithMatches+ "/" + freeText.size() + " terms have matches.");

        return result;
    }
}
