import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    public static void main(String[] args) {
        final String senderEmail = "your_email@example.com"; // Replace with your email address
        final String senderPassword = "your_password"; // Replace with your email password
        final String smtpHost = "smtp.example.com"; // Replace with your SMTP server host
        final int smtpPort = 465; // Replace with your SMTP server port

        // Recipient list
        String[] recipients = {"recipient1@example.com", "recipient2@example.com"}; // Replace with recipient email addresses

        // Email content
        String subject = "Your Newsletter Subject";
        String message = "Hello,\n\nThis is your newsletter content.\n\nRegards,\nYour Name";

        // Set mail properties
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");

        // Get session object
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create MimeMessage object
            Message mimeMessage = new MimeMessage(session);

            // Set sender email
            mimeMessage.setFrom(new InternetAddress(senderEmail));

            // Set recipient email addresses
            InternetAddress[] recipientAddresses = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                recipientAddresses[i] = new InternetAddress(recipients[i]);
            }
            mimeMessage.setRecipients(Message.RecipientType.TO, recipientAddresses);

            // Set email subject and content
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            // Send email
            Transport.send(mimeMessage);

            System.out.println("Emails sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
