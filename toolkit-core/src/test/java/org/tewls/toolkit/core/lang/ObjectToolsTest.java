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

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Scott Taylor
 */
public final class ObjectToolsTest
{

    @Test(expected = UnsupportedOperationException.class)
    public void testObjectTools()
    {
        new ObjectTools();
    }

    @Test
    public void testDefaultValue()
    {
        final String nullString = null;
        Assert.assertNull(ObjectTools.defaultValue());
        Assert.assertNull(ObjectTools.defaultValue(nullString));
        final String expected = "b";
        final String notExpected = "c";
        Assert.assertEquals(expected, ObjectTools.defaultValue(nullString, expected));
        Assert.assertEquals(expected, ObjectTools.defaultValue(nullString, expected, notExpected));
        Assert.assertEquals(expected, ObjectTools.defaultValue(nullString, nullString, expected));
    }
}
