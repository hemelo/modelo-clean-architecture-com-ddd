package shared.app.listeners;

import shared.app.events.Event;
import shared.domain.events.LoggableEvent;

public class CriarLog extends Listener {
    @Override
    protected void handle(Event event) {
        LoggableEvent loggableEvent = (LoggableEvent) event;
        System.out.println(loggableEvent.generateLog());
    }

    @Override
    protected boolean shouldBeHandle(Event event) {
        return event instanceof LoggableEvent;
    }
}
