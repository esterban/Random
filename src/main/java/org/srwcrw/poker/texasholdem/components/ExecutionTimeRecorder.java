package org.srwcrw.poker.texasholdem.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import jakarta.annotation.PreDestroy;
import org.springframework.validation.annotation.Validated;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Validated
public class ExecutionTimeRecorder {
    private static final Logger log = LoggerFactory.getLogger(ExecutionTimeRecorder.class);
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private long executionTimeMs;

    @Autowired
    private final ExecutionTimeProperties properties;

    @Value("${texasholdemcomponent.matchingSuit}")
    private boolean matchingSuit;

    @Value("#{${texasholdemcomponent.handCount}}")
    private int handCount;  // Changed to primitive type

    @Value("${texasholdemcomponent.opponentCount}")
    private int opponentCount;  // Changed to primitive type


    public ExecutionTimeRecorder(ExecutionTimeProperties properties) {
        this.properties = properties;
        createOutputDirectory();
    }

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        // Just log that we're ready, but don't start timing yet
        log.info("Application ready at: {}",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(properties.getTimeFormat())));
    }

    public void startTiming() {
        startDateTime = LocalDateTime.now();
        log.info("Started timing at: {}",
                startDateTime.format(DateTimeFormatter.ofPattern(properties.getTimeFormat())));
    }

    public void stopTiming() {
        endDateTime = LocalDateTime.now();
        executionTimeMs = java.time.Duration.between(startDateTime, endDateTime).toMillis();
        log.info("Stopped timing at: {}",
                endDateTime.format(DateTimeFormatter.ofPattern(properties.getTimeFormat())));
    }

    @PreDestroy
    public void recordExecutionTime() {
        if (startDateTime == null || endDateTime == null) {
            log.warn("Timing was not properly started/stopped, skipping execution time recording");
            return;
        }

        writeExecutionTime();
    }

    private void createOutputDirectory() {
        try {
            Files.createDirectories(Paths.get(properties.getOutputDir()));
        } catch (IOException e) {
            log.error("Failed to create directory: {}", e.getMessage());
        }
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
                "Texas Holdem Configuration:",
                "Matching Suit Enabled: " + matchingSuit,
                "Hand Count: " + handCount,
                "Opponent Count: " + opponentCount
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