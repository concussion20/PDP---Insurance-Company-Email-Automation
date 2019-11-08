package assignment3_refactored;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * subclass of EmailPoster.
 * Send emails to real email addresses.
 * @author Chang Zhou
 */
public class EmailPosterRemoteImpl implements EmailPoster {
  private String senderInfoFile;
  private String email;
  private String passwd;
  private String host;
  private String port;
  private static final String SUBJECT = "Insurance company – information about recent data breach";

  /**
   * Constructor. Take a file path and get sender's email info.
   * @param senderInfoFile senderInfoFile
   * @throws IOException IOException
   */
  public EmailPosterRemoteImpl(final String senderInfoFile) throws IOException {
    this.senderInfoFile = senderInfoFile;
    final String senderInfo = FileOperator.readFile(this.senderInfoFile);
    final String[] fields = senderInfo.split(" ");
    email = fields[0];
    passwd = fields[1];
    host = fields[2];
    port = fields[3];
  }

  /**
   * Send emails to real email addresses.
   * @param address email address or local file path
   * @param content email/letter content
   */
  @Override
  public void sendEmail(final String address, final String content) throws MessagingException {
    final Properties properties = new Properties();
    properties.setProperty("mail.transport.protocol", "smtp");
    properties.setProperty("mail.smtp.host", host);
    properties.setProperty("mail.smtp.port", port);
    properties.setProperty("mail.smtp.auth", "true");
    properties.setProperty("mail.smtp.ssl.enable", "true");
    properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    properties.setProperty("mail.smtp.socketFactory.fallback", "false");
    properties.setProperty("mail.smtp.socketFactory.port", port);

    final Session session = Session.getDefaultInstance(properties);
    session.setDebug(true);

    final String filteredContent = content.replaceAll("\n", "<br>");
    final MimeMessage message = createMimeMessage(session, email, address, filteredContent);

    final Transport transport = session.getTransport();
    transport.connect(email, passwd);
    transport.sendMessage(message, message.getAllRecipients());
    transport.close();
  }

  /**
   * Create a new Message.
   * @param session session
   * @param sendMail sendMail
   * @param receiveMail receiveMail
   * @param content content
   * @return a MimeMessage obj
   * @throws MessagingException MessagingException
   */
  public static MimeMessage createMimeMessage(final Session session, final String sendMail,
      final String receiveMail, final String content) throws MessagingException {
    MimeMessage message = new MimeMessage(session);
    message.setFrom(new InternetAddress(sendMail));
    message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail));
    message.setSubject(SUBJECT, "UTF-8");
    message.setContent(content,"text/html;charset=UTF-8");
    message.setSentDate(new Date());
   // message.saveChanges();

    return message;
  }


}
