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

import org.tewls.toolkit.chains.InputInvoker;
import org.tewls.toolkit.chains.Invoker;

/**
 * 
 * @author Scott Taylor
 * 
 * @param <S>
 * @param <I>
 */
final class ChainableInputInvoker<S, I> implements Chainable<S, I>
{
    private final Chainable<S, I> previous;
    private final InputInvoker<? super I> invoker;

    public ChainableInputInvoker(Chainable<S, I> c, InputInvoker<? super I> i)
    {
        invoker = i;
        previous = c;
    }

    @Override
    public Invoker<S> link(final Invoker<? super I> c)
    {
        return previous.link(new Invoker<I>() {
            @Override
            public void process(I input)
            {
                invoker.process(input, new BoundedInvokerAdaptor<I>(c, input));
            }
        });
    }

}
