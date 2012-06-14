package org.biosharing.dao;

import org.biosharing.model.Standard;
import org.biosharing.model.StandardFields;
import org.isatools.isacreator.publicationlocator.CiteExploreResult;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 22/05/2012
 *         Time: 11:47
 */
public class BioSharingDAO extends DAO {


    public Map<String, Standard> getStandardNodeInformation() {
        ResultSet results = executeQuery("select * from standard");
        Map<String, Standard> standards = new HashMap<String, Standard>();

        try {
            while (results.next()) {
                Standard standard = new Standard();
                // this can be made more compact using the enumeration to populate the object.

                for (StandardFields field : StandardFields.values()) {
                    standard.addFieldAndValue(field, results.getString(field.toString()));
                }
                standards.put(standard.getStandardTitle(), standard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return standards;
    }


    public void updateStandardWithPublication(Standard standard, CiteExploreResult publication) {
        try {
            String pubmedURL = "http://www.ncbi.nlm.nih.gov/pubmed?term=";
            int rowsAffected = executeUpdate("UPDATE standard SET " + StandardFields.PUBLICATION_TITLE + "='" + publication.getTitle() + "', "
                    + StandardFields.PUBLICATION_URL + "='" + pubmedURL + publication.getId() + "', " + StandardFields.PUBLICATION_ATTRIBUTES + "='" + publication.getId()
                    + "' WHERE `" + StandardFields.STANDARD_TITLE + "`='" + standard.getStandardTitle() + "';");

            if (rowsAffected == 0) {
                System.err.println("** NO UPDATE PERFORMED! **");
            } else {
                System.out.println(rowsAffected + " rows updated!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public boolean insertStandard(Standard standard) {

        StringBuilder columnNames = new StringBuilder();
        StringBuilder values = new StringBuilder();
        int count = 0;
        for (StandardFields standardField : standard.getFieldToValue().keySet()) {
            columnNames.append("`").append(standardField.toString()).append("`");
            Object value = standard.getFieldToValue().get(standardField);
            if (value instanceof String) {
                values.append("'").append(value.toString()).append("'");
            } else {

                values.append(value == null ? "''" : value.toString());
            }

            if (count < standard.getFieldToValue().size() - 1) {
                columnNames.append(",");
                values.append(",");
            }
            count++;
        }

        // this can be made more compact using the enumeration to get back the fields in the order they were served out.
        String queryURL = "INSERT standard (" + columnNames + ") VALUES (" + values + ");";

        System.out.println(queryURL);
        return executeInsert(queryURL);
    }

}
