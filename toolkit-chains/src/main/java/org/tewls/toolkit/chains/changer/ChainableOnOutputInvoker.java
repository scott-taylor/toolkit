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
import org.tewls.toolkit.chains.Invoker;

/**
 * 
 * @author Scott Taylor
 * 
 * @param <S>
 * @param <I>
 * @param <O>
 * @param <E>
 */
final class ChainableOnOutputInvoker<S, I, O, E> implements Chainable<S, I, O, E>
{

    private final Chainable<S, I, O, E> chainable;
    private final Invoker<? super O> invoker;

    public ChainableOnOutputInvoker(Chainable<S, I, O, E> c, Invoker<? super O> i)
    {
        chainable = c;
        invoker = i;
    }

    @Override
    public Changer<S, E> link(final Changer<I, O> chain)
    {
        return chainable.link(new Changer<I, O>() {
            @Override
            public O process(I input)
            {
                final O o = chain.process(input);
                invoker.process(o);
                return o;
            }
        });
    }
}
