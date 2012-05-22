package org.biosharing.model;


import org.apache.commons.collections15.map.ListOrderedMap;

import java.util.Map;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 22/05/2012
 *         Time: 11:49
 */
public class DefaultNode implements Node{

    private Map<String, String> fieldToValue;

    public DefaultNode() {
        fieldToValue = new ListOrderedMap<String, String>();
    }

    public void addFieldAndValue(String field, String value) {
        fieldToValue.put(field, value);
    }

    public String getValueForField(String field) {
        if (fieldToValue.containsKey(field)) {
            return fieldToValue.get(field);
        } else {
            return "";
        }
    }

    public Map<String, String> getFieldToValue() {
        return fieldToValue;
    }
}
