package at.home.sharedcalendar.repository.model;


import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Serdeable
@Table(name = "entries")
public class CalendarEntryModel {

    @Id
    @Column(name = "ID", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(name = "name", unique = false, updatable = true, nullable = false)
    private String name;

    @Column(name = "description", unique = false, updatable = true, nullable = false)
    private String description;

    @Column(name = "start_date_time", unique = false, updatable = true, nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time", unique = false, updatable = true, nullable = false)
    private LocalDateTime endDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id", referencedColumnName = "id", unique = false, updatable = true, nullable = false)
    private CalendarModel calendar;

    public String getUuid() {
        return uuid;
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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public CalendarModel getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarModel calendarModel) {
        this.calendar = calendarModel;
    }
}
