package org.srwcrw.poker.texasholdem.generators;

import org.srwcrw.poker.texasholdem.collections.IPack;

public interface Converter<T extends IPack> {

    T convert(IPack from);
}
