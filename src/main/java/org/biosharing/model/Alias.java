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
public class Alias extends AbstractDBTable {

    public String getPID() {
        return getValueForField(AliasFields.PID).toString();
    }

    public String toString() {
        return getPID();
    }

    public void initialiseAlias() {
        addFieldAndValue(AliasFields.LANGUAGE, "en");
    }

    public String getTableName() {
        return BioSharingDAO.ALIAS_TABLE;
    }

    public void initialiseAliasForStandard(int pid, int nodeId, Standard standard) {
        initialiseAlias();
        addFieldAndValue(AliasFields.PID, pid);
        addFieldAndValue(AliasFields.SRC, "node/" + nodeId);
        addFieldAndValue(AliasFields.DST, standard.getComputedID());
    }
}
