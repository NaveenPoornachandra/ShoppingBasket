/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.jpa.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Naveen_P08
 */
@Converter(autoApply = false)
public class BooleanToIntegerConverter implements AttributeConverter<Boolean, Integer>{

    @Override
    public Integer convertToDatabaseColumn(Boolean attribute) {
        return Boolean.TRUE.equals(attribute)?1:0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer dbData) {
        return dbData> 0;
    }
    
}
