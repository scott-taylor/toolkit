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
import org.tewls.toolkit.core.lang.AssertTools;

/**
 * 
 * @author Scott Taylor
 * 
 * @param <S>
 * @param <I>
 * @param <O>
 * @param <E>
 */
public final class ChangerBuilder<S, I, O, E> implements Linkable<S, I, O, E>
{
    private final Chainable<S, I, O, E> chainable;

    private ChangerBuilder(Chainable<S, I, O, E> chainable)
    {
        AssertTools.notNull(chainable);
        this.chainable = chainable;
    }

    public static <X, Y> Linkable<X, X, Y, Y> create(Class<X> inType, Class<Y> outType)
    {
        AssertTools.notNull(inType);
        AssertTools.notNull(outType);
        return new ChangerBuilder<X, X, Y, Y>(new Nop<X, Y>());
    }

    @Override
    public <X, Y> Linkable<S, X, Y, E> link(InputOutputChanger<? super I, X, Y, O> link)
    {
        AssertTools.notNull(link);
        return new ChangerBuilder<S, X, Y, E>(new ChainableInputOuputChanger<S, I, O, E, X, Y>(chainable, link));
    }

    @Override
    public <Y> Linkable<S, I, Y, E> link(OutputChanger<Y, O> link)
    {
        AssertTools.notNull(link);
        return new ChangerBuilder<S, I, Y, E>(new ChainableOutputChanger<S, I, O, E, Y>(chainable, link));
    }

    @Override
    public <Y> Linkable<S, I, Y, E> link(InputInvokerOutputChanger<? super I, Y, O> link)
    {
        AssertTools.notNull(link);
        return new ChangerBuilder<S, I, Y, E>(new ChainableInputInvokerOutputChanger<S, I, O, E, Y>(chainable, link));
    }

    @Override
    public <Y> Linkable<S, Y, O, E> link(InputChangerOutputInvoker<? super I, Y, O> link)
    {
        AssertTools.notNull(link);
        return new ChangerBuilder<S, Y, O, E>(new ChainableInputChangerOutputInvoker<S, I, O, E, Y>(chainable, link));
    }

    @Override
    public Linkable<S, I, O, E> link(SideEffect link)
    {
        AssertTools.notNull(link);
        return new ChangerBuilder<S, I, O, E>(new ChainableSideEffect<S, I, O, E>(chainable, link));
    }

    @Override
    public Linkable<S, I, O, E> link(InputInvoker<? super I> link)
    {
        AssertTools.notNull(link);
        return new ChangerBuilder<S, I, O, E>(new ChainableInputInvoker<S, I, O, E>(chainable, link));
    }

    @Override
    public Linkable<S, I, O, E> link(OutputInvoker<? super O> link)
    {
        AssertTools.notNull(link);
        return new ChangerBuilder<S, I, O, E>(new ChainableOutputInvoker<S, I, O, E>(chainable, link));
    }

    @Override
    public Linkable<S, I, O, E> link(InputOutputInvoker<? super I, ? super O> link)
    {
        AssertTools.notNull(link);
        return new ChangerBuilder<S, I, O, E>(new ChainableInvoker<S, I, O, E>(chainable, link));
    }

    @Override
    public <X> Linkable<S, X, O, E> linkOnInput(Changer<? super I, X> link)
    {
        AssertTools.notNull(link);
        return new ChangerBuilder<S, X, O, E>(new ChainableOnInputChanger<S, I, O, E, X>(chainable, link));
    }

    @Override
    public <X> Linkable<S, X, O, E> link(InputChanger<? super I, X> link)
    {
        AssertTools.notNull(link);
        return new ChangerBuilder<S, X, O, E>(new ChainableInputChanger<S, I, O, E, X>(chainable, link));
    }

    @Override
    public Linkable<S, I, O, E> linkOnInput(Invoker<? super I> link)
    {
        AssertTools.notNull(link);
        return new ChangerBuilder<S, I, O, E>(new ChainableOnInputInvoker<S, I, O, E>(chainable, link));
    }

    @Override
    public <Y> Linkable<S, I, Y, E> linkOnOutput(Changer<Y, O> link)
    {
        AssertTools.notNull(link);
        return new ChangerBuilder<S, I, Y, E>(new ChainableOnOutputChanger<S, I, O, E, Y>(chainable, link));
    }

    @Override
    public Linkable<S, I, O, E> linkOnOutput(Invoker<? super O> link)
    {
        AssertTools.notNull(link);
        return new ChangerBuilder<S, I, O, E>(new ChainableOnOutputInvoker<S, I, O, E>(chainable, link));
    }

    @Override
    public Changer<S, E> link(final Changer<? super I, ? extends O> chain)
    {
        AssertTools.notNull(chain);
        return chainable.link(new Changer<I, O>() {
            @Override
            public O process(I input)
            {
                return chain.process(input);
            }
        });
    }

    static class Nop<I, O> implements Chainable<I, I, O, O>
    {
        @Override
        public Changer<I, O> link(final Changer<I, O> c)
        {
            return new Changer<I, O>()
            {
                @Override
                public O process(I input)
                {
                    return c.process(input);
                }
            };
        }
    }

}
