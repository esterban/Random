package org.srwcrw.poker.texasholdem.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestPropertySource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = {ExecutionTimeRecorder.class, ExecutionTimeProperties.class})
@TestPropertySource(locations = "classpath:application-test.yaml") // Uncommented to load properties
public class ExecutionTimeRecorderTest {
    @TempDir
    Path tempDir;

    @Autowired
    private ExecutionTimeRecorder executionTimeRecorder;

    @Autowired
    private ExecutionTimeProperties properties;

    @BeforeEach
    void setup() {
        // Set the output directory for the properties to the temporary directory
        properties.setOutputDir(tempDir.toAbsolutePath().toString());
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    void shouldRecordExecutionTimeCorrectly() throws IOException {
        // Given
        SpringApplication application = new SpringApplication();
        ConfigurableApplicationContext applicationContext = mock(ConfigurableApplicationContext.class);
        ApplicationReadyEvent event = new ApplicationReadyEvent(
                application,
                new String[]{},
                applicationContext,
                Duration.ZERO
        );

        executionTimeRecorder.onApplicationReady(event);

        // Start timing before the simulated execution
        executionTimeRecorder.startTiming();

        // Simulate some execution time
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Stop timing after the simulated execution
        executionTimeRecorder.stopTiming();

        // When
        executionTimeRecorder.recordExecutionTime(); // Now this call will have valid start/end times

        // Then
        Path[] files = Arrays.stream(Objects.requireNonNull(tempDir.toFile().listFiles()))
                .map(File::toPath)
                .toArray(Path[]::new);

        assertThat(files).hasSize(1);

        List<String> lines = Files.readAllLines(files[0]);
        assertThat(lines).hasSize(9);
        assertThat(lines.get(0)).isEqualTo("Application Execution Time:");
        assertThat(lines.get(1)).startsWith("Start Time: ");
        assertThat(lines.get(2)).startsWith("End Time: ");
        assertThat(lines.get(3)).matches("Total Duration: \\d+ ms \\(\\d+\\.\\d{2} seconds\\)");

        // Verify timestamps are properly formatted
        String startTime = lines.get(1).substring("Start Time: ".length());
        String endTime = lines.get(2).substring("End Time: ".length());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(properties.getDateFormat());

        assertThatCode(() -> LocalDateTime.parse(startTime, formatter))
                .doesNotThrowAnyException();
        assertThatCode(() -> LocalDateTime.parse(endTime, formatter))
                .doesNotThrowAnyException();
    }
}