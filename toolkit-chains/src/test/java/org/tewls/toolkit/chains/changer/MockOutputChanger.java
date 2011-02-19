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
import org.tewls.toolkit.chains.OutputChanger;

/**
 * 
 * @author Scott Taylor
 * 
 * @param <F>
 * @param <T>
 */
public final class MockOutputChanger<F, T> implements OutputChanger<F, T>
{
    private final Changer<F, T> changer;

    public MockOutputChanger(Changer<F, T> converter)
    {
        this.changer = converter;
    }

    @Override
    public <I> T process(I input, Changer<I, F> chain)
    {
        return changer.process(chain.process(input));
    }
}
