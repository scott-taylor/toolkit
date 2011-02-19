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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Scott Taylor
 */
public final class StringTools
{

    private static final String EMPTY_STRING = "";
    private static final String NULL = "null";

    /*
     * This class is a utility class, and should normally not be instantiated.
     * Ideally the constructor should be made private, but then Emma would
     * report the constructor as missing test coverage. Making the constructor
     * protected allows us to provide test coverage for the constructor.
     */
    protected StringTools()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Strips a prefix from the start of a String.
     * </p>
     * 
     * <pre>
     * StringTools.stripPrefix("", *)           = ""
     * StringTools.stripPrefix("abc", "")       = "abc"
     * StringTools.stripPrefix("yxabc  ", "yx") = "abc  "
     * StringTools.stripPrefix("yxabc  ", "x")  = "yzabc  "
     * </pre>
     * 
     * @param str
     *            the String to remove the prefix from, may not be null.
     * @param prefix
     *            the prefix to remove, may not be null.
     * @return the stripped String
     */
    public static String stripPrefix(String str, String prefix)
    {
        AssertTools.notNull(str, "String cannot be null.");
        AssertTools.notNull(prefix, "Prefix cannot be null.");
        if (str.startsWith(prefix))
        {
            return str.substring(prefix.length());
        }
        return str;
    }

    /**
     * Quotes the given String with single quotes.
     * 
     * @param str
     *            the string to be quoted (e.g. "a string")
     * @return the quoted String (e.g. "'a string'"), or the string "null" if
     *         the input was <code>null</code>
     */
    public static String quote(String str)
    {
        return (str == null) ? NULL : "'" + str + "'";
    }

    /**
     * Performs argument substitution for the 'template' passed as parameter.
     * <p>
     * For example,
     * 
     * <pre>
     * StringTools.format(&quot;Hello {}.&quot;, &quot;World&quot;);
     * </pre>
     * 
     * will return the string "Hello World".
     * </p>
     * 
     * @param messagePattern
     *            The message pattern which will be parsed and formatted
     * @param args
     *            The args to be substituted in place of the formatting anchor
     * @return The formatted message
     */
    public static String format(String template, Object... args)
    {
        return formatArray(template, args);
    }

    /**
     * Same principle as the {@link #format(String, Object...)} methods except
     * that any number of arguments can be passed in an array.
     * 
     * @param template
     *            The template which will be parsed and formatted
     * @param args
     *            An array of arguments to be substituted in place of formatting
     *            anchors
     * @return The formatted message
     */
    public static String formatArray(String template, Object[] args)
    {
        final Pattern maskPattern = Pattern.compile("\\{\\}", Pattern.DOTALL);
        final Matcher matcher = maskPattern.matcher(template);
        final StringBuffer sb = new StringBuffer();
        for (Object arg : args)
        {
            AssertTools.isTrue(matcher.find(), "More arguments than template anchors.");
            matcher.appendReplacement(sb, Matcher.quoteReplacement(toString(arg)));
        }
        AssertTools.isFalse(matcher.find(), "More template anchors than arguments.");
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * A null safe toString method. If the object passed in is null then an
     * empty string is returned, otherwise the value returned from calling the
     * object's toString() method is returned.
     */
    public static String toString(Object obj)
    {
        return (obj == null) ? EMPTY_STRING : obj.toString();
    }

    /**
     * Returns a String object representing the specified Integer. If the
     * Integer is null, an empty string is returned.
     */
    public static String toString(Integer value)
    {
        return (value == null) ? EMPTY_STRING : Integer.toString(value);
    }

    /**
     * Returns a String object representing the specified Long. If the Long is
     * null, an empty string is returned.
     */
    public static String toString(Long value)
    {
        return (value == null) ? EMPTY_STRING : Long.toString(value);
    }

    /**
     * Returns true if the String contains only whitespace characters, otherwise
     * false is return.
     */
    public static boolean isBlank(String value)
    {
        final String nullSafe = toString(value);
        for (int i = 0; i < nullSafe.length(); i += 1)
        {
            if (!Character.isWhitespace(nullSafe.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns false if the String contains only whitespace characters,
     * otherwise true is return.
     */
    public static boolean isNotBlank(String value)
    {
        return !isBlank(value);
    }

}
