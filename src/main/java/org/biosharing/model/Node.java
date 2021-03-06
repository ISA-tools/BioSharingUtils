package org.biosharing.model;


import org.biosharing.dao.BioSharingDAO;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 15/06/2012
 *         Time: 13:19
 */
public class Node extends AbstractDBTable {

    public String getNodeId() {
        return getValueForField(NodeFields.NID).toString();
    }

    public String toString() {
        return getNodeId();
    }

    public String getTableName() {
        return BioSharingDAO.NODE_TABLE;
    }
    
    public void initialiseNodeForStandard(int id, Standard standard) {
//        Date date = new Date(System.currentTimeMillis());

        addFieldAndValue(NodeFields.NID, id);
        addFieldAndValue(NodeFields.VID, id);
        addFieldAndValue(NodeFields.TYPE, "standard_cck");
        addFieldAndValue(NodeFields.LANGUAGE, "en");
        addFieldAndValue(NodeFields.TITLE, standard.getStandardTitle());
        addFieldAndValue(NodeFields.UID, 1);
        addFieldAndValue(NodeFields.STATUS, 1);
        addFieldAndValue(NodeFields.COMMENT, 0);
        addFieldAndValue(NodeFields.PROMOTE, 1);
        addFieldAndValue(NodeFields.MODERATE, 0);
        addFieldAndValue(NodeFields.STICKY, 0);
        addFieldAndValue(NodeFields.TNID, 0);
        addFieldAndValue(NodeFields.TRANSLATE, 0);
    }

}
