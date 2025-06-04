package spring.toolschat.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateTimeTools {
    /**
     * Get the current date and time in the user's timezone.
     *
     * @return the time in ISO-8601 format
     */
    @Tool(description = "Get the current date and time in the user's timezone")
    String getCurrentDateTime() {
        var currentTime = LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
        log.info("Current time is {}", currentTime);

        return currentTime;
    }

    /**
     * Set a user alarm for the given time.
     *
     * @param time the time in ISO-8601 format.
     */
    @Tool(description = "Set a user alarm for the given time")
    void setAlarm(@ToolParam(description = "Time in ISO-8601 format") String time) {
        var alarmTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        log.info("Alarm set for {}", alarmTime);
    }
}
