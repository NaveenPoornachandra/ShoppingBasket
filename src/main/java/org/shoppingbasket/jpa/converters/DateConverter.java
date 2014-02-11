/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.shoppingbasket.jpa.converters;

import java.sql.Date;
import java.util.Calendar;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Naveen_P08
 */
@Converter(autoApply = false)
public class DateConverter implements AttributeConverter<java.util.Date,java.sql.Date>{

    @Override
    public Date convertToDatabaseColumn(java.util.Date attribute) {
        return new Date(attribute.getTime());
    }

    @Override
    public java.util.Date convertToEntityAttribute(Date dbData) {
        Calendar cal = Calendar.getInstance();
        System.out.println(dbData);
        //cal.setTime(dbData);
        return cal.getTime();
    }
    
}
