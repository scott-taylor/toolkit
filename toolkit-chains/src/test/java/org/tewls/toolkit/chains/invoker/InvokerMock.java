/*
 * Copyright 2010 the original author or autimport org.slf4j.Logger;
import org.slf4j.LoggerFactory;
0 (the "License");
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
import org.tewls.toolkit.chains.InputInvoker;
import org.tewls.toolkit.chains.Outputter;

/**
 * 
 * @author Scott Taylor
 * 
 * @param <T>
 */
final class InvokerMock<T> implements InputInvoker<T>
{
    private static final Logger LOG = LoggerFactory.getLogger(InvokerMock.class);

    private final InvokerController<T> controller;

    public InvokerMock(InvokerController<T> c)
    {
        controller = c;
    }

    @Override
    public <O> O process(T input, Outputter<O> chain)
    {
        LOG.debug("Received '{}'.", input);
        controller.process(input);
        return chain.process();
    }

}
