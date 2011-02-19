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
import org.tewls.toolkit.chains.OutputInvoker;

/**
 * 
 * @author Scott Taylor
 * 
 * @param <S>
 * @param <I>
 * @param <O>
 * @param <E>
 */
final class ChainableOutputInvoker<S, I, O, E> implements Chainable<S, I, O, E>
{
    private final Chainable<S, I, O, E> chainable;
    private final OutputInvoker<? super O> invoker;

    public ChainableOutputInvoker(Chainable<S, I, O, E> c, OutputInvoker<? super O> i)
    {
        chainable = c;
        invoker = i;
    }

    @Override
    public Changer<S, E> link(final Changer<I, O> changer)
    {
        return chainable.link(new Changer<I, O>() {
            @Override
            public O process(I input)
            {
                return invoker.process(new BoundedChanger<I, O>(changer, input));
            }
        });
    }
}
