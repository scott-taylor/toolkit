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


/**
 * Assertion utility class that assists in validating arguments. Useful for identifying programmer
 * errors early and clearly at runtime.
 * 
 * @author Scott Taylor
 */
public final class AssertTools
{
    /*
     * This class is a utility class, and should normally not be instantiated. Ideally the
     * constructor should be made private, but then Emma would report the constructor as missing
     * test coverage. Making the constructor protected allows us to provide test coverage for the
     * constructor.
     */
    protected AssertTools()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Assert that an object is not <code>null</code> .
     * 
     * <pre class="code">
     * Assert.notNull(value);
     * </pre>
     * 
     * @param object
     *            the object to check
     * @throws IllegalArgumentException
     *             if the object is <code>null</code>
     */
    public static <O> O notNull(O object)
    {
        return notNull(object, new IllegalArgumentException());
    }

    /**
     * Assert that an object is not <code>null</code> .
     * 
     * <pre class="code">
     * Assert.notNull(value, &quot;The value must not be null&quot;);
     * </pre>
     * 
     * @param object
     *            the object to check
     * @param message
     *            the exception message to use if the assertion fails
     * @throws IllegalArgumentException
     *             if the object is <code>null</code>
     */
    public static <O> O notNull(O object, String message)
    {
        return notNull(object, new IllegalArgumentException(message));
    }

    /**
     * Assert that an object is not <code>null</code> .
     * 
     * <pre class="code">
     * Assert.notNull(value, new IllegalArgumentException(&quot;The value must not be null&quot;));
     * </pre>
     * 
     * @param object
     *            the object to check
     * @param e
     *            the exception to throw if the assertion fails.
     * @throws RuntimeException
     *             if the object is <code>null</code>
     */
    public static <O> O notNull(O object, RuntimeException e)
    {
        if (object == null)
        {
            throw e;
        }
        return object;
    }

    /**
     * Assert a boolean expression, throwing an exception if the test result is <code>false</code>.
     * 
     * <pre class="code">
     * Assert.isTrue(i &gt; 0);
     * </pre>
     * 
     * @param expression
     *            a boolean expression
     * @param e
     *            the exception thrown if the assertion fails.
     */
    public static void isTrue(boolean expression, RuntimeException e)
    {
        if (!expression)
        {
            throw e;
        }
    }

    /**
     * Assert a boolean expression, throwing <code>IllegalArgumentException</code> if the test
     * result is <code>false</code>.
     * 
     * <pre class="code">
     * Assert.isTrue(i &gt; 0, &quot;The value must be greater than zero&quot;);
     * </pre>
     * 
     * @param expression
     *            a boolean expression
     * @param message
     *            the exception message to use if the assertion fails
     * @throws IllegalArgumentException
     *             if expression is <code>false</code>
     */
    public static void isTrue(boolean expression, String message)
    {
        isTrue(expression, new IllegalArgumentException(message));
    }

    /**
     * Assert a boolean expression, throwing <code>IllegalArgumentException</code> if the test
     * result is <code>false</code>.
     * 
     * <pre class="code">
     * Assert.isTrue(i &gt; 0);
     * </pre>
     * 
     * @param expression
     *            a boolean expression
     * @throws IllegalArgumentException
     *             if expression is <code>false</code>
     */
    public static void isTrue(boolean expression)
    {
        isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    /**
     * Assert a boolean expression, throwing an exception if the test result is <code>true</code>.
     * 
     * <pre class="code">
     * Assert.isFalse(i &lt; 0);
     * </pre>
     * 
     * @param expression
     *            a boolean expression
     * @param e
     *            the exception thrown if the assertion fails.
     */
    public static void isFalse(boolean expression, RuntimeException e)
    {
        isTrue(!expression, e);
    }

    /**
     * Assert a boolean expression, throwing <code>IllegalArgumentException</code> if the test
     * result is <code>true</code>.
     * 
     * <pre class="code">
     * Assert.isFalse(i &lt; 0,     * "The value cannot be negative.");
     * </pre>
     * 
     * @param expression
     *            a boolean expression
     * @param message
     *            the exception message to use if the assertion fails
     * @throws IllegalArgumentException
     *             if expression is <code>true</code>
     */
    public static void isFalse(boolean expression, String message)
    {
        isFalse(expression, new IllegalArgumentException(message));
    }

    /**
     * Assert a boolean expression, throwing <code>IllegalArgumentException</code> if the test
     * result is <code>true</code>.
     * 
     * <pre class="code">
     * Assert.isFalse(i &gt; 0);
     * </pre>
     * 
     * @param expression
     *            a boolean expression
     * @throws IllegalArgumentException
     *             if expression is <code>true</code>
     */
    public static void isFalse(boolean expression)
    {
        isFalse(expression, "[Assertion failed] - this expression must be false");
    }

    /**
     * Assert that a string is neither <code>null</code> nor empty.
     * 
     * <pre class="code">
     * Assert.notNullNorEmpty(value, new IllegalArgumentException(&quot;The value must not be null nor empty&quot;));
     * </pre>
     * 
     * @param str
     *            the string to check
     * @param e
     *            the exception to throw if the assertion fails.
     * @throws RuntimeException
     *             if the object is <code>null</code>
     */
    public static String notNullNorEmpty(String str, RuntimeException e)
    {
        AssertTools.notNull(str, e);
        if ("".equals(str))
        {
            throw e;
        }
        return str;
    }

    /**
     * Assert that a string is neither <code>null</code> nor empty.
     * 
     * <pre class="code">
     * Assert.notNullNorEmpty(value, &quot;The value must not be null nor empty&quot;);
     * </pre>
     * 
     * @param str
     *            the string to check
     * @param message
     *            the exception message to use if the assertion fails
     * @throws IllegalArgumentException
     *             if the string is <code>null</code>
     */
    public static String notNullNorEmpty(String str, String message)
    {
        return notNullNorEmpty(str, new IllegalArgumentException(message));
    }

    /**
     * Assert that a string is neither <code>null</code> nor empty.
     * 
     * <pre class="code">
     * Assert.notNullNorEmpty(value, new IllegalArgumentException(&quot;The value must not be null nor empty&quot;));
     * </pre>
     * 
     * @param str
     *            the string to check
     * @throws IllegalArgumentException
     *             if the string is <code>null</code>
     */
    public static String notNullNorEmpty(String str)
    {
        return notNullNorEmpty(str, new IllegalArgumentException());
    }

    public static String isNotBlank(String str)
    {
        return isNotBlank(str, new IllegalArgumentException());
    }
    
    public static String isNotBlank(String str, String message)
    {
        return isNotBlank(str, new IllegalArgumentException(message));
    }

    public static String isNotBlank(String str, RuntimeException e)
    {
        if (StringTools.isBlank(str))
        {
            throw e;
        }
        return str;
    }

    public static String isBlank(String str)
    {
        return isBlank(str, new IllegalArgumentException());
    }

    public static String isBlank(String str, String message)
    {
        return isBlank(str, new IllegalArgumentException(message));
    }

    public static String isBlank(String str, RuntimeException e)
    {
        if (StringTools.isNotBlank(str))
        {
            throw e;
        }
        return str;
    }

}
