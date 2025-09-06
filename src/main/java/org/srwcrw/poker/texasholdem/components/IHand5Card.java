package org.srwcrw.poker.texasholdem.components;

import org.srwcrw.poker.texasholdem.collections.IPack;

import java.util.AbstractMap;

public interface IHand5Card extends IPack {
    AbstractMap.SimpleEntry<Card, IPack> removeRandomCardImmutable();
}
