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
package org.tewls.toolkit.chains.invoker;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Test;
import org.tewls.toolkit.chains.Changer;
import org.tewls.toolkit.chains.InputChanger;
import org.tewls.toolkit.chains.InputInvoker;
import org.tewls.toolkit.chains.Invoker;
import org.tewls.toolkit.chains.SideEffect;
import org.tewls.toolkit.chains.changer.MockSideEffect;

/**
 * 
 * @author Scott Taylor
 */
@SuppressWarnings("unchecked")
public final class ChainBuilderTest
{

    private static final String JUNIT = "JUnit";
    private static final String WORLD = "World";
    private static final String HELLO = "Hello";

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullLinks()
    {
        final InputChanger<String, String> link = null;
        InvokerBuilder.create(String.class).link(link);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullConverter()
    {
        final Changer<String, String> link = null;
        InvokerBuilder.create(String.class).link(link);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullInvokerLinks()
    {
        final InputInvoker<String> invoker = null;
        InvokerBuilder.create(String.class).link(invoker);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullSideEffects()
    {
        final SideEffect sideEffect = null;
        InvokerBuilder.create(String.class).link(sideEffect);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubsequentLinkCannotBeNull()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final InputChanger<String, String> mockLink = control.createMock(InputChanger.class);
        final InputChanger<String, String> nullLink = null;
        InvokerBuilder.create(String.class).link(mockLink).link(nullLink);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotBuildChainWithNullInvokers()
    {
        final Invoker<String> invoker = null;
        InvokerBuilder.create(String.class).link(invoker);
    }

    @Test
    public void testEmptyChain()
    {
        final Invoker<String> mockChain = EasyMock.createStrictMock(Invoker.class);
        final Invoker<String> c = InvokerBuilder.create(String.class).link(mockChain).build();

        mockChain.process(JUNIT);
        EasyMock.replay(mockChain);
        c.process(JUNIT);
        EasyMock.verify(mockChain);
    }

    @Test
    public void testOneLinkChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Invoker<String> mockChain = control.createMock(Invoker.class);
        final LinkController<String, String> lc = control.createMock(LinkController.class);
        final LinkMock<String, String> mockLink = new LinkMock<String, String>(lc);
        final Invoker<String> c = InvokerBuilder.create(String.class).link(mockLink).link(mockChain).build();

        EasyMock.expect(lc.process(HELLO)).andReturn(WORLD);
        mockChain.process(WORLD);
        control.replay();
        c.process(HELLO);
        control.verify();
    }

    @Test
    public void testOneConverterChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Invoker<String> mockChain = control.createMock(Invoker.class);
        final Changer<Integer, String> converter = control.createMock(Changer.class);
        final Invoker<Integer> c = InvokerBuilder.create(Integer.class).link(converter).link(mockChain).build();

        EasyMock.expect(converter.process(1)).andReturn(HELLO);
        mockChain.process(HELLO);
        control.replay();
        c.process(1);
        control.verify();
    }

    @Test
    public void testOneInvokerLinkChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Invoker<Integer> mockChain = control.createMock(Invoker.class);
        final InvokerController<Number> ic = control.createMock(InvokerController.class);
        final InvokerMock<Number> invoker = new InvokerMock<Number>(ic);
        final Invoker<Integer> c = InvokerBuilder.create(Integer.class).link(invoker).link(mockChain).build();

        final Integer i = Integer.valueOf(1);
        ic.process(i);
        mockChain.process(i);
        control.replay();
        c.process(i);
        control.verify();
    }

    @Test
    public void testOneInvokerChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Invoker<Integer> mockChain = control.createMock(Invoker.class);
        final Invoker<Number> invoker = control.createMock(Invoker.class);
        final Invoker<Integer> c = InvokerBuilder.create(Integer.class).link(invoker).link(mockChain).build();

        final Integer i = Integer.valueOf(1);
        invoker.process(i);
        mockChain.process(i);
        control.replay();
        c.process(i);
        control.verify();
    }

    @Test
    public void testOneSideEffectChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Invoker<String> mockChain = control.createMock(Invoker.class);
        final SideEffectController sc = control.createMock(SideEffectController.class);
        final MockSideEffect sideEffect = new MockSideEffect(sc);
        final Invoker<String> c = InvokerBuilder.create(String.class).link(sideEffect).link(mockChain).build();

        sc.process();
        mockChain.process(JUNIT);
        control.replay();
        c.process(JUNIT);
        control.verify();
    }

    @Test
    public void testSideEffectAndLinkChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Invoker<String> mockChain = control.createMock(Invoker.class);
        final SideEffectController sc = control.createMock(SideEffectController.class);
        final MockSideEffect sideEffect = new MockSideEffect(sc);
        final LinkController<String, String> lc = control.createMock(LinkController.class);
        final LinkMock<String, String> mockLink = new LinkMock<String, String>(lc);
        final Invoker<String> c = InvokerBuilder.create(String.class).link(sideEffect).link(mockLink).link(mockChain).build();

        sc.process();
        EasyMock.expect(lc.process(HELLO)).andReturn(WORLD);
        mockChain.process(WORLD);
        control.replay();
        c.process(HELLO);
        control.verify();
    }

    @Test
    public void testLinkAndSideEffectChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Invoker<String> mockChain = control.createMock(Invoker.class);
        final SideEffectController sc = control.createMock(SideEffectController.class);
        final MockSideEffect sideEffect = new MockSideEffect(sc);
        final LinkController<String, String> lc = control.createMock(LinkController.class);
        final LinkMock<String, String> mockLink = new LinkMock<String, String>(lc);
        final Invoker<String> c = InvokerBuilder.create(String.class).link(mockLink).link(sideEffect).link(mockChain).build();

        EasyMock.expect(lc.process(HELLO)).andReturn(WORLD);
        sc.process();
        mockChain.process(WORLD);
        control.replay();
        c.process(HELLO);
        control.verify();
    }

    @Test
    public void testSideEffectAndInvokerChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Invoker<Integer> mockChain = control.createMock(Invoker.class);
        final SideEffectController sc = control.createMock(SideEffectController.class);
        final MockSideEffect sideEffect = new MockSideEffect(sc);
        final InvokerController<Number> ic = control.createMock(InvokerController.class);
        final InvokerMock<Number> invoker = new InvokerMock<Number>(ic);
        final Invoker<Integer> c = InvokerBuilder.create(Integer.class).link(sideEffect).link(invoker).link(mockChain).build();

        final Integer value = Integer.valueOf(3);
        sc.process();
        ic.process(value);
        mockChain.process(value);
        control.replay();
        c.process(value);
        control.verify();
    }

    @Test
    public void testInvokerAndSideEffectChain()
    {
        final IMocksControl control = EasyMock.createStrictControl();
        final Invoker<Integer> mockChain = control.createMock(Invoker.class);
        final SideEffectController sc = control.createMock(SideEffectController.class);
        final MockSideEffect sideEffect = new MockSideEffect(sc);
        final InvokerController<Number> ic = control.createMock(InvokerController.class);
        final InvokerMock<Number> invoker = new InvokerMock<Number>(ic);
        final Invoker<Integer> c = InvokerBuilder.create(Integer.class).link(invoker).link(sideEffect).link(mockChain).build();

        final Integer value = Integer.valueOf(3);
        ic.process(value);
        sc.process();
        mockChain.process(value);
        control.replay();
        c.process(value);
        control.verify();
    }

}
