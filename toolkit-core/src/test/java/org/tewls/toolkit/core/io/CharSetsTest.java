package org.tewls.toolkit.core.io;

import org.junit.Test;

/**
 * 
 * @author Scott Taylor
 */
public final class CharSetsTest
{
    @Test(expected = UnsupportedOperationException.class)
    public void testConstructorThrowsException()
    {
        new CharSets();
    }

}
