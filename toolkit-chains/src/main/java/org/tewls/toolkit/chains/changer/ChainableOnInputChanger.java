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

/**
 * 
 * @author Scott Taylor
 * 
 * @param <S>
 * @param <I>
 * @param <O>
 * @param <E>
 * @param <X>
 */
final class ChainableOnInputChanger<S, I, O, E, X> implements Chainable<S, X, O, E>
{
    private final Chainable<S, I, O, E> chainable;
    private final Changer<? super I, X> link;

    public ChainableOnInputChanger(Chainable<S, I, O, E> chainable, Changer<? super I, X> link)
    {
        this.chainable = chainable;
        this.link = link;
    }

    @Override
    public Changer<S, E> link(final Changer<X, O> chain)
    {
        return chainable.link(new Changer<I, O>() {
            @Override
            public O process(I input)
            {
                return chain.process(link.process(input));
            }
        });
    }
}
