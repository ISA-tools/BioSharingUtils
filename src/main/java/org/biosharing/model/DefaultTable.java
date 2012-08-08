package org.biosharing.model;


import org.apache.commons.collections15.OrderedMap;
import org.apache.commons.collections15.map.ListOrderedMap;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com)
 *         <p/>
 *         Date: 22/05/2012
 *         Time: 11:49
 */
public abstract class DefaultTable implements DBTable {

    private OrderedMap<Fields, Object> fieldToValue;

    public DefaultTable() {
        fieldToValue = new ListOrderedMap<Fields, Object>();
    }

    public void addFieldAndValue(Fields field, Object value) {
        fieldToValue.put(field, value);
    }

    public Object getValueForField(Fields field) {
        if (fieldToValue.containsKey(field)) {
            return fieldToValue.get(field) == null ? "" : fieldToValue.get(field);
        } else {
            return "";
        }
    }

    public OrderedMap<Fields, Object> getFieldToValue() {
        return fieldToValue;
    }


}
