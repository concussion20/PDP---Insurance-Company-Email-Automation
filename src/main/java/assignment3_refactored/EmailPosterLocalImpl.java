package assignment3_refactored;

import java.io.IOException;

/**
 * subclass of EmailPoster.
 * Send emails to local fs.
 * @author Chang Zhou
 */
public class EmailPosterLocalImpl implements EmailPoster {

  /**
   * Send emails to local fs.
   * @param address email address or local file path
   * @param content email/letter content
   * @throws IOException IOException
   */
  @Override
  public void sendEmail(final String address, final String content) throws IOException {
    FileOperator.writeFile(address, content);
  }
}
