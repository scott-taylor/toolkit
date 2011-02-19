package org.tewls.toolkit.joda.util;

import javax.xml.datatype.XMLGregorianCalendar;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.junit.Test;


public final class JodaToolsTest
{
    @Test(expected = UnsupportedOperationException.class)
    public void shouldNotAllowInstantiation()
    {
        new JodaTools();
    }
    
    @Test
    public void testLocalDateToXmlGregorianCalendar()
    {
        final XMLGregorianCalendar cal = JodaTools.toXmlGregorianCalendar(new LocalDate(2010, 5, 7));
        Assert.assertNotNull(cal);
        Assert.assertEquals("2010-05-07", cal.toXMLFormat());
    }
}
