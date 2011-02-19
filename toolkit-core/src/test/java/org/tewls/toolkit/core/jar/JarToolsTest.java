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
package org.tewls.toolkit.core.jar;

import java.util.jar.Manifest;

import org.junit.Assert;
import org.junit.Test;
import org.tewls.toolkit.core.lang.exception.WrappedException;

/**
 * 
 * @author Scott Taylor
 */
public final class JarToolsTest
{

    @Test(expected = UnsupportedOperationException.class)
    public void constructorShouldThrowException()
    {
        new JarTools();
    }

    @Test
    public void shouldReturnManifest()
    {
        final Manifest m = JarTools.getManifestForClass(Assert.class);
        Assert.assertNotNull(m);
    }

    @Test(expected = WrappedException.class)
    public void testGetManifestForClassNotInJarFile()
    {
        JarTools.getManifestForClass(JarTools.class);
        Assert.fail("Failed to throw exception.");
    }

}
