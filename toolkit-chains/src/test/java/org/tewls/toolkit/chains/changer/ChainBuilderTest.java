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
package org.tewls.toolkit.chains.changer;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Test;
import org.tewls.toolkit.chains.Changer;
import org.tewls.toolkit.chains.InputChanger;
import org.tewls.toolkit.chains.InputInvoker;
import org.tewls.toolkit.chains.InputInvokerOutputChanger;
import org.tewls.toolkit.chains.InputOutputChanger;
import org.tewls.toolkit.chains.InputOutputInvoker;
import org.tewls.toolkit.chains.Invoker;
import org.tewls.toolkit.chains.OutputChanger;
import org.tewls.toolkit.chains.OutputInvoker;
import org.tewls.toolkit.chains.SideEffect;
import org.tewls.toolkit.chains.invoker.SideEffectController;

/**
 * Tests the duplex chain builder.
 * 
 * @author Scott Taylor
 */
@SuppressWarnings("unchecked")
public final class ChainBuilderTest
{

    private static final String EARTH = "EARTH";
    private static final String WORLD = "World";
    private static final String HELLO = "Hello";

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullConverterOnInput()
    {
        final Changer<String, String> link = null;
        ChangerBuilder.create(String.class, Integer.class).linkOnInput(link);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullConverterOnOutput()
    {
        final Changer<String, Integer> link = null;
        ChangerBuilder.create(String.class, Integer.class).linkOnOutput(link);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullInvokerOnInput()
    {
        final Invoker<String> link = null;
        ChangerBuilder.create(String.class, Integer.class).linkOnInput(link);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullInvokerOnOutput()
    {
        final Invoker<Integer> link = null;
        ChangerBuilder.create(String.class, Integer.class).linkOnOutput(link);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullInputOutputInvoker()
    {
        final InputOutputInvoker<String, Integer> link = null;
        ChangerBuilder.create(String.class, Integer.class).link(link);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullChain()
    {
        final Changer<String, String> chain = null;
        ChangerBuilder.create(String.class, String.class).link(chain);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullInputInvoker()
    {
        final InputInvoker<String> invoker = null;
        ChangerBuilder.create(String.class, Integer.class).link(invoker);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullOutputInvoker()
    {
        final OutputInvoker<Integer> invoker = null;
        ChangerBuilder.create(String.class, Integer.class).link(invoker);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullOutputConverter()
    {
        final OutputChanger<Integer, String> converter = null;
        ChangerBuilder.create(String.class, String.class).link(converter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullInputChanger()
    {
        final InputChanger<String, Integer> converter = null;
        ChangerBuilder.create(String.class, String.class).link(converter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullInputInvokerLink()
    {
        final InputInvokerOutputChanger<String, Integer, String> converter = null;
        ChangerBuilder.create(String.class, String.class).link(converter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullSideEffect()
    {
        final SideEffect converter = null;
        ChangerBuilder.create(String.class, String.class).link(converter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullLink()
    {
        final InputOutputChanger<String, Integer, Integer, String> link = null;
        ChangerBuilder.create(String.class, String.class).link(link);
    }

    @Test
    public void testChangerOnInputChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Changer<String, String> mockChain = control.createMock(Changer.class);
        final Changer<Integer, String> converter = control.createMock(Changer.class);
        final Changer<Integer, String> c = ChangerBuilder.create(Integer.class, String.class).linkOnInput(converter)
                .link(mockChain);

        EasyMock.expect(converter.process(1)).andReturn(HELLO);
        EasyMock.expect(mockChain.process(HELLO)).andReturn(WORLD);
        control.replay();
        assertEquals(WORLD, c.process(1));
        control.verify();
    }

    @Test
    public void testInputChangerChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Changer<String, String> mockChain = control.createMock(Changer.class);
        final Changer<Integer, String> mockChanger = control.createMock(Changer.class);
        final InputChanger<Integer, String> inputChanger = new MockInputChanger<Integer, String>(mockChanger);
        final Changer<Integer, String> c = ChangerBuilder.create(Integer.class, String.class).link(inputChanger)
                .link(mockChain);

        EasyMock.expect(mockChanger.process(1)).andReturn(HELLO);
        EasyMock.expect(mockChain.process(HELLO)).andReturn(WORLD);
        control.replay();
        assertEquals(WORLD, c.process(1));
        control.verify();
    }


    @Test
    public void testChangerOnOutputChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Changer<String, String> mockChain = control.createMock(Changer.class);
        final Changer<String, Integer> converter = control.createMock(Changer.class);
        final Changer<String, Integer> c = ChangerBuilder.create(String.class, Integer.class).linkOnOutput(converter)
                .link(mockChain);

        EasyMock.expect(mockChain.process(HELLO)).andReturn(WORLD);
        EasyMock.expect(converter.process(WORLD)).andReturn(1);
        control.replay();
        assertEquals(Integer.valueOf(1), c.process(HELLO));
        control.verify();
    }

    @Test
    public void testInvokerOnInputChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Changer<String, String> mockChain = control.createMock(Changer.class);
        final Invoker<String> invoker = control.createMock(Invoker.class);
        final Changer<String, String> c = ChangerBuilder.create(String.class, String.class).linkOnInput(invoker)
                .link(mockChain);

        invoker.process(HELLO);
        EasyMock.expect(mockChain.process(HELLO)).andReturn(WORLD);
        control.replay();
        assertEquals(WORLD, c.process(HELLO));
        control.verify();
    }

    @Test
    public void testInvokerOnOutputChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Changer<String, String> mockChain = control.createMock(Changer.class);
        final Invoker<String> invoker = control.createMock(Invoker.class);
        final Changer<String, String> c = ChangerBuilder.create(String.class, String.class).linkOnOutput(invoker)
                .link(mockChain);

        EasyMock.expect(mockChain.process(HELLO)).andReturn(WORLD);
        invoker.process(WORLD);
        control.replay();
        assertEquals(WORLD, c.process(HELLO));
        control.verify();
    }

    @Test
    public void testInputOutputInvokerChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Changer<Integer, String> mockChain = control.createMock(Changer.class);
        final Invoker<Object> ic = control.createMock(Invoker.class);
        final MockInputOutputInvoker<Integer, String> invoker = new MockInputOutputInvoker<Integer, String>(ic);
        final Changer<Integer, String> c = ChangerBuilder.create(Integer.class, String.class).link(invoker).link(mockChain);

        ic.process(1);
        EasyMock.expect(mockChain.process(1)).andReturn(WORLD);
        ic.process(WORLD);
        control.replay();
        assertEquals(WORLD, c.process(1));
        control.verify();
    }

    @Test
    public void testInputInvokerChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Changer<String, String> mockChain = control.createMock(Changer.class);
        final Invoker<String> ic = control.createMock(Invoker.class);
        final MockInputInvoker<String> invoker = new MockInputInvoker<String>(ic);
        final Changer<String, String> c = ChangerBuilder.create(String.class, String.class).link(invoker).link(mockChain);

        ic.process(HELLO);
        EasyMock.expect(mockChain.process(HELLO)).andReturn(WORLD);
        control.replay();
        assertEquals(WORLD, c.process(HELLO));
        control.verify();
    }

    @Test
    public void testOutputInvokerChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Changer<String, String> mockChain = control.createMock(Changer.class);
        final Invoker<String> ic = control.createMock(Invoker.class);
        final MockOutputInvoker<String> invoker = new MockOutputInvoker<String>(ic);
        final Changer<String, String> c = ChangerBuilder.create(String.class, String.class).link(invoker).link(mockChain);

        EasyMock.expect(mockChain.process(HELLO)).andReturn(WORLD);
        ic.process(WORLD);
        control.replay();
        assertEquals(WORLD, c.process(HELLO));
        control.verify();
    }

    @Test
    public void testOutputConverterChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Changer<String, String> mockChain = control.createMock(Changer.class);
        final Changer<String, Integer> ic = control.createMock(Changer.class);
        final MockOutputChanger<String, Integer> invoker = new MockOutputChanger<String, Integer>(ic);
        final Changer<String, Integer> c = ChangerBuilder.create(String.class, Integer.class).link(invoker).link(mockChain);

        EasyMock.expect(mockChain.process(HELLO)).andReturn(WORLD);
        EasyMock.expect(ic.process(WORLD)).andReturn(1);
        control.replay();
        assertEquals(Integer.valueOf(1), c.process(HELLO));
        control.verify();
    }

    @Test
    public void testSideEffectChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Changer<String, String> mockChain = control.createMock(Changer.class);
        final SideEffectController ic = control.createMock(SideEffectController.class);
        final MockSideEffect sideEffect = new MockSideEffect(ic);
        final Changer<String, String> c = ChangerBuilder.create(String.class, String.class).link(sideEffect)
                .link(mockChain);

        ic.process();
        EasyMock.expect(mockChain.process(HELLO)).andReturn(WORLD);
        control.replay();
        assertEquals(WORLD, c.process(HELLO));
        control.verify();
    }

    @Test
    public void testInputInvokerLink()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Changer<String, String> mockChain = control.createMock(Changer.class);
        final Invoker<String> i = control.createMock(Invoker.class);
        final Changer<String, String> c = control.createMock(Changer.class);
        final MockInputInvokerOutputConverter<String, String> invoker = new MockInputInvokerOutputConverter<String, String>(
                i, c);
        final Changer<String, String> chain = ChangerBuilder.create(String.class, String.class).link(invoker)
                .link(mockChain);

        i.process(HELLO);
        EasyMock.expect(mockChain.process(HELLO)).andReturn(WORLD);
        EasyMock.expect(c.process(WORLD)).andReturn(EARTH);
        control.replay();
        assertEquals(EARTH, chain.process(HELLO));
        control.verify();
    }

    @Test
    public void testLink()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Changer<String, String> mockChain = control.createMock(Changer.class);
        final Changer<Integer, String> i = control.createMock(Changer.class);
        final Changer<String, Integer> o = control.createMock(Changer.class);
        final MockLink<Integer, String, String, Integer> invoker = new MockLink<Integer, String, String, Integer>(i, o);
        final Changer<Integer, Integer> chain = ChangerBuilder.create(Integer.class, Integer.class).link(invoker)
                .link(mockChain);

        EasyMock.expect(i.process(1)).andReturn(HELLO);
        EasyMock.expect(mockChain.process(HELLO)).andReturn(WORLD);
        EasyMock.expect(o.process(WORLD)).andReturn(2);
        control.replay();
        assertEquals(Integer.valueOf(2), chain.process(1));
        control.verify();
    }

}
