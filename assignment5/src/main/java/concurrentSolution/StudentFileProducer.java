package concurrentSolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * Reads student VLE data from a CSV file and produces records into a blocking queue.
 *
 * @author Xihao Liu
 */
public class StudentFileProducer implements Runnable {

  /**
   * A blocking queue to store student VLE records for processing by consumers.
   */
  private final BlockingQueue<ArrayList<String>> studentVleBlockingQueue;
  /**
   * The path to the student VLE CSV file.
   */
  private final String studentVleCsvPath;

  /**
   * Constructs a StudentFileProducer.
   *
   * @param studentVleBlockingQueue The blocking queue to store parsed records.
   * @param studentVleCsvPath       The path to the student VLE CSV file.
   */
  public StudentFileProducer(BlockingQueue<ArrayList<String>> studentVleBlockingQueue,
      String studentVleCsvPath) {
    this.studentVleBlockingQueue = studentVleBlockingQueue;
    this.studentVleCsvPath = studentVleCsvPath;
  }

  /**
   * Reads the CSV file, parses each record, and adds it to the blocking queue. Inserts poison pills
   * to signal the end of data production.
   */
  @Override
  public void run() {
    try (BufferedReader reader = new BufferedReader(new FileReader(studentVleCsvPath))) {
      String titleLine = reader.readLine();
      String[] titles = cleanData(titleLine.split(Constants.CSV_DELIMITER));
      int codeModuleIndex = Arrays.asList(titles).indexOf(Constants.CODE_MODULE_COLUMN);
      int codePresentationIndex = Arrays.asList(titles).indexOf(Constants.CODE_PRESENTATION_COLUMN);
      int dateIndex = Arrays.asList(titles).indexOf(Constants.DATE_COLUMN);
      int sumClickIndex = Arrays.asList(titles).indexOf(Constants.SUM_CLICK_COLUMN);
      String recordLine;
      while ((recordLine = reader.readLine()) != null) {
        String[] records = cleanData(recordLine.split(Constants.CSV_DELIMITER));
        String course =
            records[codeModuleIndex] + Constants.UNDERSCORE + records[codePresentationIndex];
        String date = records[dateIndex];
        String sumClick = records[sumClickIndex];
        ArrayList<String> data = new ArrayList<>(List.of(course, date, sumClick));
        studentVleBlockingQueue.put(data);
      }
      for (int i = 0; i < Constants.CONSUMER_THREAD_NUM; i++) {
        studentVleBlockingQueue.put(Constants.POISON_PILL);
      }
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Cleans the provided array of strings by removing double quotes.
   *
   * @param data An array of strings to be cleaned.
   * @return The cleaned array of strings.
   */
  private String[] cleanData(String[] data) {
    for (int i = 0; i < data.length; i++) {
      data[i] = data[i].replace(Constants.DOUBLE_QUOTA, Constants.EMPTY_STRING);
    }
    return data;
  }

  /**
   * Override equals method, indicates whether some other object is "equal to" this one
   *
   * @param o the reference object with which to compare
   * @return true if this object is the same as the obj argument; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentFileProducer that = (StudentFileProducer) o;
    return Objects.equals(studentVleBlockingQueue, that.studentVleBlockingQueue) && Objects.equals(
        studentVleCsvPath, that.studentVleCsvPath);
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(studentVleBlockingQueue, studentVleCsvPath);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "StudentFileProducer{" + "studentVleCsvPath='" + studentVleCsvPath + '\'' + '}';
  }
}

