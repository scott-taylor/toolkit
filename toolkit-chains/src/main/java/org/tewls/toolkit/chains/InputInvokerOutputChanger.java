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
 * <p>
 * Implement this interface when you want to both:
 * </p>
 * <ul>
 * <li>invoke methods on the object flowing forward through the chain.</li>
 * <li>create a result, or change the type or instance of the object flowing back through the chain.
 * </li>
 * </ul>
 * 
 * @author Scott Taylor
 * 
 * @param <T> The class type whose methods you will be invoking on the input object.
 * @param <From> The type of object you need the rest of the chain to return.
 * @param <To> The type of object this class will pass back up the chain.
 * 
 */
public interface InputInvokerOutputChanger<T, From, To>
{
    To process(T input, Outputter<From> chain);
}
