package org.tewls.toolkit.chains.invoker;

import org.tewls.toolkit.chains.Invoker;
import org.tewls.toolkit.chains.Outputter;

final class BoundedInvokerAdaptor<T> implements Outputter<Class<Void>>
{
    private final Invoker<? super T> invoker;
    private final T input;
    
    public BoundedInvokerAdaptor(Invoker<? super T> invoker, T input)
    {
        this.invoker = invoker;
        this.input = input;
    }

    @Override
    public Class<Void> process()
    {
        invoker.process(input);
        return Void.TYPE;
    }

}
