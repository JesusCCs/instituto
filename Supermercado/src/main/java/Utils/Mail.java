package Utils;

import Config.Configuracion;

import javax.mail.*;
import javax.mail.internet.*;

public class Mail {

    private static final Session session;

    static {
        session = Session.getInstance(Configuracion.MailConfigProps, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Configuracion.MailConfigUser, Configuracion.MailConfigPass);
            }
        });
    }

    public static void enviarMail(String subject,String body) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(Configuracion.MailConfigFrom));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(Configuracion.MailConfigTo));

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();

        message.setSubject(subject);
        mimeBodyPart.setContent(body,"text/plain; charset=UTF-8");

        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);

        Transport.send(message);
    }

}
