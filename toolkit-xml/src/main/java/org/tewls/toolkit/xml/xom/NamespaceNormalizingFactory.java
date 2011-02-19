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
package org.tewls.toolkit.xml.xom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nu.xom.Attribute.Type;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.NodeFactory;
import nu.xom.Nodes;

/**
 * 
 * @author Scott Taylor
 */
public final class NamespaceNormalizingFactory extends NodeFactory
{
    private final Map<String, String> namespacePrefixes = new HashMap<String, String>();

    public NamespaceNormalizingFactory(Set<String> namespaces)
    {
        final List<String> namespaceList = new ArrayList<String>(namespaces);
        Collections.sort(namespaceList);
        for (int i = 0; i < namespaceList.size(); i += 1)
        {
            namespacePrefixes.put(namespaceList.get(i), "ns" + (i + 1));
        }
    }

    @Override
    public Element startMakingElement(String name, String namespace)
    {
        return super.startMakingElement(correctName(name, namespace), namespace);
    }

    @Override
    public Nodes makeAttribute(String name, String namespace, String value, Type type)
    {
        return super.makeAttribute(correctName(name, namespace), namespace, value, type);
    }

    @Override
    public Nodes finishMakingElement(Element e)
    {
        final List<String> prefixes = getNamespaceDeclaredPrefixes(e);
        prefixes.removeAll(namespacePrefixes.values());
        for (String prefix : prefixes)
        {
            e.removeNamespaceDeclaration(prefix);
        }
        return new Nodes(e);
    }

    private List<String> getNamespaceDeclaredPrefixes(Element e)
    {
        final List<String> prefixes = new ArrayList<String>();
        for (int i = 0; i < e.getNamespaceDeclarationCount(); i += 1)
        {
            prefixes.add(e.getNamespacePrefix(i));
        }
        return prefixes;
    }

    @Override
    public void finishMakingDocument(Document document)
    {
        final Element e = document.getRootElement();
        for (String namespace : namespacePrefixes.keySet())
        {
            e.addNamespaceDeclaration(namespacePrefixes.get(namespace), namespace);
        }
        super.finishMakingDocument(document);
    }

    private String correctName(String name, String namespace)
    {
        if ("".equals(namespace))
        {
            return name;
        }
        final String prefix = namespacePrefixes.get(namespace);
        if (prefix == null)
        {
            throw new IllegalArgumentException("Prefix for namespace '" + namespace + "' not found in list.");
        }
        final String localName = name.substring(name.indexOf(':') + 1);
        return prefix + ":" + localName;
    }

    @Override
    public Nodes makeComment(String arg0)
    {
        return new Nodes();
    }
    
    
}
