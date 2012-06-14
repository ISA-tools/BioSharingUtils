package org.biosharing.cli.tasks;

import org.biosharing.dao.BioSharingDAO;
import org.biosharing.model.Standard;
import org.biosharing.utils.PublicationLocator;
import org.isatools.isacreator.publicationlocator.CiteExploreResult;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 14/06/2012
 *         Time: 15:18
 */
public class PublicationAssignmentTask extends Task {

    public PublicationAssignmentTask() {
        super("Publication update task");
    }

    @Override
    public void doTask() {
        BioSharingDAO dao = new BioSharingDAO();
        // this will let us know what hasn't already been added
        Map<String, Standard> standards = dao.getStandardNodeInformation();

        PublicationLocator publicationLocator = new PublicationLocator();

        for (Standard standard : standards.values()) {
            if (standard.getPublication().isEmpty()) {
                System.out.println("Locating publications for " + standard.getStandardTitle());

                List<CiteExploreResult> publications = publicationLocator.searchForPublication(standard.getStandardTitle(), standard.getFullName());

                if (publications.size() > 0) {
                    System.out.println("We have " + publications.size() + " publications for " + standard.getStandardTitle());
                    System.out.println("");

                    int publicationCount = 1;
                    for (CiteExploreResult result : publications) {
                        System.out.println("");
                        System.out.println(publicationCount + " " + result.getTitle() + "\n  " + result.getAuthors() + "\n  Pubmed ID: " +result.getId() + " DOI: " + result.getDoi());
                        System.out.println("");
                        publicationCount++;
                    }

                    int option = requestOption(0);

                    if (option > publicationCount) {
                        requestOption(0);
                    }

                    if (option != 0) {
                        dao.updateStandardWithPublication(standard, publications.get(option - 1));
                    }
                }
            }
        }
    }

    private int requestOption(int cycleCount) {
        System.out.println("\n---------------------------------------------------------------");
        System.out.println("Choose a publication or enter 0 to skip addition at this time:");

        Scanner inputstr = new Scanner(in);
        try {
            return Integer.valueOf(inputstr.nextLine());
        } catch (NumberFormatException nfe) {
            if (cycleCount < 3) {
                System.out.println("Incorrect option specified. You have one more chance. Enter 0 to skip.");
                requestOption(cycleCount + 1);
            }
        }
        return 0;
    }
}
