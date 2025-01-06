package concurrentSolution;

import java.util.ArrayList;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Main class to process OULAD (Open University Learning Analytics Dataset) data. Orchestrates the
 * producer-consumer pattern and manages concurrent processing of student VLE data.
 *
 * @author Xihao Liu
 */
public class ConcurrentOuladProcessor {

  /**
   * The main entry point of the application. Validates command-line arguments, sets up the
   * processing pipeline, and starts the producer and consumer threads.
   *
   * @param args Command-line arguments:
   *             1. Path to the courses CSV file.
   *             2. Path to the student VLE CSV file.
   *             3. Output directory path.
   *             4. (Optional) High-activity threshold.
   */
  public static void main(String[] args) {
    if (args.length != Constants.THREE && args.length != Constants.FOUR) {
      System.err.println(
          "Error: Incorrect number of arguments. Expected 3 or 4 arguments, but got " + args.length
              + ".");
      return;
    }

    String coursesCsvPath = args[Constants.ZERO];
    String studentVleCsvPath = args[Constants.ONE];
    String outputPath = args[Constants.TWO];
    String threshold = null;
    if (args.length == Constants.FOUR) {
      threshold = args[Constants.THREE];
    }

    if (!FileUtils.isCSVFileValid(coursesCsvPath)) {
      System.err.println(
          "Error: The file at path '" + coursesCsvPath + "' is not a valid CSV file.");
      return;
    }
    if (!FileUtils.isCSVFileValid(studentVleCsvPath)) {
      System.err.println(
          "Error: The file at path '" + studentVleCsvPath + "' is not a valid CSV file.");
      return;
    }
    FileUtils.checkAndCreateOutputPath(outputPath);

    CourseCSVReader courseCSVReader = new CourseCSVReader();
    ConcurrentHashMap<String, ConcurrentHashMap<Integer, AtomicInteger>> coursesMap = new ConcurrentHashMap<>();
    courseCSVReader.readCourses(coursesCsvPath, coursesMap);
    BlockingQueue<ArrayList<String>> studentVleBlockingQueue = new ArrayBlockingQueue<>(
        Constants.STUDENT_VLE_QUEUE_CAPACITY);
    ExecutorService producerPool = Executors.newFixedThreadPool(Constants.PRODUCER_THREAD_NUM);
    ExecutorService consumerPool = Executors.newFixedThreadPool(Constants.CONSUMER_THREAD_NUM);
    for (int i = 0; i < Constants.PRODUCER_THREAD_NUM; i++) {
      producerPool.submit(new StudentFileProducer(studentVleBlockingQueue, studentVleCsvPath));
    }
    for (int i = 0; i < Constants.CONSUMER_THREAD_NUM; i++) {
      if (threshold == null) {
        consumerPool.submit(new DataProcessor(studentVleBlockingQueue, coursesMap, outputPath));
      } else {
        consumerPool.submit(new DataProcessor(studentVleBlockingQueue, coursesMap, outputPath,
            Integer.parseInt(threshold)));
      }
    }
    producerPool.shutdown();
    consumerPool.shutdown();
  }
}


