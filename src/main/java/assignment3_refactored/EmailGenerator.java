package assignment3_refactored;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;

/**
 * Main program to generate email and letter.
 * @author Chang Zhou
 */
public class EmailGenerator {
  private int cnt;
  private String[] headers;
  private String[] lines;
  private String outputDir;
  private String csvFile;
  private String csvContent;
  private String emailTemplate;
  private String letterTemplate;
  private String recipientEmail;
  private String senderInfoFilePath;
  private boolean isEmail;
  private boolean isSend2Remote;
  private EmailPoster emailPosterLocal;
  private EmailPoster emailPosterRemote;
  private static final String OPEN_SQUARE_BRACKET = "\\[\\[";
  private static final String CLOSE_SQUARE_BRACKET = "]]";
  private static final String ARG_SYMBOL = "--";
  private static final String EMAIL = "--email";
  private static final String SEND_2_REMOTE = "--send-to-remote";
  private static final String EMAIL_TEMPLATE = "--email-template";
  private static final String LETTER = "--letter";
  private static final String LETTER_TEMPLATE = "--letter-template";
  private static final String OUTPUT_DIR = "--output-dir";
  private static final String CSV_FILE = "--csv-file";
  private static final String SENDER_INFO_FILE = "--sender-info-file";
  private static final String EMAIL_RECIPIENT = "--email-recipient";
  private static final String USAGES = "Usage:\n"
      + "--email only generate email messages\n"
      + "--email-template <file> accept a filename that holds the"
      + "email template.\n"
      + "Required if --email is used\n"
      + "--letter only generate letters\n"
      + "--letter-template <file> accept a filename that holds"
      + "the email template.\n"
      + "Required if --letter is used\n"
      + "--output-dir <path> accept the name of a folder, all"
      + "output is placed in this folder\n"
      + "--csv-file <path> accept the name of the csv file to"
      + "process\n"
      + "Examples:\n"
      + "--email --email-template email-template.txt --output-dir\n"
      + "emails --csv-file customer.csv"
      + "--letter --letter-template letter-template.txt --output-dir\n"
      + "emails --csv-file customer.csv";

  /**
   * Constructor.
   */
  public EmailGenerator() {
    this.cnt = 0;
  }

