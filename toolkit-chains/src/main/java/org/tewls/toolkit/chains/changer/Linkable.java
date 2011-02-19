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

import org.tewls.toolkit.chains.Changer;
import org.tewls.toolkit.chains.InputChanger;
import org.tewls.toolkit.chains.InputChangerOutputInvoker;
import org.tewls.toolkit.chains.InputInvoker;
import org.tewls.toolkit.chains.InputInvokerOutputChanger;
import org.tewls.toolkit.chains.InputOutputChanger;
import org.tewls.toolkit.chains.InputOutputInvoker;
import org.tewls.toolkit.chains.Invoker;
import org.tewls.toolkit.chains.OutputChanger;
import org.tewls.toolkit.chains.OutputInvoker;
import org.tewls.toolkit.chains.SideEffect;

/**
 * 
 * @author Scott Taylor
 * 
 * @param <LinkIn>
 * @param <ChainIn>
 * @param <ChainOut>
 * @param <LinkOut>
 */
public interface Linkable<LinkIn, ChainIn, ChainOut, LinkOut>
{
    <I, O> Linkable<LinkIn, I, O, LinkOut> link(InputOutputChanger<? super ChainIn, I, O, ChainOut> link);

    <O> Linkable<LinkIn, ChainIn, O, LinkOut> link(OutputChanger<O, ChainOut> link);

    <O> Linkable<LinkIn, O, ChainOut, LinkOut> link(InputChanger<? super ChainIn, O> link);

    <O> Linkable<LinkIn, O, ChainOut, LinkOut> linkOnInput(Changer<? super ChainIn, O> link);

    <I> Linkable<LinkIn, ChainIn, I, LinkOut> linkOnOutput(Changer<I, ChainOut> link);

    <O> Linkable<LinkIn, ChainIn, O, LinkOut> link(InputInvokerOutputChanger<? super ChainIn, O, ChainOut> link);

    <O> Linkable<LinkIn, O, ChainOut, LinkOut> link(InputChangerOutputInvoker<? super ChainIn, O, ChainOut> link);

    Linkable<LinkIn, ChainIn, ChainOut, LinkOut> link(SideEffect link);

    Linkable<LinkIn, ChainIn, ChainOut, LinkOut> link(InputInvoker<? super ChainIn> link);

    Linkable<LinkIn, ChainIn, ChainOut, LinkOut> link(OutputInvoker<? super ChainOut> link);

    Linkable<LinkIn, ChainIn, ChainOut, LinkOut> link(InputOutputInvoker<? super ChainIn, ? super ChainOut> link);

    Linkable<LinkIn, ChainIn, ChainOut, LinkOut> linkOnInput(Invoker<? super ChainIn> link);

    Linkable<LinkIn, ChainIn, ChainOut, LinkOut> linkOnOutput(Invoker<? super ChainOut> link);

    Changer<LinkIn, LinkOut> link(Changer<? super ChainIn, ? extends ChainOut> chain);
}
