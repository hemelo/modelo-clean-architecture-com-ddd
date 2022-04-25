package shared.infra.mail;

import javax.mail.internet.MimeMessage;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MailQueue {
    private Queue<MimeMessage> queue;

    public MailQueue() {
        this.queue = new ConcurrentLinkedQueue<MimeMessage>();
    }

    public void addEmail(MimeMessage message) {
        queue.offer(message);
    }

    public Queue<MimeMessage> getQueue() {
        return queue;
    }
}