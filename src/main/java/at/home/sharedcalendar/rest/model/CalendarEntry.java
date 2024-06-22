package at.home.sharedcalendar.rest.model;


import io.micronaut.serde.annotation.Serdeable;

import java.time.OffsetDateTime;

@Serdeable
public class CalendarEntry {
    private String uuid;

    private String name;

    private String description;

    private OffsetDateTime startDateTime;

    private OffsetDateTime endDateTime;

    private Boolean allDay;

    private String calendarUuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(OffsetDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public OffsetDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(OffsetDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    public String getCalendarUuid() {
        return calendarUuid;
    }

    public void setCalendarUuid(String calendarUuid) {
        this.calendarUuid = calendarUuid;
    }

    @Override
    public String toString() {
        return "CalendarEntry [name=" + name + ", description=" + description + ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime + ", calendarUuid=" + calendarUuid + "]";
    }
}
