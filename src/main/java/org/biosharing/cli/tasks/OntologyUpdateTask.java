package org.biosharing.cli.tasks;

import org.biosharing.model.Alias;
import org.biosharing.model.Node;
import org.biosharing.model.Standard;
import org.biosharing.model.StandardFields;
import org.biosharing.utils.OntologyLocator;

import java.util.List;
import java.util.Map;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 14/06/2012
 *         Time: 14:42
 */
public class OntologyUpdateTask extends Task {

    public OntologyUpdateTask() {
        super("Ontology update task");
    }

    @Override
    public void doTask() {

        // this will let us know what hasn't already been added
        Map<String, Standard> standards = dao.getStandardNodeInformation();

        OntologyLocator ontologyLocator = new OntologyLocator();
        List<Standard> ontologies = ontologyLocator.getAllOntologies();

        System.out.println("Getting next node id.");
        int startingNodeId = getNextNodeId();
        System.out.println(startingNodeId);

        System.out.println("Getting next computed node id.");
        int startingComputedId = getLastComputedId(standards);
        System.out.println(startingComputedId);

        int count = startingNodeId;

        System.out.println("Going to add " + ontologies.size() + " ontologies");
        System.out.println();
        for (Standard ontology : ontologies) {
            System.out.println("Going to add " + ontology.getStandardTitle());
            if (!standards.containsKey(ontology.getStandardTitle())) {
                ontology.getFieldToValue().put(StandardFields.SERIAL_ID, count);
                ontology.getFieldToValue().put(StandardFields.NID, count);
                ontology.getFieldToValue().put(StandardFields.VID, count);
                ontology.addFieldAndValue(StandardFields.COMPUTED_ID, createComputedId(startingComputedId + 1, '0', 6));
                System.out.println("inserting " + ontology.getStandardTitle() + " into db");
                dao.insertInformation(ontology);
                System.out.println("inserted");

                Node nodeForStandard = new Node();
                nodeForStandard.initialiseNodeForStandard(count, ontology);
                dao.insertInformation(nodeForStandard);

                System.out.println("Inserted Node");

                Alias aliasForStandard = new Alias();
                aliasForStandard.initialiseAliasForStandard(count, ontology);
                dao.insertInformation(aliasForStandard);

                System.out.println("Inserted Alias");

                count++;
            }
        }

        if (count == 0) {
            System.out.println("Database is up to date.");
        } else {
            System.out.println((count - startingNodeId) +
                    " ontologies were not in the database before. They have now been added.");
        }

        dao.closeConnection();
    }

    public int getLastComputedId(Map<String, Standard> standards) {
        int maxValue = 0;

        for (Standard standard : standards.values()) {
            try {
                maxValue = Integer.valueOf(standard.getComputedID().split("-")[1]);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        return maxValue;
    }

    private String createComputedId(int id, char paddingCharacter, int desiredLength) {
        String value = String.valueOf(id);

        int padding = desiredLength - value.length();
        for (int paddingCount = 0; paddingCount < padding; padding++) {
            value = paddingCharacter + value;
        }
        return "bsg-" + value;
    }

    public int getNextNodeId() {

        // this will let us know what hasn't already been added
        List<Node> nodes = dao.getNodeInformation();
        return getNodeIdStart(nodes);
    }

    public int getNodeIdStart(List<Node> nodes) {
        int maxValue = 0;

        for (Node node : nodes) {
            try {
                int nodeId = Integer.valueOf(node.getNodeId());
                if (nodeId > maxValue) {
                    maxValue = nodeId;
                }
            } catch (NumberFormatException nfe) {
                // skip this node
            }
        }

        return maxValue + 1;
    }
}
