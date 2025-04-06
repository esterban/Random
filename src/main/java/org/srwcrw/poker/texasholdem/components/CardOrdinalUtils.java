package org.srwcrw.poker.texasholdem.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.srwcrw.poker.texasholdem.entities.Suit;
import org.srwcrw.poker.texasholdem.entities.Value;

@Component
public class CardOrdinalUtils {
    @Autowired
    private CardOrdinalFactory cardOrdinalFactory;

    Suit ordinalToSuit(CardOrdinal cardOrdinal) {
        return cardOrdinalFactory.cardOrdinalToSuit(cardOrdinal);
    }

    Value ordinalToValue(CardOrdinal cardOrdinal) {
        return cardOrdinalFactory.cardOrdinalToValue(cardOrdinal);
    }
}
