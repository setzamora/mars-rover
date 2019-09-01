package au.com.buenosystems.marsrover.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class EventStore extends ArrayList<Event> {

    private static EventStore instance = new EventStore();

    private EventStore() {
    }

    public static EventStore getInstance() {
        return instance;
    }

    @Override
    public Event remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeIf(Predicate<? super Event> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Event set(int index, Event element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Event get(int index) {
        return super.get(index).copy();
    }
}
