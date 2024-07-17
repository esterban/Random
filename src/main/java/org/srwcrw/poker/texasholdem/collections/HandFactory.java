package org.srwcrw.poker.texasholdem.collections;

public  interface  HandFactory<T extends IPack> {
    T create();
}
