package org.tewls.toolkit.core.lang;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Assert;
import org.junit.Test;

public final class ThreadToolsTest
{

    @Test(expected = UnsupportedOperationException.class)
    public void shouldNotBeAbleToInstantiateClass()
    {
        new ThreadTools();
    }

    @Test(timeout = 1000)
    public void testSleepWithOutInterruption()
    {
        ThreadTools.sleep(200L);
    }

    @Test(timeout = 1000)
    public void testSleepWithInterruption() throws InterruptedException
    {
        final AtomicBoolean interrupted = new AtomicBoolean(false);
        final Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
                ThreadTools.sleep(2000L);
                interrupted.set(Thread.currentThread().isInterrupted());
            }
        });
        thread.start();
        ThreadTools.sleep(200L);
        thread.interrupt();
        thread.join();
        Assert.assertTrue(interrupted.get());
    }

}
