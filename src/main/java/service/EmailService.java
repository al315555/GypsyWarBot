package service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Properties;

public class EmailService {
    private final Properties properties = new Properties();
    private String password;
    private boolean init;
    private Session session;

    public void init() throws Exception {
        try {
            Properties props = new Properties();
            InputStream is = EmailService.class.getClassLoader().getResourceAsStream("email.properties");
            props.load(is);
            is.close();
            properties.put("mail.smtp.host", props.getProperty("host"));
            properties.put("mail.smtp.starttls.enable", props.getProperty("starttls"));
            properties.put("mail.smtp.port", props.getProperty("port"));
            properties.put("mail.smtp.mail.sender", props.getProperty("mail"));
            properties.put("mail.smtp.user", props.getProperty("user"));
            properties.put("mail.smtp.auth", props.getProperty("auth"));
            properties.put("mail.smtp.password", props.getProperty("password"));
            password = props.getProperty("password");

            session = Session.getDefaultInstance(properties);
            session.setDebug(true);
            init = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex);
        }

    }

    public void sendEmail() throws Exception {
        if (!init) return;

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("al315555@uji.es"));
        message.setSubject("Prueba");
        message.setText("Texto");
        Transport t = session.getTransport("smtp");
        t.connect((String) properties.get("mail.smtp.user"), password);
        t.sendMessage(message, message.getAllRecipients());
        t.close();


    }

}
