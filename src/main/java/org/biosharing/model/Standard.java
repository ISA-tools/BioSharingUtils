package org.biosharing.model;

import org.biosharing.dao.BioSharingDAO;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 22/05/2012
 *         Time: 14:34
 */
public class Standard extends AbstractDBTable {



    // publications may need to be a list. Also need to add contacts. Might be able to get this from
    // the ontology object directly. Look at dbInfo.txt for the schema.

    public String getVId() {
        return getValueForField(StandardFields.VID).toString();
    }

    public String getNodeId() {
        return getValueForField(StandardFields.NID).toString();
    }
    
    public String getSerialID() {
        return getValueForField(StandardFields.SERIAL_ID).toString();
    }

    public String getComputedID() {
        return getValueForField(StandardFields.COMPUTED_ID).toString();
    }

    public String getStandardURL() {
        return getValueForField(StandardFields.STANDARD_URL).toString();
    }

    public String getStandardTitle() {
        return getValueForField(StandardFields.STANDARD_TITLE).toString();
    }

    public String getFullName() {
        return getValueForField(StandardFields.FULL_NAME).toString();
    }

    public String getType() {
        return getValueForField(StandardFields.TYPE).toString();
    }

    public String getDomain() {
        return getValueForField(StandardFields.DOMAIN).toString();
    }

    public String getOrganization() {
        return getValueForField(StandardFields.ORGANIZATION_TITLE).toString();
    }

    public String getOrganizationURL() {
        return getValueForField(StandardFields.ORGANIZATION_URL).toString();
    }

    public String getPublication() {
        return getValueForField(StandardFields.PUBLICATION_TITLE).toString();
    }

    public String getPublicationURL() {
        return getValueForField(StandardFields.PUBLICATION_URL).toString();
    }

    public String getTableName() {
        return BioSharingDAO.STANDARD_TABLE;
    }

    public String toString() {
        return getFullName();
    }

    public void initialiseStandard() {
        for(StandardFields standardField : StandardFields.values()) {
            addFieldAndValue(standardField, "");
        }
    }

}
