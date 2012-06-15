package org.biosharing.model;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 15/06/2012
 *         Time: 13:21
 */
public enum AliasFields implements Fields{

    PID("pid"),
    SRC("src"),
    DST("dst"),
    LANGUAGE("language");

    private String dbField;

    private AliasFields(String dbField) {
        this.dbField = dbField;
    }

    @Override
    public String toString() {
        return dbField;
    }

}
