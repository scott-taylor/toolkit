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

/**
 * 
 * @author Scott Taylor
 * 
 * @param <S>
 * @param <E>
 */
public interface Linkable<S, E>
{
    <O> Linkable<S, O> link(InputChanger<? super E, O> link);

    Linkable<S, E> link(SideEffect link);

    Linkable<S, E> link(InputInvoker<? super E> link);

    Linkable<S, E> link(Invoker<? super E> link);

    <O> Linkable<S, O> link(Changer<? super E, O> chain);
    
    Invoker<S> build();
    
}
