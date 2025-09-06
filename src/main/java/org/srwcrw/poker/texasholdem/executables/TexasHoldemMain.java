package org.srwcrw.poker.texasholdem.executables;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.srwcrw.poker.texasholdem.components.ExecutionTimeRecorder;
import org.srwcrw.poker.texasholdem.components.ExecutionTimeProperties;
import org.srwcrw.poker.texasholdem.components.TexasHoldemComponent;


@SpringBootApplication(scanBasePackages = {"org/srwcrw/poker/texasholdem/components"})
public class TexasHoldemMain extends SpringApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(TexasHoldemMain.class);

    @Autowired
    private TexasHoldemComponent texasHoldemComponent;

    @Value("#{${texasholdemcomponent.handCount}}")
    private int handCount;

    @Value("${texasholdemmain.loopCount}")
    private int loopCount;

    @Autowired
    private final ExecutionTimeRecorder executionTimeRecorder;

    @Autowired
    private ExecutionTimeProperties executionTimeProperties;

    public TexasHoldemMain(ExecutionTimeRecorder executionTimeRecorder) {
        this.executionTimeRecorder = executionTimeRecorder;
    }

    @PostConstruct
    private void init() {
        executionTimeRecorder.startTiming();

        for (int loopCounter = 1; loopCounter <= loopCount; ++loopCounter) {
            double executionTime = texasHoldemComponent.monteCarloOneOpponent();
            executionTimeProperties.addExecutionTimeValue(executionTime);
        }

        executionTimeRecorder.stopTiming();

        LOGGER.warn("Number of outer iterations {}, inner iterations {}", loopCount, handCount);
        LOGGER.warn("Mean execution {} , standard deviation {}",
                String.format("Execution time = %4.3f", executionTimeProperties.getMeanExecutionTime()),
                String.format("Execution time = %4.3f", executionTimeProperties.getStandardDeviationExecutionTime()));
    }

    public static void main(String[] args) {
        SpringApplication.run(TexasHoldemMain.class, args);
    }
}