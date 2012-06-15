package org.biosharing.model;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 01/06/2012
 *         Time: 14:17
 */
public enum StandardFields {

    NID("nid"),
    VID("vid"),
    SERIAL_ID("field_bsg_serial_id_value"),
    COMPUTED_ID("field_bsg_computed_id_value"),
    STANDARD_URL("field_standard_url"),
    STANDARD_TITLE("field_standard_title"),
    FULL_NAME("field_full_name_value"),
    TYPE("field_type_value"),
    MIBBI("field_mibbi_value"),
    OBO("field_obo_value"),
    DOMAIN("field_domain_value"),
    PUBLICATION_URL("field_publication_url"),
    PUBLICATION_TITLE("field_publication_title"),
    PUBLICATION_ATTRIBUTES("field_publication_attributes"),
    ORGANIZATION_URL("field_organization_url"),
    ORGANIZATION_TITLE("field_organization_title"),
    ORGANIZATION_ATTRIBUTES("field_organization_attributes"),
    CONTACT("field_contact_value");


    private String dbField;

    private StandardFields(String dbField) {
        this.dbField = dbField;
    }

    @Override
    public String toString() {
        return dbField;
    }
}
