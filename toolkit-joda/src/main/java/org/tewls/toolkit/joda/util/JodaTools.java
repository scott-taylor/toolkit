package org.tewls.toolkit.joda.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.LocalDate;

public final class JodaTools
{
    protected JodaTools()
    {
        throw new UnsupportedOperationException();
    }
    
    /**
     * This converts a LocalDate into an XMLGregorianCalendar as an xsd:date
     * rather than a xsd:datetime. Eg. 2010-11-10 instead of
     * 2010-11-10T00:00:00.000+01:00
     */
    public static XMLGregorianCalendar toXmlGregorianCalendar(LocalDate date)
    {
        try
        {
            final XMLGregorianCalendar cal = DatatypeFactory.newInstance().newXMLGregorianCalendar();
            cal.setDay(date.getDayOfMonth());
            cal.setMonth(date.getMonthOfYear());
            cal.setYear(date.getYear());
            return cal;
        }
        catch (DatatypeConfigurationException e)
        {
            throw new RuntimeException(e);
        }
    }
}
