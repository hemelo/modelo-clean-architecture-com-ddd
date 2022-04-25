package shared.app.services;

import shared.app.events.Event;
import shared.app.listeners.Listener;

import java.util.ArrayList;
import java.util.List;

public class EventDispatcher {
    private List<Listener> listeners = new ArrayList<>();

    public EventDispatcher adicionar(Listener listener) {
        listeners.add(listener);
        return this;
    }

    public void dispatch(Event event) {
        listeners.forEach(l -> l.listen(event));
    }
}
