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
 * <li>change the type or instance of the object flowing forward through the chain.</li>
 * <li>create a result, or change the type or instance of the result flowing back through the chain.
 * </li>
 * </ul>
 * 
 * @author Scott Taylor
 * 
 * @param <LinkInput>
 * @param <ChainInput>
 * @param <ChainOutput>
 * @param <LinkOutput>
 */
public interface InputOutputChanger<LinkInput, ChainInput, ChainOutput, LinkOutput>
{
    /**
     * Process the input.
     * 
     * @param input The input to the Link.
     * @param chain Allows the Link to pass on an ChainInput to the remaining part of the chain and
     *            to receive a ChainOutput from it.
     */
    LinkOutput process(LinkInput input, Changer<ChainInput, ChainOutput> chain);
}
