package application;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailController {
    public static void main(String[] args) {
        // sendEmail(to);
    }

    // This method is used for sending the email.
    public static void ResetPasswordEmail(String to, int Passkey) {
        String message = "Hello! This is an automated message from Capital Bank. \n"
                + "This Email is regarding the resetting of your password \n"
                + "Kindly Enter this Passkey " + Passkey + " to reset your password. \n "
                + "Thank You. \n";
        
        String subject = "Password Reset";
        String from = "codehuzefa@gmail.com";
        
        // Variable for Gmail.
        String host = "smtp.gmail.com";
        
        // Get the system properties.
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");

        // Step 1: Create a session.
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "emvx udym icev cgns");
            }
        });
        
        session.setDebug(false);

        // Step 2: Compose the message.
        MimeMessage m = new MimeMessage(session);
        try {
            // From email.
            m.setFrom(new InternetAddress(from));
        
            // Adding recipient to message.
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            
            // Adding subject.
            m.setSubject(subject);
            
            // Adding message.
            m.setText(message);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Step 3: Send the message using Transport class.
        try {
            Transport.send(m);
            System.out.println("Email sent.");
//            lblConfirmation.setText("Email Sent Successfully. Please check your email to reset password.");
        } catch (Exception e) {
        	System.out.println("74");
//        	lblConfirmation.setText("Failed to send Email. Please try again.");
        }
    }
}


