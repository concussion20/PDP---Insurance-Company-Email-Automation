package assignment3_refactored;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * File Operator.
 * @author Chang Zhou
 */
public class FileOperator {

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
   * Read file from local.
   * @param filePath filePath
   * @return fileContent
   * @throws IOException IOException
   */
  public static String readFile(final String filePath) throws IOException {
    String fileStr = "";
    final FileReader fr = new FileReader(filePath);
    final BufferedReader bf = new BufferedReader(fr);

    String line;
    while ((line = bf.readLine()) != null) {
      fileStr += line + "\n";
    }
    bf.close();
    fr.close();
    return fileStr;
  }

  /**
   * Write file to local.
   * @param filePath filePath
   * @param  str str
   * @throws IOException IOException
   */
  public static void writeFile(final String filePath, final String str) throws IOException{
    final File file = new File(filePath);

    //if file doesnt exists, then create it
    if (!file.exists()) {
      if (!file.createNewFile()) {
        throw new IOException("ERROR: Can't create file!");
      }
    }

    final FileWriter fileWritter = new FileWriter(file.getPath());
    final BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    bufferWritter.write(str);
    bufferWritter.close();
    fileWritter.close();
  }
}
