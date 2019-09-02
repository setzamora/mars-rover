package au.com.buenosystems.marsrover.command;

import au.com.buenosystems.marsrover.event.Event;
import au.com.buenosystems.marsrover.event.EventStore;

import java.util.Objects;
import java.util.stream.Collectors;

public class RoverCommandStats {
    public static void display() {
        int allCommandsCount = EventStore.getInstance().size();
        int failedCommandsCount = EventStore.getInstance()
                .stream()
                .filter(e -> Objects.equals(Event.ROVER_COMMAND_FAILED, e.getName()))
                .collect(Collectors.toList()).size();
        System.out.println(String.format("\nSent %d command(s) / %d failed.\n", allCommandsCount, failedCommandsCount));
    }
}
