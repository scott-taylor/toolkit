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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Scott Taylor
 */
public final class SqlTools
{
    private static final Logger LOG = Logger.getLogger(SqlTools.class.getName());
    
    /*
     * This class is a utility class, and should normally not be instantiated. Ideally the
     * constructor should be made private, but then Emma would report the constructor as missing
     * test coverage. Making the constructor protected allows us to provide test coverage for the
     * constructor.
     */
    protected SqlTools()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Closes the connection, catching and ignoring any <code>SqlException<code> that is thrown.
     * 
     * @param c
     *            the connection to close.
     */
    public static void closeQuietly(Connection c)
    {
        try
        {
            c.close();
        }
        catch (SQLException e)
        {
            // Is logging the exception breaking the quiet contract.
            LOG.log(Level.FINE, "Exception while closing connection.", e);
        }
    }
    
}
