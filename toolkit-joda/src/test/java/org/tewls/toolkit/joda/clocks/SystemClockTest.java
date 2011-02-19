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
package org.tewls.toolkit.joda.clocks;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.joda.time.DateTime;
import org.junit.Test;

/**
 * 
 * @author Scott Taylor
 */
public final class SystemClockTest
{
    private final SystemClock clock = new SystemClock();

    @Test
    public void testGetDateTime()
    {
        final DateTime dateTime1 = clock.getDateTime();
        assertNotNull(dateTime1);
        final DateTime dateTime2 = clock.getDateTime();
        assertFalse(dateTime1.isAfter(dateTime2));
    }
}
