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
package org.tewls.toolkit.core.sql;

import java.sql.Connection;
import java.sql.SQLException;

import org.easymock.EasyMock;
import org.junit.Test;

/**
 * 
 * @author Scott Taylor
 */
public final class SqlToolsTest
{
    @Test
    public void testWhenCloseThrowsNoException() throws SQLException
    {
        final Connection c = EasyMock.createMock(Connection.class);
        c.close();
        EasyMock.replay(c);
        SqlTools.closeQuietly(c);
        EasyMock.verify(c);
    }

    @Test
    public void testWhenCloseThrowsException() throws SQLException
    {
        final Connection c = EasyMock.createMock(Connection.class);
        c.close();
        EasyMock.expectLastCall().andThrow(new SQLException("Junit"));
        EasyMock.replay(c);
        SqlTools.closeQuietly(c);
        EasyMock.verify(c);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testConstructorThrowsException()
    {
        new SqlTools();
    }

}
