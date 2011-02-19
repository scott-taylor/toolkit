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
 * <li>create, or change the type of instance of the object flowing forward through the chain.
 * <li>invoke methods on the object flowing back through the chain.</li>
 * </li>
 * </ul>
 * 
 * @author Scott Taylor
 * 
 * @param <From> The type of object this class requires as input.
 * @param <To> The type of object this class will output to the chain.
 * @param <T> The class type whose methods you will be invoking on the output object.
 * 
 */
public interface InputChangerOutputInvoker<From, To, T>
{
    <O extends T> O process(From input, Changer<To, O> chain);
}
