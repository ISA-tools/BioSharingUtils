package org.biosharing.model;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 15/06/2012
 *         Time: 13:21
 */
public enum NodeFields implements Fields{

    NID("nid"),
    VID("vid"),
    TYPE("type"),
    LANGUAGE("language"),
    TITLE("title"),
    UID("uid"),
    STATUS("status"),
    CREATED("created"),
    CHANGED("changed"),
    COMMENT("comment"),
    PROMOTE("promote"),
    MODERATE("moderate"),
    STICKY("sticky"),
    TNID("tnid"),
    TRANSLATE("translate");

    private String dbField;

    private NodeFields(String dbField) {
        this.dbField = dbField;
    }

    @Override
    public String toString() {
        return dbField;
    }
}
