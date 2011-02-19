package org.tewls.toolkit.chains.changer;

import org.tewls.toolkit.chains.Changer;

/**
 * @author stailor
 * @deprecated Use ChangerBuilder.create method for creating Changers.
 */
@Deprecated
public final class ChangerBuilderHack<I, O>
{

    /**
     * Do not use this method. This method will be deleted in the
     * near future. Its a hack to allow an older chain package to 
     * use this package for building chains.
     * Use the ChangeBuilder.create method instead.
     * @param chainable
     */
    public ChangerBuilder<I, I, O, O> createChangerBuilderHack()
    {
        Chainable<I, I, O, O> chainable = new Chainable<I, I, O, O>() {
            @Override
            public Changer<I, O> link(final Changer<I, O> chain)
            {
                return new Changer<I, O>()
                {
                    @Override
                    public O process(I input)
                    {
                        return chain.process(input);
                    }
                };
            }
        };
        return new ChangerBuilder<I, I, O, O>(chainable);
    }

}
