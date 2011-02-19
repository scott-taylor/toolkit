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

/**
 * 
 * @author Scott Taylor
 *
 * @param <F> from
 * @param <T> to
 */
public final class MockInputChanger<F, T> implements InputChanger<F, T>
{
    private final Changer<F, T> changer;
    
    public MockInputChanger(Changer<F, T> changer)
    {
        this.changer = changer;
    }
    
    @Override
    public <O> O process(F input, Changer<T, O> chain)
    {
        return chain.process(changer.process(input));
    }

}
