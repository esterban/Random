package org.srwcrw.poker.texasholdem.executables;

import jakarta.annotation.PostConstruct;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.srwcrw.poker.texasholdem.components.TexasHoldemComponent;


@SpringBootApplication(scanBasePackages = {"org/srwcrw/poker/texasholdem/components"})
//@Profile()
public class TexasHoldemMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(TexasHoldemMain.class);

    @Autowired
    private TexasHoldemComponent texasHoldemComponent;

    @Value("#{${texasholdemcomponent.handCount}}")
    private int handCount;

    @Value("${texasholdemmain.loopCount}")
    private int loopCount;

    @PostConstruct
    private void init() {
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();

        for (int loopCounter = 1; loopCounter <= loopCount; ++loopCounter) {
            double executionTime = texasHoldemComponent.monteCarloOneOpponent();
            descriptiveStatistics.addValue(executionTime);
        }

        LOGGER.warn("Number of outer iterations {}, inner iterations {}", loopCount, handCount);
        LOGGER.warn("Mean exection {} , standard deviation {}",
                String.format("Execution time = %4.3f", descriptiveStatistics.getMean()),
                String.format("Execution time = %4.3f", descriptiveStatistics.getStandardDeviation()));
    }

    public static void main(String[] args) {
        SpringApplication.run(TexasHoldemMain.class, args);
    }
}
