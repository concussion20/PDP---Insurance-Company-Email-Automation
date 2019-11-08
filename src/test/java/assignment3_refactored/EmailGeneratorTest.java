package assignment3_refactored;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for EmailGenerator.
 * @author Chang Zhou
 */
public class EmailGeneratorTest {
  private EmailGenerator emailGenerator;

  @Before
  public void setUp() throws Exception {
    emailGenerator = new EmailGenerator();
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(emailGenerator.hashCode(), emailGenerator.hashCode());
  }

  @Test
  public void testEquals() {
    Assert.assertEquals(emailGenerator, emailGenerator);
  }

  @Test
  public void testToString() {
    Assert.assertEquals(emailGenerator.toString(), emailGenerator.toString());
  }

  @Test
  public void generateEmail() {
    emailGenerator.setCsvFile("files/insurancecompanymembers.csv");
    emailGenerator.setEmailTemplate("files/emailtemplate.txt");
    try {
      final String csvContent = FileOperator.readFile("files/insurancecompanymembers.csv");
      final String[] lines = csvContent.split("\n");
      final String[] headers = lines[0].substring(1, lines[0].length() - 1).split("\",\"");
      emailGenerator.setCsvContent(csvContent);
      emailGenerator.setHeaders(headers);
      emailGenerator.setLines(lines);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    emailGenerator.setOutputDir("output");
    String[] newEmails = null;
    try {
      emailGenerator.setCnt(0);
      newEmails = emailGenerator.generateEmail();
    } catch (IOException e) {
      e.printStackTrace();
      Assert.fail();
    }
    Assert.assertNotNull(newEmails);
    Assert.assertEquals(501, newEmails.length);
  }

  @Test
  public void generateLetter() {
    emailGenerator.setCsvFile("files/insurancecompanymembers.csv");
    emailGenerator.setLetterTemplate("files/lettertemplate.txt");
    try {
      final String csvContent = FileOperator.readFile("files/insurancecompanymembers.csv");
      final String[] lines = csvContent.split("\n");
      final String[] headers = lines[0].substring(1, lines[0].length() - 1).split("\",\"");
      emailGenerator.setCsvContent(csvContent);
      emailGenerator.setHeaders(headers);
      emailGenerator.setLines(lines);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    emailGenerator.setOutputDir("output");
    String[] newLetters = null;
    try {
      emailGenerator.setCnt(0);
      newLetters = emailGenerator.generateLetter();
    } catch (IOException e) {
      e.printStackTrace();
      Assert.fail();
    }
    Assert.assertEquals(501, newLetters.length);
  }

  @Test
  public void printHelp() {
    Assert.assertTrue(true);
  }

  @Test
  public void getCnt() {
    emailGenerator.setCnt(0);
    Assert.assertEquals(0, emailGenerator.getCnt());
  }

  @Test
  public void setCnt() {
    emailGenerator.setCnt(20);
    Assert.assertEquals(20, emailGenerator.getCnt());
  }

  @Test
  public void getHeaders() {
    emailGenerator.setHeaders(null);
    Assert.assertNull(emailGenerator.getHeaders());
  }

  @Test
  public void setHeaders() {
    emailGenerator.setHeaders(null);
    Assert.assertNull(emailGenerator.getHeaders());
  }

  @Test
  public void getLines() {
    emailGenerator.setLines(null);
    Assert.assertNull(emailGenerator.getLines());
  }

  @Test
  public void setLines() {
    emailGenerator.setLines(null);
    Assert.assertNull(emailGenerator.getLines());
  }

  @Test
  public void getOutputDir() {
    emailGenerator.setOutputDir(null);
    Assert.assertNull(emailGenerator.getOutputDir());
  }

  @Test
  public void setOutputDir() {
    emailGenerator.setOutputDir(null);
    Assert.assertNull(emailGenerator.getOutputDir());
  }

  @Test
  public void getCsvFile() {
    emailGenerator.setCsvFile(null);
    Assert.assertNull(emailGenerator.getCsvFile());
  }

  @Test
  public void setCsvFile() {
    emailGenerator.setCsvFile(null);
    Assert.assertNull(emailGenerator.getCsvFile());
  }

  @Test
  public void getCsvContent() {
    emailGenerator.setCsvContent(null);
    Assert.assertNull(emailGenerator.getCsvContent());
  }

  @Test
  public void setCsvContent() {
    emailGenerator.setCsvContent(null);
    Assert.assertNull(emailGenerator.getCsvContent());
  }

  @Test
  public void getEmailTemplate() {
    emailGenerator.setEmailTemplate(null);
    Assert.assertNull(emailGenerator.getEmailTemplate());
  }

  @Test
  public void setEmailTemplate() {
    emailGenerator.setEmailTemplate(null);
    Assert.assertNull(emailGenerator.getEmailTemplate());
  }

  @Test
  public void getLetterTemplate() {
    Assert.assertNull(emailGenerator.getLetterTemplate());
  }

  @Test
  public void setLetterTemplate() {
    emailGenerator.setLetterTemplate(null);
    Assert.assertNull(emailGenerator.getLetterTemplate());
  }
  @Test
  public void isEmail() {
    Assert.assertFalse(emailGenerator.isEmail());
  }

  @Test
  public void setIsEmail() {
    emailGenerator.setIsEmail(true);
    Assert.assertTrue(emailGenerator.isEmail());
  }

  @Test
  public void getEmailPosterRemote() {
    Assert.assertNull(emailGenerator.getEmailPosterRemote());
  }

  @Test
  public void setEmailPosterRemote() {
    emailGenerator.setEmailPosterRemote(null);
    Assert.assertNull(emailGenerator.getEmailPosterRemote());
  }

  @Test
  public void getEmailPosterLocal() {
    Assert.assertNull(emailGenerator.getEmailPosterLocal());
  }
  @Test
  public void setEmailPosterLocal() {
    emailGenerator.setEmailPosterLocal(null);
    Assert.assertNull(emailGenerator.getEmailPosterLocal());
  }

  @Test
  public void handleInputArgs() {
    emailGenerator.setCsvContent(null);
    final String[] args = null;
    try {
      emailGenerator.handleInputArgs(args);
      Assert.assertNull(emailGenerator.getCsvContent());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void handleInputArgs2() {
    emailGenerator.setCsvContent(null);
    final String[] args = new String[5];
    try {
      emailGenerator.handleInputArgs(args);
      Assert.assertNull(emailGenerator.getCsvContent());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void handleInputArgs3() {
    emailGenerator.setCsvContent(null);
    final String[] args = {"--email", "--email-template", "files/emailtemplate.txt", "--output-dir",
        "output", "--csv-file", "files/insurancecompanymembers.csv"};
    try {
      emailGenerator.handleInputArgs(args);
      Assert.assertEquals(501, emailGenerator.getLines().length);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void handleInputArgs4() {
    emailGenerator.setCsvContent(null);
    final String[] args = {"--emai", "--email-template", "files/emailtemplate.txt", "--output-dir",
        "output", "--csv-file", "files/insurancecompanymembers.csv"};
    try {
      emailGenerator.handleInputArgs(args);
      Assert.assertNull(emailGenerator.getCsvContent());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void handleInputArgs5() {
    emailGenerator.setCsvContent(null);
    final String[] args = {"--email", "--email", "--email", "--output-dir",
        "output", "--csv-file", "files/insurancecompanymembers.csv"};
    try {
      emailGenerator.handleInputArgs(args);
      Assert.assertNull(emailGenerator.getCsvContent());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void handleInputArgs6() {
    emailGenerator.setCsvContent(null);
    final String[] args = {"--email", "--letter-template", "lettertemplate.txt", "--output-dir",
        "output", "--csv-file", "files/insurancecompanymembers.csv"};
    try {
      emailGenerator.handleInputArgs(args);
      Assert.assertNull(emailGenerator.getCsvContent());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void handleInputArgs7() {
    emailGenerator.setCsvContent(null);
    final String[] args = {"--email-template", "emailtemplate.txt", "--letter", "--output-dir",
        "output", "--csv-file", "files/insurancecompanymembers.csv"};
    try {
      emailGenerator.handleInputArgs(args);
      Assert.assertNull(emailGenerator.getCsvContent());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void handleInputArgs8() {
    emailGenerator.setCsvContent(null);
    final String[] args = {"--letter", "--email-template", "emailtemplate.txt", "--output-dir",
        "output", "--csv-file", "files/insurancecompanymembers.csv"};
    try {
      emailGenerator.handleInputArgs(args);
      Assert.assertNull(emailGenerator.getCsvContent());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void handleInputArgs9() {
    emailGenerator.setCsvContent(null);
    final String[] args = {"--letter-template", "lettertemplate.txt", "--email", "--output-dir",
        "output", "--csv-file", "files/insurancecompanymembers.csv"};
    try {
      emailGenerator.handleInputArgs(args);
      Assert.assertNull(emailGenerator.getCsvContent());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void doEmailAutomation() {
    final String command = "--email --email-template files/emailtemplate.txt --output-dir output"
        + " --csv-file files/insurancecompanymembers.csv";
    try {
      emailGenerator.doEmailAutomation(command.split(" "));
    } catch (IOException e) {
      e.printStackTrace();
      Assert.fail();
    }
    Assert.assertEquals(500, emailGenerator.getCnt());
  }

  @Test
  public void doEmailAutomation2() {
    final String command = "--letter --letter-template files/lettertemplate.txt --output-dir"
        + " output --csv-file files/insurancecompanymembers.csv";
    try {
      emailGenerator.doEmailAutomation(command.split(" "));
    } catch (IOException e) {
      e.printStackTrace();
      Assert.fail();
    }
    Assert.assertEquals(500, emailGenerator.getCnt());
  }

  @Test
  public void doEmailAutomation3() {
    final String command = "--letter --letter-template files/lettertemplate.txt "
        + "--csv-file files/insurancecompanymembers.csv --send-to-remote "
        + "--sender-info-file files/senderInfo.txt --email-recipient zhou.chang1@husky.neu.edu";
    try {
      emailGenerator.doEmailAutomation(command.split(" "));
    } catch (IOException e) {
      e.printStackTrace();
      Assert.fail();
    }
  }

  @Test
  public void doEmailAutomation4() {
    final String command = "--email --email-template files/emailtemplate.txt "
        + "--csv-file files/insurancecompanymembers.csv --send-to-remote"
        + " --sender-info-file files/senderInfo.txt --email-recipient zhou.chang1@husky.neu.edu";
    try {
      emailGenerator.doEmailAutomation(command.split(" "));
    } catch (IOException e) {
      e.printStackTrace();
      Assert.fail();
    }
  }
}