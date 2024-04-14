package at.home.sharedcalendar.repository.model;


import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Serdeable
@Table(name = "entries")
public class Entry {

    @Id
    @Column(name = "ID", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(name = "name", unique = false, updatable = true, nullable = false)
    private String name;

    @Column(name = "description", unique = false, updatable = true, nullable = false)
    private String description;

    @Column(name = "start_date_time", unique = false, updatable = true, nullable = false)
    private OffsetDateTime startDateTime;

    @Column(name = "end_date_time", unique = false, updatable = true, nullable = false)
    private OffsetDateTime endDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id", referencedColumnName = "id", unique = false, updatable = true, nullable = false)
    private Calendar calendar;

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

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
