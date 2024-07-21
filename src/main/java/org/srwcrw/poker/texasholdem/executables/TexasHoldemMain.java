package org.srwcrw.poker.texasholdem.executables;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.srwcrw.poker.texasholdem.components.TexasHoldemComponent;


@SpringBootApplication(scanBasePackages = {"org/srwcrw/poker/texasholdem/components"})
public class TexasHoldemMain {
    @Autowired
    private TexasHoldemComponent texasHoldemComponent;

    @Value("${texasholdemmain.loopCount}")
    private int loopCount;

    @PostConstruct
    private void init() {
        for (int loopCounter = 1; loopCounter <= loopCount; ++loopCounter) {
            texasHoldemComponent.monteCarloOneOpponent();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(TexasHoldemMain.class, args);
    }
}
