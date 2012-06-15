package org.biosharing.model;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 15/06/2012
 *         Time: 13:19
 */
public class Alias extends DefaultTable {

    public String getNodeId() {
        return getValueForField(NodeFields.NID).toString();
    }

    public String toString() {
        return getNodeId();
    }

    public void initialiseAlias() {
        addFieldAndValue(AliasFields.LANGUAGE, "en");
    }

    public void initialiseAliasForStandard(int nodeId, Standard standard) {
        initialiseAlias();
        addFieldAndValue(AliasFields.PID, nodeId);
        addFieldAndValue(AliasFields.SRC, "node/" + nodeId);
        addFieldAndValue(AliasFields.DST, standard.getComputedID());
    }
}
