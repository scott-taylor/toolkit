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

import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.junit.Test;

/**
 * 
 * @author Scott Taylor
 */
public final class StoppedClockTest
{
    @Test
    public void testGetAndSetDateTime()
    {
        final DateTime initDateTime = new DateTime(2005, 3, 26, 12, 0, 0, 0);
        final StoppedClock clock = new StoppedClock(initDateTime);
        assertTrue(initDateTime.equals(clock.getDateTime()));
        final DateTime newDateTime = new DateTime(2006, 3, 26, 12, 0, 0, 0);
        clock.setDateTime(newDateTime);
        assertTrue(newDateTime.equals(clock.getDateTime()));
    }
}
