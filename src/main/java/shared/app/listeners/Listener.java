package shared.app.listeners;

import shared.app.events.Event;

public abstract class Listener {
    public void listen(Event event) {
        if(shouldBeHandle(event)) this.handle(event);
    }

    protected abstract void handle(Event event);
    protected abstract boolean shouldBeHandle(Event event);
}
