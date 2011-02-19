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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.tewls.toolkit.core.lang.exception.ExceptionTools;

/**
 * 
 * @author Scott Taylor
 */
public final class IoTools
{
    private static final Logger LOG = Logger.getLogger(IoTools.class.getName());
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    protected IoTools()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Closes the <code>InputStream</code>, catching and ignoring any
     * <code>IOException<code> that is thrown.
     * 
     * @param c the <code>InputStream</code> to close.
     */
    public static void closeQuietly(InputStream inputStream)
    {
        try
        {
            inputStream.close();
        }
        catch (IOException e)
        {
            // Is logging the exception breaking the quiet contract.
            LOG.log(Level.FINE, "Exception while closing InputStream.", e);
        }
    }

    /**
     * Closes the <code>OutputStream</code>, catching and ignoring any
     * <code>IOException<code> that is thrown.
     * 
     * @param c the <code>OutputStream</code> to close.
     */
    public static void closeQuietly(OutputStream outputStream)
    {
        try
        {
            outputStream.close();
        }
        catch (IOException e)
        {
            // Is logging the exception breaking the quiet contract.
            LOG.log(Level.FINE, "Exception while closing OutputStream.", e);
        }
    }

    /**
     * Closes the <code>InputStream</code>, catching and ignoring any
     * <code>IOException<code> that is thrown.
     * 
     * @param c the <code>InputStream</code> to close.
     */
    public static void closeQuietly(Reader reader)
    {
        try
        {
            reader.close();
        }
        catch (IOException e)
        {
            // Is logging the exception breaking the quiet contract.
            LOG.log(Level.FINE, "Exception while closing Reader.", e);
        }
    }

    /**
     * Closes the <code>OutputStream</code>, catching and ignoring any
     * <code>IOException<code> that is thrown.
     * 
     * @param c the <code>OutputStream</code> to close.
     */
    public static void closeQuietly(Writer writer)
    {
        try
        {
            writer.close();
        }
        catch (IOException e)
        {
            // Is logging the exception breaking the quiet contract.
            LOG.log(Level.FINE, "Exception while closing Writer.", e);
        }
    }

    public static URL getResourceUrl(String path)
    {
        return new ClassPathResourceLoader().getResourceUrl(path);
    }

    public static InputStream getResourceAsStream(String path)
    {
        return new ClassPathResourceLoader().getResourceAsStream(path);
    }

    public static String getResourceAsString(String path, Charset charset)
    {
        final InputStream inputStream = getResourceAsStream(path);
        try
        {
            return toString(inputStream, charset);
        }
        finally
        {
            closeQuietly(inputStream);
        }
    }

    public static String toString(InputStream inputStream, Charset charset)
    {
        final StringWriter sw = new StringWriter(DEFAULT_BUFFER_SIZE);
        copy(new InputStreamReader(inputStream, charset), sw);
        return sw.toString();
    }

    private static void copy(Reader reader, Writer writer)
    {
        try
        {
            final char[] buffer = new char[DEFAULT_BUFFER_SIZE];
            while (true)
            {
                final int n = reader.read(buffer);
                if (n == -1)
                {
                    break;
                }
                writer.write(buffer, 0, n);
            }
        }
        catch (IOException e)
        {
            throw ExceptionTools.wrapAsRuntimeException(e);
        }
    }

}
