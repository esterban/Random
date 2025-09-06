package org.srwcrw.poker.texasholdem.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class ExecutionTimeRecorder {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private long executionTimeMs;

    @Autowired
    private ExecutionTimeProperties properties;

    @Value("${texasholdemcomponent.matchingSuit}")
    private boolean matchingSuit;

    @Value("#{${texasholdemcomponent.handCount}}")
    private int handCount;

    @Value("${texasholdemcomponent.opponentCount}")
    private int opponentCount;

    @Value("${texasholdemmain.loopCount}") // Injected loopCount directly
    private int loopCount;

    public void startTiming() {
        startDateTime = LocalDateTime.now();
    }

    public void stopTiming() {
        endDateTime = LocalDateTime.now();
        executionTimeMs = Duration.between(startDateTime, endDateTime).toMillis();
    }

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        if (startDateTime != null && endDateTime != null) {
            recordExecutionTime();
        }
    }

    public void recordExecutionTime() {
        writeExecutionTime();
    }

    private void writeExecutionTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(properties.getDateFormat());

        String fileName = String.format("%s_%s.txt",
                properties.getFilePrefix(),
                endDateTime.format(formatter));

        Path filePath = Paths.get(properties.getOutputDir(), fileName);

        List<String> lines = Arrays.asList(
                "Application Execution Time:",
                "Start Time: " + startDateTime.format(formatter),
                "End Time: " + endDateTime.format(formatter),
                String.format("Total Duration: %d ms (%.2f seconds)", executionTimeMs, executionTimeMs / 1000.0),
                "",
                "Mean Execution Time: " + String.format("%.3f", properties.getMeanExecutionTime()),
                "Standard Deviation of Execution Time: " + String.format("%.3f", properties.getStandardDeviationExecutionTime()),
                "",
                "Texas Holdem Configuration:",
                "Matching Suit Enabled: " + matchingSuit,
                "Hand Count: " + handCount,
                "Opponent Count: " + opponentCount,
                "Loop Count: " + loopCount // Using the injected loopCount
        );

        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            lines.forEach(line -> {
                try {
                    writer.write(line + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            log.info("Execution time record written to: {}", filePath);
        } catch (IOException e) {
            log.error("Failed to write execution time: {}", e.getMessage());
        }
    }
}