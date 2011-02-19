/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tewls.toolkit.core.lang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import junit.framework.Assert;

import org.junit.Test;

/**
 * 
 * @author Scott Taylor
 */
public final class StringToolsTest
{
    private static final String EMPTY_STRING = "";
    private static final String NULL_STRING = null;

    @Test(expected = UnsupportedOperationException.class)
    public void workAroundForEmmaConstructorCoverage()
    {
        new StringTools();
    }

    @Test
    public void testStripPrefix()
    {
        assertEquals("Mr. Taylor", StringTools.stripPrefix("Mr. Taylor", EMPTY_STRING));
        assertEquals("Taylor", StringTools.stripPrefix("Mr. Taylor", "Mr. "));
        assertEquals(EMPTY_STRING, StringTools.stripPrefix(EMPTY_STRING, "a"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenStringIsNull()
    {
        StringTools.stripPrefix(NULL_STRING, EMPTY_STRING);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenPrefixIsNull()
    {
        StringTools.stripPrefix(EMPTY_STRING, NULL_STRING);
    }

    @Test
    public void testQuoting()
    {
        assertEquals("null", StringTools.quote(null));
        assertEquals("''", StringTools.quote(EMPTY_STRING));
        assertEquals("' '", StringTools.quote(" "));
        assertEquals("'text'", StringTools.quote("text"));
    }

    @Test
    public void testFormatWithNoAnchors()
    {
        final String template = "No anchors";
        Assert.assertEquals(template, StringTools.format(template));
        
    }

    @Test
    public void testFormatWithOneAnchor()
    {
        final String template = "Value: '{}'";
        Assert.assertEquals("Value: 'quoted'", StringTools.format(template, "quoted"));
        Assert.assertEquals("Value: ''", StringTools.format(template, NULL_STRING));
        Assert.assertEquals("Value: ''", StringTools.format(template, EMPTY_STRING));        
    }

    @Test
    public void testFormatWithTwoAnchors()
    {
        final String template = "{} and {}";
        Assert.assertEquals("A and B", StringTools.format(template, "A", "B"));
        Assert.assertEquals(" and C", StringTools.format(template, NULL_STRING, "C"));
        Assert.assertEquals("D and ", StringTools.format(template, "D", EMPTY_STRING));        
    }


    @Test(expected = IllegalArgumentException.class)
    public void testTooManyArguments()
    {
        StringTools.format("D E F", "G");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooFewArguments()
    {
        StringTools.format("{} {}", "H");
    }

    @Test
    public void testFormatWithRegExpCharacters()
    {
        assertEquals("The value was $().", StringTools.format("The value was {}.", "$()"));
    }

    @Test
    public void testIntegerToString()
    {
        final Integer nullInteger = null;
        assertEquals("", StringTools.toString(nullInteger));
        assertEquals("1", StringTools.toString(Integer.valueOf(1)));
    }
    
    @Test
    public void testLongToString()
    {
        final Long nullLong = null;
        assertEquals("", StringTools.toString(nullLong));
        assertEquals("1", StringTools.toString(Long.valueOf(1)));
    }

    @Test
    public void testIsBlank()
    {
        assertTrue(StringTools.isBlank(""));
        assertTrue(StringTools.isBlank(null));
        assertTrue(StringTools.isBlank(" \t\n\t "));
        assertFalse(StringTools.isBlank(" a "));
    }

    @Test
    public void testIsNotBlank()
    {
        assertFalse(StringTools.isNotBlank(""));
        assertFalse(StringTools.isNotBlank(null));
        assertFalse(StringTools.isNotBlank(" \t\n\t "));
        assertTrue(StringTools.isNotBlank(" a "));
    }
}
