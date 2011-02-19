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

import java.util.Set;

import nu.xom.Attribute.Type;
import nu.xom.Element;
import nu.xom.NodeFactory;
import nu.xom.Nodes;

/**
 * Collects all namespaces URIs encountered when building up Documents.
 * @author Scott Taylor
 */
public final class NamespaceCollectingNodeFactory extends NodeFactory
{
    private final Set<String> namespaces;

    public NamespaceCollectingNodeFactory(Set<String> namespaces)
    {
        this.namespaces = namespaces;
    }

    @Override
    public Element makeRootElement(String name, String namespace)
    {
        addNamespace(namespace);
        return super.makeRootElement(name, namespace);
    }

    @Override
    public Element startMakingElement(String name, String namespace)
    {
        addNamespace(namespace);
        return super.startMakingElement(name, namespace);
    }

    @Override
    public Nodes makeAttribute(String name, String namespace, String value, Type type)
    {
        addNamespace(namespace);
        return super.makeAttribute(name, namespace, value, type);
    }

    private void addNamespace(String namespace)
    {
        if (!"".equals(namespace))
        {
            namespaces.add(namespace);
        }
    }
}
