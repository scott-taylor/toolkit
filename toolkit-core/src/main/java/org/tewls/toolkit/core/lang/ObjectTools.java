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
 * 
 * @author Scott Taylor
 */
public final class ObjectTools
{
    protected ObjectTools()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the first non null argument in the parameter list.
     * If all arguments are null then null is returned.
     */
    public static <T> T defaultValue(T... objs)
    {
        for (T obj : objs)
        {
            if (obj != null)
            {
                return obj;
            }
        }
        return null;
    }
}
