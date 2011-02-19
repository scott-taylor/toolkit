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
import org.tewls.toolkit.chains.InputOutputChanger;

/**
 * 
 * @author Scott Taylor
 * 
 * @param <S>
 * @param <I>
 * @param <O>
 * @param <E>
 */
public final class MockLink<S, I, O, E> implements InputOutputChanger<S, I, O, E>
{
    private final Changer<S, I> inputConverter;
    private final Changer<O, E> outputConverter;

    public MockLink(Changer<S, I> inputConverter, Changer<O, E> outputConverter)
    {
        this.inputConverter = inputConverter;
        this.outputConverter = outputConverter;
    }

    @Override
    public E process(S input, Changer<I, O> c)
    {
        return outputConverter.process(c.process(inputConverter.process(input)));
    }

}
