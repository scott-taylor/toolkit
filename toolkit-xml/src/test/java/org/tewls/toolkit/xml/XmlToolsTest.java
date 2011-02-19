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
package org.tewls.toolkit.xml;

import javax.xml.transform.Source;

import org.junit.Assert;
import org.junit.Test;
import org.tewls.toolkit.core.io.CharSets;
import org.tewls.toolkit.core.io.IoTools;
import org.tewls.toolkit.core.lang.exception.WrappedException;

/**
 * @author Scott Taylor
 */
public final class XmlToolsTest
{
    @Test
    public void testPrettyFormatWellFormedXml()
    {
        final String actual = XmlTools.prettyFormat("<a><b><c>test</c></b><d/></a>");
        final StringBuilder sb = new StringBuilder();
        sb.append("<a>\n");
        sb.append("    <b>\n");
        sb.append("        <c>test</c>\n");
        sb.append("    </b>\n");
        sb.append("    <d/>\n");
        sb.append("</a>\n");
        final String expected = sb.toString();
        Assert.assertEquals(expected, actual.replace("\r", ""));
    }

    @Test(expected = WrappedException.class)
    public void testPrettyFormatBadlyFormedXml()
    {
        XmlTools.prettyFormat("<a></b>");
    }

    @Test
    public void testXmlStringToSourceAndBack()
    {
        final String xmlString = "<a><b><c>test</c></b><d/></a>";
        final Source source = XmlTools.toStreamSource(xmlString);
        final String expected = "<a><b><c>test</c></b><d/></a>";
        Assert.assertEquals(expected, XmlTools.toString(source));
    }

    @Test(expected = WrappedException.class)
    public void testSourceToStringWithBadlyFormedXml()
    {
        final String xmlString = "<a></b>";
        final Source source = XmlTools.toStreamSource(xmlString);
        XmlTools.toString(source);
    }

    @Test
    public void testStringToDocumentWithWellFormedXml()
    {
        final String xmlString = "<a><b><c>test</c></b><d/></a>";
        Assert.assertNotNull(XmlTools.toDocument(xmlString));
    }

    @Test(expected = WrappedException.class)
    public void testStringToDocumentWithBadlyFormedXml()
    {
        final String xmlString = "<a></b>";
        XmlTools.toDocument(xmlString);
    }

    @Test
    public void testXmlTrim()
    {
        final String xmlString = "<a>   <b>   <c> two  words  </c>\n</b><d/></a>";
        final String expected = "<a><b><c> two  words  </c></b><d/></a>";
        Assert.assertEquals(expected, XmlTools.trim(xmlString));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testConstructorThrowsException()
    {
        new XmlTools();
    }

    @Test
    public void testCanoncialXml()
    {
        final String expected = IoTools.getResourceAsString("example1.canonical.xml", CharSets.UTF8);
        final String xml1 = IoTools.getResourceAsString("example1a.xml", CharSets.UTF8);
        Assert.assertEquals(expected, XmlTools.toCanonical(xml1));
        final String xml2 = IoTools.getResourceAsString("example1b.xml", CharSets.UTF8);
        Assert.assertEquals(expected, XmlTools.toCanonical(xml2));
        final String xml3 = IoTools.getResourceAsString("example1c.xml", CharSets.UTF8);
        Assert.assertEquals(expected, XmlTools.toCanonical(xml3));
    }
}
