package at.home.sharedcalendar.rest.model;


import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Calendar {

    private String uuid;

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

    @Override
    public String toString() {
        return "Calendar [name=" + name + "]";
    }
}
