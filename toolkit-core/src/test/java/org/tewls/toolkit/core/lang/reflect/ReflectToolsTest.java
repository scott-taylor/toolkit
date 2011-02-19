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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.easymock.EasyMock;
import org.junit.Test;

/**
 * 
 * @author Scott Taylor
 */
public final class ReflectToolsTest
{
    @Test(expected = UnsupportedOperationException.class)
    public void testObjectTools()
    {
        new ReflectTools();
    }

    @Test
    public void testCreatingProxy()
    {
        final Runnable mockRunnable = EasyMock.createMock(Runnable.class);
        mockRunnable.run();
        final Runnable proxy = ReflectTools.createProxy(new MyInvocationHandler(mockRunnable), Runnable.class);
        EasyMock.replay(mockRunnable);
        proxy.run();
        EasyMock.verify(mockRunnable);
    }
    
    private static class MyInvocationHandler implements InvocationHandler
    {
        private final Runnable delegate;

        public MyInvocationHandler(Runnable delegate)
        {
            this.delegate = delegate;
        }
    
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
        {
            try 
            {
                return method.invoke(delegate, args);
            }
            catch (InvocationTargetException e)
            {
                throw e.getCause();
            }
        }
        
    }
}
