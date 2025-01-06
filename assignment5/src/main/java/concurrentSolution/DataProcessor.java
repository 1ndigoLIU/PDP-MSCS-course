package concurrentSolution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Processes student VLE data. Counts clicks, generates output files, and identifies high-activity
 * days.
 *
 * @author Xihao Liu
 */
public class DataProcessor implements Runnable {

  /**
   * A blocking queue holding student VLE data to be processed.
   */
  private final BlockingQueue<ArrayList<String>> studentVleBlockingQueue;
  /**
   * A concurrent map storing aggregated course click data. Keys are course identifiers, and values
   * are maps of dates to click counts.
   */
  private final ConcurrentHashMap<String, ConcurrentHashMap<Integer, AtomicInteger>> coursesMap;
  /**
   * The output path for generated files.
   */
  private final String outputPath;
  /**
   * The threshold for identifying high-activity days. Can be null if not specified.
   */
  private Integer threshold = null;
  /**
   * A CyclicBarrier ensuring all threads synchronize before outputting high-activity data.
   */
  private static final CyclicBarrier outputHighActivityBarrier = new CyclicBarrier(
      Constants.CONSUMER_THREAD_NUM);
  /**
   * An atomic flag indicating whether high-activity output has been completed.
   */
  private static final AtomicBoolean highActivityFinished = new AtomicBoolean(false);

  /**
   * Constructs a DataProcessor without a high-activity threshold.
   *
   * @param studentVleBlockingQueue The queue holding student VLE data to be processed.
   * @param coursesMap              A map to store aggregated course click data.
   * @param outputPath              The path to the output directory.
   */
  public DataProcessor(BlockingQueue<ArrayList<String>> studentVleBlockingQueue,
      ConcurrentHashMap<String, ConcurrentHashMap<Integer, AtomicInteger>> coursesMap,
      String outputPath) {
    this.studentVleBlockingQueue = studentVleBlockingQueue;
    this.coursesMap = coursesMap;
    this.outputPath = outputPath;
  }

  /**
   * Constructs a DataProcessor with a high-activity threshold.
   *
   * @param studentVleBlockingQueue The queue holding student VLE data to be processed.
   * @param coursesMap              A map to store aggregated course click data.
   * @param outputPath              The path to the output directory.
   * @param threshold               The threshold for identifying high-activity days.
   */
  public DataProcessor(BlockingQueue<ArrayList<String>> studentVleBlockingQueue,
      ConcurrentHashMap<String, ConcurrentHashMap<Integer, AtomicInteger>> coursesMap,
      String outputPath, Integer threshold) {
    this.studentVleBlockingQueue = studentVleBlockingQueue;
    this.coursesMap = coursesMap;
    this.outputPath = outputPath;
    this.threshold = threshold;
  }

  /**
   * Executes the processing logic, including counting clicks, generating output, and identifying
   * high-activity days if a threshold is specified.
   */
  @Override
  public void run() {
    countClickNumber();
    outputData();

    try {
      outputHighActivityBarrier.await();
    } catch (InterruptedException | BrokenBarrierException e) {
      e.printStackTrace();
    }

    if (threshold != null && highActivityFinished.compareAndSet(false, true)) {
      HighActivityFilter.writeToFile(outputPath + Constants.HIGH_ACTIVITY_FILE_PREFIX + threshold
          + Constants.CSV_FILE_EXTENSION);
    }
  }

  /**
   * Counts click numbers by processing data from the queue and aggregating them in the course map.
   */
  private void countClickNumber() {
    try {
      while (true) {
        ArrayList<String> data = studentVleBlockingQueue.take();
        if (Constants.POISON_PILL.equals(data)) {
          break;
        }
        String course = data.get(Constants.ZERO);
        int date = Integer.parseInt(data.get(Constants.ONE));
        int sumClick = Integer.parseInt(data.get(Constants.TWO));

        ConcurrentHashMap<Integer, AtomicInteger> courseData = this.coursesMap.get(course);
        if (courseData.putIfAbsent(date, new AtomicInteger(sumClick)) != null) {
          courseData.get(date).addAndGet(sumClick);
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Outputs aggregated course data to CSV files and identifies high-activity days.
   */
  private void outputData() {
    for (String key : coursesMap.keySet()) {
      ConcurrentHashMap<Integer, AtomicInteger> courseData = coursesMap.remove(key);
      if (courseData != null && !courseData.isEmpty()) {
        try (BufferedWriter writer = new BufferedWriter(
            new FileWriter(outputPath + key + Constants.CSV_FILE_EXTENSION))) {
          writer.write(Constants.DOUBLE_QUOTA + Constants.DATE_COLUMN + Constants.DOUBLE_QUOTA
              + Constants.CSV_DELIMITER + Constants.DOUBLE_QUOTA + Constants.TOTAL_CLICKS_COLUMN
              + Constants.DOUBLE_QUOTA + Constants.NEW_LINE);
          courseData.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(data -> {
            try {
              String outputLine = Constants.DOUBLE_QUOTA + data.getKey() + Constants.DOUBLE_QUOTA
                  + Constants.CSV_DELIMITER + Constants.DOUBLE_QUOTA + data.getValue()
                  + Constants.DOUBLE_QUOTA + Constants.NEW_LINE;
              writer.write(outputLine);
              if (threshold != null && data.getValue().get() > threshold) {
                HighActivityFilter.addActivity(key, data.getKey(), data.getValue().get());
              }
            } catch (IOException e) {
              e.printStackTrace();
            }
          });
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
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
    DataProcessor that = (DataProcessor) o;
    return Objects.equals(outputPath, that.outputPath) && Objects.equals(threshold, that.threshold);
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(outputPath, threshold);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "DataProcessor{" + "outputPath='" + outputPath + '\'' + ", threshold=" + threshold + '}';
  }
}