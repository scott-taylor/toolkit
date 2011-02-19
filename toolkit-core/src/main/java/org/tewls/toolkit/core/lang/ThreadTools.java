package org.tewls.toolkit.core.lang;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class ThreadTools
{
    private static final Logger LOG = Logger.getLogger(ThreadTools.class.getName());
    
    protected ThreadTools()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Causes the currently executing thread to sleep (temporarily cease
     * execution) for the specified number of milliseconds, subject to the
     * precision and accuracy of system timers and schedulers.
     * 
     * If the thread is interrupted it will return early, but the 
     * InterruptedException will not be thrown.
     */
    public static void sleep(long millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
            LOG.log(Level.FINE, "Sleep interrupted.", e);
            Thread.currentThread().interrupt();
        }
    }
}
