package assignment3_refactored;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for EmailPosterLocalImpl.
 * @author Chang Zhou
 */
public class EmailPosterLocalImplTest {
  private EmailPosterLocalImpl emailPosterLocalImpl;

  @Before
  public void setUp() throws Exception {
    emailPosterLocalImpl = new EmailPosterLocalImpl();
  }

  @Test
  public void sendEmail() {
    try {
      emailPosterLocalImpl.sendEmail("testSendEmail.txt", "12345");
    } catch (IOException e) {
      e.printStackTrace();
      Assert.fail();
    }
    try {
      final String content = FileOperator.readFile("testSendEmail.txt");
      Assert.assertEquals("12345\n", content);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}