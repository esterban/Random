package org.srwcrw.poker.texasholdem.components;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "app.execution-time")
@Configuration
public class ExecutionTimeProperties {
    private String outputDir = "src/main/docs/optimisationrecord";
    private String filePrefix = "execution_time";
    private String dateFormat = "yyyyMMdd_HHmmss";
    private String timeFormat = "yyyy-MM-dd HH:mm:ss.SSS";

    // Getters and setters
    public String getOutputDir() { return outputDir; }
    public void setOutputDir(String outputDir) { this.outputDir = outputDir; }
    public String getFilePrefix() { return filePrefix; }
    public void setFilePrefix(String filePrefix) { this.filePrefix = filePrefix; }
    public String getDateFormat() { return dateFormat; }
    public void setDateFormat(String dateFormat) { this.dateFormat = dateFormat; }
    public String getTimeFormat() { return timeFormat; }
    public void setTimeFormat(String timeFormat) { this.timeFormat = timeFormat; }
}