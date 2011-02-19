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
package org.tewls.toolkit.chains;

/**
 * A side effect isn't interested in what is flowing through the chain, only that something is
 * flowing through the chain. They typically interact with other objects than those flowing through
 * the system.
 * 
 * For example: Starting and stopping a stop watch to track execution time.
 * 
 * SideEffects should normally propagate back any object returned or exception thrown from the
 * chain.
 * 
 * @author Scott Taylor
 */
public interface SideEffect
{
    <O> O process(Outputter<O> chain);
}
