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

import java.util.concurrent.atomic.AtomicReference;

import org.joda.time.DateTime;

/**
 * Implements a clock that returns the time it was last set to. Useful for unit
 * testing.
 * 
 * @author Scott Taylor
 */
public final class StoppedClock implements Clock
{
    private final AtomicReference<DateTime> dateTime;

    public StoppedClock(DateTime dateTime)
    {
        this.dateTime = new AtomicReference<DateTime>(dateTime);
    }

    @Override
    public DateTime getDateTime()
    {
        return dateTime.get();
    }

    public void setDateTime(DateTime dateTime)
    {
        this.dateTime.set(dateTime);
    }
}
