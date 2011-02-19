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

/**
 * @author Scott Taylor
 */
public final class ExceptionTools
{

    /*
     * This class is a utility class, and should normally not be instantiated.
     * Ideally the constructor should be made private, but then Emma would
     * report the constructor as missing test coverage. Making the constructor
     * protected allows us to provide test coverage for the constructor.
     */
    protected ExceptionTools()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * If the exception is already a RuntimeException it is returned as is,
     * otherwise it is wrapped in a <code>WrappedException</code> which is then
     * returned.
     * 
     * @param e
     *            the exception to be thrown.
     * @return WrappedException if the argument was a checked exception then it
     *         is returned inside a <code>WrappedException</code>, otherwise the
     *         original exception is thrown.
     */
    public static RuntimeException wrapAsRuntimeException(Exception e)
    {
        if (e instanceof RuntimeException)
        {
            return (RuntimeException) e;
        }
        return new WrappedException(e);
    }

}
