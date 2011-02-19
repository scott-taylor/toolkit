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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * 
 * @author Scott Taylor
 */
public final class AssertToolsTest
{
    private static final String ARGUMENT_CANNOT_BE_NULL_NOR_EMPTY = "Argument cannot be null nor empty.";
    private static final String OBJECT_CANNOT_BE_NULL = "Object cannot be null.";
    private static final String FAILED_TO_THROW_EXCEPTION = "Failed to throw exception.";
    private static final String HELLO = "Hello";

    @Test(expected = UnsupportedOperationException.class)
    public void workAroundForEmmaConstructorCoverage()
    {
        new AssertTools();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull()
    {
        AssertTools.notNull(null);
    }

    @Test
    public void testNotNull()
    {
        AssertTools.notNull("");
    }

    @Test
    public void testAssertWithMessageWhenNull()
    {
        final String message = OBJECT_CANNOT_BE_NULL;
        try
        {
            AssertTools.notNull(null, message);
            fail(FAILED_TO_THROW_EXCEPTION);
        }
        catch (final IllegalArgumentException e)
        {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void testAssertWithMessageWhenNotNull()
    {
        assertEquals(HELLO, AssertTools.notNull(HELLO, OBJECT_CANNOT_BE_NULL));
    }

    @Test
    public void testAssertWithExceptionWhenNull()
    {
        final String message = OBJECT_CANNOT_BE_NULL;
        try
        {
            AssertTools.notNull(null, new IllegalStateException(message));
            fail(FAILED_TO_THROW_EXCEPTION);
        }
        catch (final IllegalStateException e)
        {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void testAssertWithExceptionWhenNotNull()
    {
        final String message = OBJECT_CANNOT_BE_NULL;
        AssertTools.notNull("", new IllegalStateException(message));
    }

    @Test
    public void testAssertIsTrueWhenExpressionIsTrue()
    {
        AssertTools.isTrue(true);
        AssertTools.isTrue(true, "Wasn't true");
        AssertTools.isTrue(true, new IllegalArgumentException());
    }

    @Test(expected = IllegalStateException.class)
    public void testAssertIsTrueWhenExpressionIsFalse()
    {
        AssertTools.isTrue(false, new IllegalStateException());
    }

    @Test
    public void testAssertIsFalseWhenExpressionIsFalse()
    {
        AssertTools.isFalse(false);
        AssertTools.isFalse(false, "Wasn't false");
        AssertTools.isFalse(false, new IllegalArgumentException());
    }

    @Test(expected = IllegalStateException.class)
    public void testAssertIsFalseWhenExpressionIsTrue()
    {
        AssertTools.isFalse(true, new IllegalStateException());
    }

    @Test
    public void testWhenStringIsNull()
    {
        final String message = "Argument cannot be null.";
        try
        {
            AssertTools.notNullNorEmpty(null, message);
            fail(FAILED_TO_THROW_EXCEPTION);
        }
        catch (final IllegalArgumentException e)
        {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void testWhenStringIsEmpty()
    {
        final String message = ARGUMENT_CANNOT_BE_NULL_NOR_EMPTY;
        try
        {
            AssertTools.notNullNorEmpty("", message);
            fail(FAILED_TO_THROW_EXCEPTION);
        }
        catch (final IllegalArgumentException e)
        {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void testWhenStringIsNotEmpty()
    {
        final String message = ARGUMENT_CANNOT_BE_NULL_NOR_EMPTY;
        AssertTools.notNullNorEmpty("-", message);
    }

    @Test
    public void testWhenStringIsBlank()
    {
        final String str = " ";
        org.junit.Assert.assertEquals(str, AssertTools.notNullNorEmpty(str));
    }

    @Test
    public void testAssertNotBlankWhenNotBlank()
    {
        AssertTools.isNotBlank("a", "Cannot be blank.");
        AssertTools.isNotBlank("        a");
    }
    
    @Test
    public void testAssertBlankWhenBlank()
    {
        AssertTools.isBlank(null);
        AssertTools.isBlank("", "Must be blank");
        AssertTools.isBlank(" ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotBlankWhenNull()
    {
        AssertTools.isNotBlank(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotBlankWhenEmpty()
    {
        AssertTools.isNotBlank("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertNotBlankWhenBlank()
    {
        AssertTools.isNotBlank("    ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertBlankWhenNotBlank()
    {
        AssertTools.isBlank(" b ");
    }

    @Test
    public void testMessageReturnedWhenAssertNotBlankFails()
    {
        final String message = "Cannot be blank.";
        try 
        {
            AssertTools.isNotBlank(null, message);
        }
        catch (IllegalArgumentException e)
        {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void testMessageReturnedWhenAssertBlankFails()
    {
        final String message = "Must be blank.";
        try 
        {
            AssertTools.isBlank("a", message);
        }
        catch (IllegalArgumentException e)
        {
            assertEquals(message, e.getMessage());
        }
    }


}
