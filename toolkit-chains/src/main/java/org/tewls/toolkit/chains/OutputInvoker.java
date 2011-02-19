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
 * Implement this inteface when you want to both:
 * </p>
 * <ul>
 * <li>invoke methods on the object flowing back through the chain.</li>
 * <li>catch unhandled exceptions thrown by processing objects farther down the chain.</li>
 * </ul>
 * <p>
 * If you don't need to handle exceptions than you should implement the
 * {@link org.tewls.toolkit.chains.Invoker} interface instead.
 * </p>
 * 
 * @author Scott Taylor
 * 
 * @param <T>
 */
public interface OutputInvoker<T>
{
    <O extends T> O process(Outputter<O> chain);
}
