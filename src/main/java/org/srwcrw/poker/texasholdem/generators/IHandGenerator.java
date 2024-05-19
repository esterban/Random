package org.srwcrw.poker.texasholdem.generators;

import org.srwcrw.poker.texasholdem.collections.IPack;

import java.util.AbstractMap;

public interface IHandGenerator {
    AbstractMap.SimpleEntry<IPack, IPack> generateHandAndRemoveImmutable(IPack pack, int numberOfCards);
//    IPack generateFullPack();
}
