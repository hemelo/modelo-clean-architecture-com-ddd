package academico.infra.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public Properties properties;
    public Email email;

    public Config() throws IOException {
        this.properties = new Properties();
        InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties");
        properties.load(input);
        this.email = new Email();
    }

    public class Email {
        public final String FROM;
        public final String FROMNAME;

        // Replace smtp_username with your Oracle Cloud Infrastructure SMTP username generated in console.
        public final String SMTP_USERNAME;

        // Replace smtp_password with your Oracle Cloud Infrastructure SMTP password generated in console.
        public final String SMTP_PASSWORD;

        // Oracle Cloud Infrastructure Email Delivery hostname.
        public final String HOST;

        // The port you will connect to on the SMTP endpoint. Port 25 or 587 is allowed.
        public final int PORT;

        public Email() {
            FROM = properties.getProperty("mail.smtp.from");
            FROMNAME = properties.getProperty("mail.smtp.username");
            SMTP_USERNAME = properties.getProperty("mail.smtp.user");
            SMTP_PASSWORD = properties.getProperty("mail.smtp.password");
            HOST = properties.getProperty("mail.smtp.host");
            PORT = Integer.parseInt(properties.getProperty("mail.smtp.port"));
        }
    }
}
