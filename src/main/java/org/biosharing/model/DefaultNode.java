package org.biosharing.model;


import org.apache.commons.collections15.OrderedMap;
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

    private OrderedMap<StandardFields, Object> fieldToValue;

    public DefaultNode() {
        fieldToValue = new ListOrderedMap<StandardFields, Object>();
    }

    public void addFieldAndValue(StandardFields field, String value) {
        fieldToValue.put(field, value);
    }

    public Object getValueForField(StandardFields field) {
        if (fieldToValue.containsKey(field)) {
            return fieldToValue.get(field);
        } else {
            return "";
        }
    }

    public OrderedMap<StandardFields, Object> getFieldToValue() {
        return fieldToValue;
    }
}
