package assignment3_refactored;

import java.io.IOException;
import javax.mail.MessagingException;

/**
 * Interface EmailPoster.
 * Use its subclasses to send emails/letters to local fs or real email address.
 * @author Chang Zhou
 */
public interface EmailPoster {

  /**
   * send emails/letters.
   * @param address email address or local file path
   * @param content email/letter content
   * @throws IOException IOException
   * @throws MessagingException MessagingException
   */
  void sendEmail(String address, String content) throws MessagingException, IOException;
}
