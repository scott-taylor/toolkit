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

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import nu.xom.Builder;
import nu.xom.canonical.Canonicalizer;

import org.tewls.toolkit.core.io.IoTools;
import org.tewls.toolkit.core.lang.exception.ExceptionTools;
import org.tewls.toolkit.xml.xom.NamespaceCollectingNodeFactory;
import org.tewls.toolkit.xml.xom.NamespaceNormalizingFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * @author Scott Taylor
 */
public final class XmlTools
{

    protected XmlTools()
    {
        throw new UnsupportedOperationException();
    }

    public static String prettyFormat(String xmlString)
    {
        try
        {
            final Source xmlInput = new StreamSource(new StringReader(xmlString));
            final StringWriter stringWriter = new StringWriter();
            final StreamResult xmlOutput = new StreamResult(stringWriter);
            final Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        }
        catch (Exception e)
        {
            throw ExceptionTools.wrapAsRuntimeException(e);
        }
    }

    public static StreamSource toStreamSource(String xmlString)
    {
        return new StreamSource(new StringReader(xmlString));
    }

    public static String toString(Source source)
    {
        try
        {
            final StringWriter sw = new StringWriter();
            final StreamResult result = new StreamResult(sw);
            final Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(source, result);
            return sw.toString();
        }
        catch (Exception e)
        {
            throw ExceptionTools.wrapAsRuntimeException(e);
        }
    }

    public static Document toDocument(String xmlString)
    {
        try
        {
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = dbf.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xmlString)));
        }
        catch (Exception e)
        {
            throw ExceptionTools.wrapAsRuntimeException(e);
        }
    }

    /** 
     * Removes whitespace between xml elements.
     */
    public static String trim(String xmlString)
    {
        try
        {
            final TransformerFactory factory = TransformerFactory.newInstance();
            final StreamSource source = new StreamSource(IoTools.getResourceAsStream("strip-space.xsl"));
            final Transformer transformer = factory.newTransformer(source);
            final StringWriter sw = new StringWriter();
            transformer.transform(toStreamSource(xmlString), new StreamResult(sw));
            return sw.toString();
        }
        catch (Exception e)
        {
            throw ExceptionTools.wrapAsRuntimeException(e);
        }
    }

    public static String toCanonical(Source xml)
    {
        return toCanonical(toString(xml));
    }
    
    public static String toCanonical(String xml)
    {
        try
        {
            final Set<String> namespaces = new HashSet<String>();
            final NamespaceCollectingNodeFactory collector = new NamespaceCollectingNodeFactory(namespaces);
            new Builder(collector).build(new StringReader(xml));
            final NamespaceNormalizingFactory normalizer = new NamespaceNormalizingFactory(namespaces);
            final nu.xom.Document doc = new Builder(normalizer).build(new StringReader(xml));
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            new Canonicalizer(baos).write(doc);
            return trim(baos.toString("UTF-8"));
        }
        catch (Exception e)
        {
            throw ExceptionTools.wrapAsRuntimeException(e);
        }
    }

}
