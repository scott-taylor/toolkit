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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tewls.toolkit.chains.Changer;
import org.tewls.toolkit.chains.InputChanger;

/**
 * 
 * @author Scott Taylor
 * 
 * @param <I>
 * @param <O>
 */
public final class LinkMock<I, O> implements InputChanger<I, O>
{
    private static final Logger LOG = LoggerFactory.getLogger(LinkMock.class);

    private final LinkController<I, O> controller;

    public LinkMock(LinkController<I, O> c)
    {
        controller = c;
    }

    @Override
    public <X> X process(I input, Changer<O, X> chain)
    {
        LOG.debug("Received '{}'.", input);
        final O output = controller.process(input);
        LOG.debug("Sending '{}'.", output);
        return chain.process(output);
    }

}
