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
import org.tewls.toolkit.chains.Invoker;

/**
 * Adapts a Invoker into a Changer that returns void.
 * 
 * @author Scott Taylor
 * 
 * @param <I>
 */
public final class ChangerAdaptor<I> implements Changer<I, Class<Void>>
{
    private final Invoker<? super I> invoker;

    public ChangerAdaptor(Invoker<? super I> acceptor)
    {
        this.invoker = acceptor;
    }

    @Override
    public Class<Void> process(I input)
    {
        invoker.process(input);
        return Void.TYPE;
    }
}
