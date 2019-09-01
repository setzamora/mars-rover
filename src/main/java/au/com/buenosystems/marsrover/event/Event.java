package au.com.buenosystems.marsrover.event;

import java.time.Instant;
import java.util.Objects;

public class Event {

    public static final String ROVER_MOVED = "ROVER_MOVED";
    public static final String ROVER_ROTATED_LEFT = "ROVER_ROTATED_LEFT";
    public static final String ROVER_ROTATED_RIGHT = "ROVER_ROTATED_RIGHT";
    public static final String ROVER_COMMAND_FAILED = "ROVER_COMMAND_FAILED";

    private String name;
    private String description;
    private Instant dateTime;

    private Event(String name, String description, Instant dateTime) {
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
    }

    private Event(String name, String description) {
        this(name, description, Instant.now());
    }

    private Event(Event that) {
        this(that.name, that.description, that.dateTime);
    }

    private Event() { }

    public static Event create(String name, String description) {
        return new Event(name, description);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(name, event.name) &&
                Objects.equals(description, event.description) &&
                Objects.equals(dateTime, event.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, dateTime);
    }

    /**
     * Create a copy of the event for stored events to be immutable
     * @return A copy of the event
     */
    public Event copy() {
        return new Event(this);
    }
}
