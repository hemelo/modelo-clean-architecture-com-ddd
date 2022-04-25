package shared.app.listeners;

import shared.domain.contracts.EmailFactory;
import shared.domain.events.MailEvent;
import shared.app.events.Event;

public class NotificarEmail extends Listener{
    private EmailFactory emailFactory;

    public NotificarEmail(EmailFactory emailFactory) {
        this.emailFactory = emailFactory;
    }

    @Override
    protected void handle(Event event) {
        MailEvent notificableEvent = (MailEvent) event;
        emailFactory.newEmail(notificableEvent);
    }

    @Override
    protected boolean shouldBeHandle(Event event) {
        return event instanceof MailEvent;
    }
}
