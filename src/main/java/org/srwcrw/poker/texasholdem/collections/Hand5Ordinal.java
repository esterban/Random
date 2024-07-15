package org.srwcrw.poker.texasholdem.collections;

public final class Hand5Ordinal implements IHandOrdinal {
    private final long ordinal;

    public Hand5Ordinal(long ordinal) {
        this.ordinal = ordinal;
    }

    @Override
    public long getOrdinal() {
        return ordinal;
    }
}
