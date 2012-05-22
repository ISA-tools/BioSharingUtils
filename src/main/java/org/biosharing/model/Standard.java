package org.biosharing.model;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 22/05/2012
 *         Time: 14:34
 */
public class Standard extends DefaultNode {

    public static final String STANDARD = "standard name";
    public static final String FULL_NAME = "full name";
    public static final String TYPE = "type";
    public static final String DOMAIN = "domains";
    public static final String ORGANIZATION = "organizations";
    public static final String PUBLICATION = "publications";
    
    public String getStandard() {
        return getValueForField(STANDARD);
    }

    public String getFullName() {
        return getValueForField(FULL_NAME);
    }

    public String getType() {
        return getValueForField(TYPE);
    }

    public String getDomain() {
        return getValueForField(DOMAIN);
    }

    public String getOrganization() {
        return getValueForField(ORGANIZATION);
    }

    public String getPublication() {
        return getValueForField(PUBLICATION);
    }

    public String toString() {
        return getStandard() + "(" + getFullName() + ")";
    }

}
