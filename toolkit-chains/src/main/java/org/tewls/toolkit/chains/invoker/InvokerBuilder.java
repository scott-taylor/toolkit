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

import org.tewls.toolkit.chains.Changer;
import org.tewls.toolkit.chains.InputChanger;
import org.tewls.toolkit.chains.InputInvoker;
import org.tewls.toolkit.chains.Invoker;
import org.tewls.toolkit.chains.SideEffect;
import org.tewls.toolkit.core.lang.AssertTools;

/**
 * 
 * @author Scott Taylor
 * 
 * @param <S>
 * @param <E>
 */
public final class InvokerBuilder<S, E> implements Linkable<S, E>
{
    private final Chainable<S, E> chainable;

    private InvokerBuilder(Chainable<S, E> chainable)
    {
        AssertTools.notNull(chainable);
        this.chainable = chainable;
    }

    public static <T> Linkable<T, T> create(Class<T> clazz)
    {
        AssertTools.notNull(clazz);
        return new InvokerBuilder<T, T>(new Nop<T>());
    }

    @Override
    public <O> Linkable<S, O> link(InputChanger<? super E, O> link)
    {
        AssertTools.notNull(link);
        return new InvokerBuilder<S, O>(new ChainableInputChanger<S, E, O>(chainable, link));
    }

    @Override
    public Linkable<S, E> link(SideEffect link)
    {
        AssertTools.notNull(link);
        return new InvokerBuilder<S, E>(new ChainableSideEffect<S, E>(chainable, link));
    }

    @Override
    public Linkable<S, E> link(InputInvoker<? super E> link)
    {
        AssertTools.notNull(link);
        return new InvokerBuilder<S, E>(new ChainableInputInvoker<S, E>(chainable, link));
    }

    @Override
    public <O> Linkable<S, O> link(Changer<? super E, O> link)
    {
        AssertTools.notNull(link);
        return new InvokerBuilder<S, O>(new ChainableChanger<S, E, O>(chainable, link));
    }

    @Override
    public Linkable<S, E> link(Invoker<? super E> link)
    {
        AssertTools.notNull(link);
        return new InvokerBuilder<S, E>(new ChainableInvoker<S, E>(chainable, link));
    }

    public Invoker<S> build()
    {
        return chainable.link(new Invoker<E>()
        {
            @Override
            public void process(E input)
            {
                // Do nothing;
            }
        });
    }

    static class Nop<T> implements Chainable<T, T>
    {
        @Override
        public Invoker<T> link(final Invoker<? super T> c)
        {
            return new Invoker<T>()
            {
                @Override
                public void process(T input)
                {
                    c.process(input);
                }
            };
        }
    }

}
