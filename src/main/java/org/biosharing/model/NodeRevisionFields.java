package org.biosharing.model;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 08/08/2012
 *         Time: 17:20
 */
public enum NodeRevisionFields implements Fields{

    NID("nid"),
    VID("vid"),
    UID("uid"),
    TITLE("title");

    private String dbField;

    private NodeRevisionFields(String dbField) {
        this.dbField = dbField;
    }

    @Override
    public String toString() {
        return dbField;
    }
}
