package org.tewls.toolkit.chains.changer;

import org.tewls.toolkit.chains.Changer;
import org.tewls.toolkit.chains.Outputter;

final class BoundedChanger<I, O> implements Outputter<O>
{
    private final Changer<I, O> changer;
    private final I input;

    public BoundedChanger(Changer<I, O> changer, I input)
    {
        this.changer = changer;
        this.input = input;
    }

    @Override
    public O process()
    {
        return changer.process(input);
    }

}
