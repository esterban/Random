package org.srwcrw.poker.texasholdem.collections;

public final class Hand5Ordinal implements IHandOrdinal<Hand5Card> {
    private final long ordinal;

    Hand5Ordinal(long ordinal) {
        this.ordinal = ordinal;
    }

    @Override
    public long getOrdinal() {
        return ordinal;
    }
}
