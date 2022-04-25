package shared.domain.contracts;

import shared.domain.events.MailEvent;

public interface EmailFactory {
    void newEmail(MailEvent toNotificate);
}
