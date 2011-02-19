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
package org.tewls.toolkit.core.lang.exception;

import java.io.IOException;

import org.junit.Test;

/**
 * 
 * @author Scott Taylor
 */
public final class WrappedExceptionTest
{
    @Test(expected = IllegalArgumentException.class)
    public void testCannotWrapRuntimeException()
    {
        throw new WrappedException(new RuntimeException());
    }

    @Test(expected = IOException.class)
    public void testThrowException() throws Throwable
    {
        final WrappedException e = new WrappedException(new IOException());
        throw e.getCause();
    }

}