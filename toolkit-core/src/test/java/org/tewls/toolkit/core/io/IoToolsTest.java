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
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.tewls.toolkit.core.lang.exception.NotFoundException;

/**
 * 
 * @author Scott Taylor
 */
public final class IoToolsTest
{

    @Test
    public void testWhenClosingInputStreamThrowsNoException() throws IOException
    {
        final InputStream inputStream = EasyMock.createMock(InputStream.class);
        inputStream.close();
        EasyMock.replay(inputStream);
        IoTools.closeQuietly(inputStream);
        EasyMock.verify(inputStream);
    }

    @Test
    public void testWhenClosingInputStreamThrowsException() throws IOException
    {
        final InputStream inputStream = EasyMock.createMock(InputStream.class);
        inputStream.close();
        EasyMock.expectLastCall().andThrow(new IOException());
        EasyMock.replay(inputStream);
        IoTools.closeQuietly(inputStream);
        EasyMock.verify(inputStream);
    }

    @Test
    public void testWhenClosingOutputStreamThrowsNoException() throws IOException
    {
        final OutputStream outputStream = EasyMock.createMock(OutputStream.class);
        outputStream.close();
        EasyMock.replay(outputStream);
        IoTools.closeQuietly(outputStream);
        EasyMock.verify(outputStream);
    }

    @Test
    public void testWhenClosingOutputStreamThrowsException() throws IOException
    {
        final OutputStream outputStream = EasyMock.createMock(OutputStream.class);
        outputStream.close();
        EasyMock.expectLastCall().andThrow(new IOException());
        EasyMock.replay(outputStream);
        IoTools.closeQuietly(outputStream);
        EasyMock.verify(outputStream);
    }


    @Test
    public void testWhenClosingReaderThrowsNoException() throws IOException
    {
        final Reader reader = EasyMock.createMock(Reader.class);
        reader.close();
        EasyMock.replay(reader);
        IoTools.closeQuietly(reader);
        EasyMock.verify(reader);
    }

    @Test
    public void testWhenClosingReaderThrowsException() throws IOException
    {
        final Reader reader = EasyMock.createMock(Reader.class);
        reader.close();
        EasyMock.expectLastCall().andThrow(new IOException());
        EasyMock.replay(reader);
        IoTools.closeQuietly(reader);
        EasyMock.verify(reader);
    }

    @Test
    public void testWhenClosingWriterThrowsNoException() throws IOException
    {
        final Writer writer = EasyMock.createMock(Writer.class);
        writer.close();
        EasyMock.replay(writer);
        IoTools.closeQuietly(writer);
        EasyMock.verify(writer);
    }

    @Test
    public void testWhenClosingWriterThrowsException() throws IOException
    {
        final Writer writer = EasyMock.createMock(Writer.class);
        writer.close();
        EasyMock.expectLastCall().andThrow(new IOException());
        EasyMock.replay(writer);
        IoTools.closeQuietly(writer);
        EasyMock.verify(writer);
    }

    @Test(expected = NotFoundException.class)
    public void getResourceUrlShouldThrowNotFoundExceptionIfResourceIsNotFound()
    {
        IoTools.getResourceUrl("doesn't exist");
    }

    @Test(expected = NotFoundException.class)
    public void getResourceAsStreamShouldThrowNotFoundExceptionIfResourceIsNotFound()
    {
        IoTools.getResourceAsStream("doesn't exist");
    }

    @Test
    public void getResourceUrlShouldReturnUrlToResource()
    {
        IoTools.getResourceUrl("logback-test.xml");
    }

    @Test
    public void getResourceAsStreamShouldReturnInputStreamToResource()
    {
        IoTools.getResourceAsStream("logback-test.xml");
    }

    @Test
    public void getResourceAsString()
    {
        final String contents = IoTools.getResourceAsString("logback-test.xml", CharSets.UTF8);
        Assert.assertNotNull(contents);
    }

    @Test(expected = NotFoundException.class)
    public void getResourceAsStringThrowsExceptionWhenResourceNotFound()
    {
        IoTools.getResourceAsString("doesnotexist.xml", CharSets.UTF8);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testConstructorThrowsException()
    {
        new IoTools();
    }
}
