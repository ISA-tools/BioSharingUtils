package org.biosharing.utils;

import org.isatools.isacreator.publicationlocator.CiteExploreClient;
import org.isatools.isacreator.publicationlocator.CiteExploreResult;
import org.isatools.isacreator.publicationlocator.NoPublicationFoundException;
import org.isatools.isacreator.publicationlocator.SearchOption;
import uk.ac.ebi.cdb.client.QueryException_Exception;

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
public class PublicationLocator {

    public static final int ABSTRACT = 0;
    public static final int AFFILIATION = 1;
    public static final int AUTHOR = 2;
    public static final int TITLE = 3;

    public List<CiteExploreResult> searchForPublicationByTitle(SearchOption searchOption, String query) {
        CiteExploreClient publicationSearcher = new CiteExploreClient();

        try {
            return publicationSearcher.searchForPublication(searchOption, query);
        } catch (QueryException_Exception qex) {
            System.out.printf("Caught QueryException_Exception: %s\n", qex.getFaultInfo().getMessage());
        } catch (NoPublicationFoundException e) {
            System.out.println("No publication found");
        }

        return new ArrayList<CiteExploreResult>();
    }

    public List<CiteExploreResult> searchForPublication(String... queries) {
        CiteExploreClient publicationSearcher = new CiteExploreClient();

        try {
            String queryURL = "";

            for (String query : queries) {
                if(!queryURL.isEmpty()) {
                    queryURL += " AND ";
                }
                queryURL += SearchOption.TITLE.getQueryString(query) + " OR "
                        + SearchOption.FULL_TEXT.getQueryString(query);
            }
            System.out.println(queryURL);
            return publicationSearcher.performQuery(SearchOption.COMPOSITE, queryURL);
        } catch (QueryException_Exception qex) {
            System.out.printf("Caught QueryException_Exception: %s\n", qex.getFaultInfo().getMessage());
        } catch (NoPublicationFoundException e) {
            System.out.println("No publication found");
        }

        return new ArrayList<CiteExploreResult>();
    }

    public List<CiteExploreResult> filterPublicationsByResultInField(List<CiteExploreResult> results, int filter, String value) {
        List<CiteExploreResult> filteredResults = new ArrayList<CiteExploreResult>();

        for (CiteExploreResult result : results) {
            switch (filter) {
                case ABSTRACT:
                    if (result.getAbstractText() != null && result.getAbstractText().contains(value)) {
                        filteredResults.add(result);
                    }
                    break;
                case AFFILIATION:
                    if (result.getAffiliation() != null && result.getAffiliation().contains(value)) {
                        filteredResults.add(result);
                    }
                    break;
                case AUTHOR:
                    if (result.getAuthors() != null && result.getAuthors().contains(value)) {
                        filteredResults.add(result);
                    }
                    break;
                case TITLE:
                    if (result.getTitle() != null && result.getTitle().contains(value)) {
                        filteredResults.add(result);
                    }
                    break;
                default:
                    filteredResults.add(result);
            }
        }
        return filteredResults;
    }
}
