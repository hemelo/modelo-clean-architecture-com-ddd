package shared.infra.mail;

import academico.infra.config.Config;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Queue;

public class MailSender implements Runnable {
    private final Queue<MimeMessage> queue;
    private final Session session;
    private final Config.Email config;

    public MailSender(Session session, Queue queue, Config.Email config) {
        this.session = session;
        this.queue = queue;
        this.config = config;
    }

    @Override
    public void run() {
        while(true) {
            MimeMessage message = this.queue.poll();
            try {
                if(message != null) this.sendEmail(message);
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
                this.queue.add(message);
                break;
            }
        }
    }

    public void sendEmail(MimeMessage email) throws MessagingException {
        Transport transport = session.getTransport();
        try {
            System.err.println();
            transport.connect(config.HOST, config.SMTP_USERNAME, config.SMTP_PASSWORD);
            transport.sendMessage(email, email.getAllRecipients());
        }
        finally {
            transport.close();
        }
    }
}
