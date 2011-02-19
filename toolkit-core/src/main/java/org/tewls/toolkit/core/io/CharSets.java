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
package org.tewls.toolkit.core.io;

import java.nio.charset.Charset;

/**
 * 
 * @author Scott Taylor
 */
public final class CharSets
{
    public static final Charset UTF8 = Charset.forName("UTF-8");
    public static final Charset ISO8859_1 = Charset.forName("ISO8859-1");

    protected CharSets()
    {
        throw new UnsupportedOperationException();
    }
    
}