  /**
   * calculate the hashcode.
   * @return hashcode
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * check if two objs are equal.
   * @param obj obj
   * @return ifEquals
   */
  @Override
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }

  /**
   * transfer this obj to String.
   * @return string
   */
  @Override
  public String toString() {
    return super.toString();
  }


  /**
   * Generate emails and return as strings.
   * @return emails array.
   * @throws IOException IOException
   */
  public String[] generateEmail() throws IOException {
    String[] newEmails = new String[lines.length];
    final String emailContent = FileOperator.readFile(emailTemplate);
    for (int i = 1; i < lines.length; i++) {
      final String[] fields = lines[i].substring(1, lines[i].length() - 1).split("\",\"");
      String newEmail = emailContent;
      for (int j = 0; j < fields.length; j++) {
        newEmail = newEmail.replaceAll(OPEN_SQUARE_BRACKET + headers[j] +
            CLOSE_SQUARE_BRACKET, fields[j]);
      }
      newEmails[i] = newEmail;
      //FileOperator.writeFile(outputDir + File.separator + "email" + cnt++, newEmail);
    }
    return newEmails;
  }

  /**
   * Generate letters and return as strings.
   * @return letters array.
   * @throws IOException IOException
   */
  public String[] generateLetter() throws IOException {
    String[] newLetters = new String[lines.length];
    final String letterContent = FileOperator.readFile(letterTemplate);
    for (int i = 1; i < lines.length; i++) {
      final String[] fields = lines[i].substring(1, lines[i].length() - 1).split("\",\"");
      String newLetter = letterContent;
      for (int j = 0; j < fields.length; j++) {
        newLetter = newLetter.replaceAll(OPEN_SQUARE_BRACKET + headers[j] +
            CLOSE_SQUARE_BRACKET, fields[j]);
      }
      newLetters[i] = newLetter;
     // FileOperator.writeFile(outputDir + File.separator + "letter" + cnt++, newLetter);
    }
    return newLetters;
  }

  /**
   * print error and help message.
   * @param message message
   */
  public void printHelp(final String message) {
    System.out.println("Error: " + message);
    System.out.println(USAGES);
  }

  /**
   * getCnt.
   * @return cnt
   */
  public int getCnt() {
    return cnt;
  }

  /**
   * set a new cnt.
   * @param cnt cnt
   */
  public void setCnt(final int cnt) {
    this.cnt = cnt;
  }

  /**
   * getHeaders.
   * @return headers
   */
  public String[] getHeaders() {
    if (headers == null) {
      return null;
    }
    return Arrays.copyOf(headers, headers.length);
  }

  /**
   * set new headers.
   * @param headers headers
   */
  public void setHeaders(final String[] headers) {
    if (headers == null) {
      return;
    }
    this.headers = Arrays.copyOf(headers, headers.length);
  }

  /**
   * getLines.
   * @return lines
   */
  public String[] getLines() {
    if (lines == null) {
      return null;
    }
    return Arrays.copyOf(lines, lines.length);
  }

  /**
   * set new lines.
   * @param lines lines
   */
  public void setLines(final String[] lines) {
    if (lines == null) {
      return;
    }
    this.lines = Arrays.copyOf(lines, lines.length);
  }

  /**
   * getOutputDir.
   * @return outputDir
   */
  public String getOutputDir() {
    return outputDir;
  }

  /**
   * set new outputDir.
   * @param outputDir outputDir
   */
  public void setOutputDir(final String outputDir) {
    this.outputDir = outputDir;
  }

  /**
   * getCsvFile.
   * @return csvFile
   */
  public String getCsvFile() {
    return csvFile;
  }

  /**
   * set a new csvFile.
   * @param csvFile csvFile
   */
  public void setCsvFile(final String csvFile) {
    this.csvFile = csvFile;
  }

  /**
   * getCsvContent.
   * @return csvContent
   */
  public String getCsvContent() {
    return csvContent;
  }

  /**
   * set new csvContent.
   * @param csvContent csvContent
   */
  public void setCsvContent(final String csvContent) {
    this.csvContent = csvContent;
  }

  /**
   * getEmailTemplate.
   * @return emailTemplate
   */
  public String getEmailTemplate() {
    return emailTemplate;
  }

  /**
   * set new emailTemplate.
   * @param emailTemplate emailTemplate
   */
  public void setEmailTemplate(final String emailTemplate) {
    this.emailTemplate = emailTemplate;
  }

  /**
   * getLetterTemplate.
   * @return letterTemplate
   */
  public String getLetterTemplate() {
    return letterTemplate;
  }

  /**
   * set new letterTemplate.
   * @param letterTemplate letterTemplate
   */
  public void setLetterTemplate(final String letterTemplate) {
    this.letterTemplate = letterTemplate;
  }

  /**
   * getEmailPosterLocal.
   * @return emailPosterLocal
   */
  public EmailPoster getEmailPosterLocal() {
    return emailPosterLocal;
  }

  /**
   * set new emailPosterLocal obj.
   * @param emailPosterLocal emailPosterLocal
   */
  public void setEmailPosterLocal(final EmailPoster emailPosterLocal) {
    this.emailPosterLocal = emailPosterLocal;
  }

  /**
   * getEmailPosterRemote.
   * @return emailPosterRemote
   */
  public EmailPoster getEmailPosterRemote() {
    return emailPosterRemote;
  }

  /**
   * set a new emailPosterRemote obj.
   * @param emailPosterRemote emailPosterRemote
   */
  public void setEmailPosterRemote(final EmailPoster emailPosterRemote) {
    this.emailPosterRemote = emailPosterRemote;
  }

  /**
   * is generating email? boolean val.
   * @return isEmail
   */
  public boolean isEmail() {
    return isEmail;
  }

  /**
   * set if this class is generating email. boolean val.
   * @param isEmail isEmail
   */
  public void setIsEmail(final boolean isEmail) {
    this.isEmail = isEmail;
  }

  /**
   * isSend2Remote.
   * @return isSend2Remote
   */
  public boolean isSend2Remote() {
    return isSend2Remote;
  }

  /**
   * set a new isSend2Remote val.
   * @param send2Remote send2Remote
   */
  public void setSend2Remote(final boolean send2Remote) {
    isSend2Remote = send2Remote;
  }

  /**
   * handle input args to set static fields.
   * @param args args
   * @throws IOException IOException
   */
  public void handleInputArgs(final String[] args) throws IOException {
    if (args == null) {
      this.printHelp("Must give args.");
      throw new EmailAutomationException();
    }

    final Map<String, Integer> argsCnt = new HashMap<>();
    //in case get a null afterwards.
    argsCnt.put(SEND_2_REMOTE, 0);
    argsCnt.put(EMAIL, 0);
    argsCnt.put(EMAIL_TEMPLATE, 0);
    argsCnt.put(LETTER, 0);
    argsCnt.put(LETTER_TEMPLATE, 0);
    argsCnt.put(OUTPUT_DIR, 0);
    argsCnt.put(CSV_FILE, 0);
    argsCnt.put(SENDER_INFO_FILE, 0);
    argsCnt.put(EMAIL_RECIPIENT, 0);

    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case SEND_2_REMOTE:
          isSend2Remote = true;
          argsCnt.put(SEND_2_REMOTE, argsCnt.getOrDefault(SEND_2_REMOTE, 0) + 1);
          break;
        case EMAIL:
          isEmail = true;
          argsCnt.put(EMAIL, argsCnt.getOrDefault(EMAIL, 0) + 1);
          break;
        case EMAIL_TEMPLATE:
          if (i == args.length - 1 || args[i + 1].contains(ARG_SYMBOL)) {
            this.printHelp(EMAIL_TEMPLATE + " provided but no filename was given.");
            throw new EmailAutomationException();
          }
          emailTemplate = args[++i];
          argsCnt.put(EMAIL_TEMPLATE, argsCnt.getOrDefault(EMAIL_TEMPLATE, 0) + 1);
          break;
        case LETTER:
          argsCnt.put(LETTER, argsCnt.getOrDefault(LETTER, 0) + 1);
          break;
        case LETTER_TEMPLATE:
          if (i == args.length - 1 || args[i + 1].contains(ARG_SYMBOL)) {
            this.printHelp(LETTER_TEMPLATE + " provided but no filename was given.");
            throw new EmailAutomationException();
          }
          letterTemplate = args[++i];
          argsCnt.put(LETTER_TEMPLATE, argsCnt.getOrDefault(LETTER_TEMPLATE, 0) + 1);
          break;
        case OUTPUT_DIR:
          if (i == args.length - 1 || args[i + 1].contains(ARG_SYMBOL)) {
            this.printHelp(OUTPUT_DIR + " provided but no path was given.");
            throw new EmailAutomationException();
          }
          outputDir = args[++i];
          argsCnt.put(OUTPUT_DIR, argsCnt.getOrDefault(OUTPUT_DIR, 0) + 1);
          break;
        case CSV_FILE:
          if (i == args.length - 1 || args[i + 1].contains(ARG_SYMBOL)) {
            this.printHelp(CSV_FILE + " provided but no path was given.");
            throw new EmailAutomationException();
          }
          csvFile = args[++i];
          argsCnt.put(CSV_FILE, argsCnt.getOrDefault(CSV_FILE, 0) + 1);
          break;
        case SENDER_INFO_FILE:
          if (i == args.length - 1 || args[i + 1].contains(ARG_SYMBOL)) {
            this.printHelp(SENDER_INFO_FILE + " provided but no path was given.");
            throw new EmailAutomationException();
          }
          senderInfoFilePath = args[++i];
          argsCnt.put(SENDER_INFO_FILE, argsCnt.getOrDefault(SENDER_INFO_FILE, 0) + 1);
          break;
        case EMAIL_RECIPIENT:
          if (i == args.length - 1 || args[i + 1].contains(ARG_SYMBOL)) {
            this.printHelp(EMAIL_RECIPIENT + " provided but no recipient address was given.");
            throw new EmailAutomationException();
          }
          recipientEmail = args[++i];
          argsCnt.put(EMAIL_RECIPIENT, argsCnt.getOrDefault(EMAIL_RECIPIENT, 0) + 1);
          break;
        default:
          this.printHelp("Wrong arg type.");
          throw new EmailAutomationException();
      }
    }

    for (final int cnt: argsCnt.values()) {
      if (cnt > 1) {
        this.printHelp("No arg can be specified more than once.");
        throw new EmailAutomationException();
      }
    }

    if (!((argsCnt.get(EMAIL) == 1 && argsCnt.get(EMAIL_TEMPLATE) == 1)
        || (argsCnt.get(LETTER) == 1 && argsCnt.get(LETTER_TEMPLATE) == 1))) {
      this.printHelp(EMAIL + " and " + EMAIL_TEMPLATE + " should be provided together. "
          + LETTER + " and " + LETTER_TEMPLATE + " should be provided together too.");
      throw new EmailAutomationException();
    }
    if (!((argsCnt.get(SENDER_INFO_FILE) == 0 && argsCnt.get(EMAIL_RECIPIENT) == 0 &&
        argsCnt.get(SEND_2_REMOTE) == 0) || ((argsCnt.get(SENDER_INFO_FILE) == 1 &&
        argsCnt.get(EMAIL_RECIPIENT) == 1 && argsCnt.get(SEND_2_REMOTE) == 1)))) {
      this.printHelp(SEND_2_REMOTE + ", " + SENDER_INFO_FILE + " and " + EMAIL_RECIPIENT +
          " should be provided together. Otherwise none of them should be provided.");
      throw new EmailAutomationException();
    }
    if (!((argsCnt.get(SEND_2_REMOTE) == 0 && argsCnt.get(OUTPUT_DIR) == 1)
        || ((argsCnt.get(SEND_2_REMOTE) == 1) && argsCnt.get(OUTPUT_DIR) == 0))) {
      this.printHelp("When send emails to local fs, " + OUTPUT_DIR + " must be specified."
          + " Otherwise, " + OUTPUT_DIR + " should not be specified.");
      throw new EmailAutomationException();
    }
    if (argsCnt.get(CSV_FILE) == 0) {
      this.printHelp(CSV_FILE + " must be specified.");
      throw new EmailAutomationException();
    }

    csvContent = FileOperator.readFile(csvFile);
    lines = csvContent.split("\n");
    headers = lines[0].substring(1, lines[0].length() - 1).split("\",\"");
  }

  /**
   * The main logic of this program, called by main method.
   * @param args args from main.
   * @throws IOException IOException
   */
  public void doEmailAutomation(final String[] args) throws IOException {
    handleInputArgs(args);

    if (isSend2Remote) {
      setEmailPosterRemote(new EmailPosterRemoteImpl(senderInfoFilePath));
    } else {
      setEmailPosterLocal(new EmailPosterLocalImpl());
    }
    String[] newEmails = null;
    String[] newLetters = null;
    if (isEmail()) {
      newEmails = generateEmail();
    } else {
      newLetters = generateLetter();
    }
    //send emails
    if (isEmail() && newEmails != null) {
      //send to a real email
      if (isSend2Remote) {
        try {
          getEmailPosterRemote().sendEmail(recipientEmail, newEmails[1]);
        } catch (MessagingException e) {
          e.printStackTrace();
        }
      } else {
        //send to local fs
        for (int i = 1; i < newEmails.length; i++) {
          try {
            getEmailPosterLocal().sendEmail(getOutputDir() + File.
                separator + "email" + getCnt(), newEmails[i]);
            setCnt(getCnt() + 1);
          } catch (MessagingException e) {
            e.printStackTrace();
          }
        }
      }
    }
    //send letters
    if (!isEmail() && newLetters != null) {
      //send to a real email
      if (isSend2Remote) {
        try {
          getEmailPosterRemote().sendEmail(recipientEmail, newLetters[1]);
        } catch (MessagingException e) {
          e.printStackTrace();
        }
      } else {
        //send to local fs
        for (int i = 1; i < newLetters.length; i++) {
          try {
            getEmailPosterLocal().sendEmail(getOutputDir() + File.
                separator + "letter" + getCnt(), newLetters[i]);
            setCnt(getCnt() + 1);
          } catch (MessagingException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  /**
   * main program.
   * @param args args
   * @throws IOException IOException
   */
  public static void main(final String[] args) throws IOException {
    final EmailGenerator emailGenerator = new EmailGenerator();
    try {
      emailGenerator.doEmailAutomation(args);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}