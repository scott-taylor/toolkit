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

import java.io.IOException;
import java.net.URL;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.tewls.toolkit.core.lang.exception.ExceptionTools;

/**
 * 
 * @author Scott Taylor
 */
public final class JarTools
{
    protected JarTools()
    {
        throw new UnsupportedOperationException();
    }
    
    public static Manifest getManifestForClass(Class<? extends Object> aClass)
    {
        final URL resource = aClass.getProtectionDomain().getCodeSource().getLocation();
        try
        {
            /*
             * URL.getPath() returns a string where spaces in the path are url encode (%20). But a
             * plus sign (in the filename at least) doesn't appear to be url encoded.
             */
            final String path = resource.getPath().replace("%20", " ");
            final JarFile jf = new JarFile(path);
            try
            {
                return jf.getManifest();
            }
            finally
            {
                jf.close();
            }
        }
        catch (IOException e)
        {
            throw ExceptionTools.wrapAsRuntimeException(e);
        }
    }

}
