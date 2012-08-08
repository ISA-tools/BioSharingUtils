package org.biosharing.model;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 08/08/2012
 *         Time: 17:19
 */
public class NodeRevision extends DefaultTable {

    public String toString() {
        return getVID();
    }
    
    private String getVID() {
        return getValueForField(NodeRevisionFields.VID).toString();
    }

    public void initialiseNodeRevisionForStandard(int nid, Standard standard) {

        addFieldAndValue(NodeRevisionFields.NID, nid);
        addFieldAndValue(NodeRevisionFields.VID, nid);
        addFieldAndValue(NodeRevisionFields.TITLE, standard.getStandardTitle());
    }
}
