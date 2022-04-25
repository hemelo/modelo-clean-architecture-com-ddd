package shared.domain.events;

public interface MailEvent {
    String getMailAddress();
    String getHeaderMessage();
    String getBodyMessage();
}
