package at.home.sharedcalendar.repository.model;


import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Serdeable
@Table(name = "calendars")
public class Calendar {

    @Id
    @Column(name = "ID", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(name = "name", unique = false, updatable = false, nullable = false)
    private String name;

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
}
