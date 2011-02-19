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
package org.tewls.toolkit.core.lang.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.tewls.toolkit.core.lang.AssertTools;

/**
 * 
 * @author Scott Taylor
 */
public final class ReflectTools
{
    protected ReflectTools()
    {
        throw new UnsupportedOperationException();
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(InvocationHandler handler, Class<T> interfaceClass)
    {
        AssertTools.isTrue(interfaceClass.isInterface(), "Only interfaces can be proxied.");
        final Class<T>[] interfaces = new Class[] {interfaceClass};
        final ClassLoader classLoader = handler.getClass().getClassLoader();
        return (T) Proxy.newProxyInstance(classLoader, interfaces, handler); 
    }
}
