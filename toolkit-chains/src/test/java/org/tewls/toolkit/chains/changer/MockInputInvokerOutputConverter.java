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
import org.tewls.toolkit.chains.InputInvokerOutputChanger;
import org.tewls.toolkit.chains.Invoker;
import org.tewls.toolkit.chains.Outputter;

/**
 * 
 * @author Scott Taylor
 * 
 * @param <I>
 * @param <O>
 */
public final class MockInputInvokerOutputConverter<I, O> implements InputInvokerOutputChanger<I, O, O>
{
    private final Invoker<I> invoker;
    private final Changer<O, O> converter;

    public MockInputInvokerOutputConverter(Invoker<I> invoker, Changer<O, O> converter)
    {
        this.invoker = invoker;
        this.converter = converter;
    }

    @Override
    public O process(I input, Outputter<O> chain)
    {
        invoker.process(input);
        return converter.process(chain.process());
    }

}
