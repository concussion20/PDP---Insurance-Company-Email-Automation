package assignment3_refactored;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for FileOperator.
 * @author Chang Zhou
 */
public class FileOperatorTest {
  private FileOperator fileOperator;

  @Before
  public void setUp() throws Exception {
    fileOperator = new FileOperator();
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(fileOperator.hashCode(), fileOperator.hashCode());
  }

  @Test
  public void testEquals() {
    Assert.assertEquals(fileOperator, fileOperator);
  }

  @Test
  public void testToString() {
    Assert.assertEquals(fileOperator.toString(), fileOperator.toString());
  }

  @Test
  public void readFile() {
    try {
      Assert.assertEquals("From: insuranceCompany@ic.com\n"
          + "To: [[email]]\n"
          + "Subject: Insurance company – information about recent data breach\n"
          + "Dear [[first_name]] [[last_name]],\n"
          + "As you may have heard or read, last month we learned that criminals forced"
          + " their way into our systems, \n"
          + "and stole information about our customers. Late last week, as part of our ongoing"
          + " investigation, \n"
          + "we learned that the taken information includes names, mailing addresses, phone "
          + "numbers or email addresses.\n"
          + " \n"
          + "I am writing to make you aware that your name, mailing address, phone number"
          + " or email address may have been \n"
          + "taken during the intrusion. \n"
          + "\n"
          + "I am truly sorry this incident occurred, and I sincerely regret any "
          + "inconvenience it may cause you. \n"
          + "\n"
          + "Because we value you as a customer, and because your trust is important to us,"
          + " our company is offering you one \n"
          + "year of free credit monitoring through Experian’s ProtectMyID product, which"
          + " includes identity theft insurance \n"
          + "where available. You will receive more information about this offer via regular mail.\n"
          + "\n"
          + "You can find additional information and FAQs about this incident at our website."
          + " If you have further questions, \n"
          + "you may call us at 866-852-8680. \n"
          + "\n"
          + "Thank you for your patience and your loyalty. \n"
          + "Sincerely,\n"
          + "Insurance Company CEO\n", FileOperator.readFile("files/emailtemplate.txt"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void writeFile() {
    try {
      FileOperator.writeFile("test.txt", "123\n777");
      Assert.assertEquals("123\n777\n", FileOperator.
          readFile("test.txt"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}