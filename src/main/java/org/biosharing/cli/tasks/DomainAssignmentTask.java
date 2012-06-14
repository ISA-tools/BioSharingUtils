package org.biosharing.cli.tasks;

import org.biosharing.dao.BioSharingDAO;
import org.biosharing.model.Standard;
import org.biosharing.utils.Annotator;
import org.isatools.isacreator.ontologymanager.bioportal.model.AnnotatorResult;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 14/06/2012
 *         Time: 15:20
 */
public class DomainAssignmentTask extends Task{

    public DomainAssignmentTask() {
        super("Domain assignment task");
    }

    @Override
    public void doTask() {
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
}
