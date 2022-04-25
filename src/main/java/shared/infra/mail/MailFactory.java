package shared.infra.mail;

import academico.infra.config.Config;
import shared.domain.contracts.EmailFactory;
import shared.domain.events.MailEvent;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public class MailFactory implements EmailFactory {
    private final Session session;
    private final MailQueue queue;
    private final Config.Email config;

    public MailFactory(Session session, MailQueue queue, Config.Email config) {
        this.session = session;
        this.queue = queue;
        this.config = config;
    }

    @Override
    public void newEmail(MailEvent emailDataObject)  {
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(config.FROM, config.FROMNAME));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDataObject.getMailAddress()));
            msg.setSubject(emailDataObject.getHeaderMessage());
            msg.setContent(emailDataObject.getBodyMessage(),"text/html");

            queue.addEmail(msg);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
