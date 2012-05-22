package org.biosharing.dao;

import org.biosharing.model.Standard;
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
        ResultSet results = executeQuery("select * from standards");
        Map<String, Standard> standards = new HashMap<String, Standard>();

        try {
            while (results.next()) {
                Standard standard = new Standard();
                standard.addFieldAndValue(Standard.STANDARD, results.getString(Standard.STANDARD));
                standard.addFieldAndValue(Standard.FULL_NAME, results.getString(Standard.FULL_NAME));
                standard.addFieldAndValue(Standard.TYPE, results.getString(Standard.TYPE));
                standard.addFieldAndValue(Standard.DOMAIN, results.getString(Standard.DOMAIN));
                standard.addFieldAndValue(Standard.ORGANIZATION, results.getString(Standard.ORGANIZATION));
                standard.addFieldAndValue(Standard.PUBLICATION, results.getString(Standard.PUBLICATION));
                standards.put(standard.getStandard(), standard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return standards;
    }


    public void updateStandard(Standard standard, CiteExploreResult publication) {
        try {

            int rowsAffected = executeUpdate("UPDATE standards SET publications='" + publication.getId() + "' WHERE `standard name`='" + standard.getStandard() + "';");

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

        String queryURL = "INSERT standards VALUES ('" + standard.getStandard() + "',  '" + standard.getFullName() + "',  '" + standard.getType() + "',  '" + standard.getDomain()
                + "',  '" + standard.getPublication() + "',  '" + standard.getOrganization() + "');";

        System.out.println(queryURL);
        return executeInsert(queryURL);
    }

}
