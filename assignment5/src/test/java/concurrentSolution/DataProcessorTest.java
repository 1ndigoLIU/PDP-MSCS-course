package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataProcessorTest {

  BlockingQueue<ArrayList<String>> studentVleBlockingQueue = new LinkedBlockingQueue<>();
  ConcurrentHashMap<String, ConcurrentHashMap<Integer, AtomicInteger>> coursesMap = new ConcurrentHashMap<>();
  private static final String OUTPUT_PATH = "./testOutput/";
  Integer threshold = 2;

  @BeforeEach
  void setUp() throws Exception {
    FileUtils.checkAndCreateOutputPath(OUTPUT_PATH);
    ArrayList<String> data1 = new ArrayList<>(Arrays.asList("BBB_2014J", "138", "11"));
    ArrayList<String> data2 = new ArrayList<>(Arrays.asList("FFF_2013B", "38", "22"));
    ArrayList<String> data3 = new ArrayList<>(Arrays.asList("FFF_2013B", "37", "13"));
    studentVleBlockingQueue.put(data1);
    studentVleBlockingQueue.put(data2);
    studentVleBlockingQueue.put(data3);
    studentVleBlockingQueue.put(Constants.POISON_PILL);
    coursesMap.put("BBB_2014J", new ConcurrentHashMap<>());
    coursesMap.put("FFF_2013B", new ConcurrentHashMap<>());
  }

  @Test
  void runTestWithoutThreshold() throws InterruptedException {
    int threadCount = Constants.CONSUMER_THREAD_NUM;
    ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

    List<DataProcessor> processors = new ArrayList<>();
    for (int i = 0; i < threadCount; i++) {
      DataProcessor processor = new DataProcessor(studentVleBlockingQueue, coursesMap, OUTPUT_PATH);
      processors.add(processor);
      executorService.submit(processor);
    }

    executorService.shutdown();
    executorService.awaitTermination(10, TimeUnit.SECONDS);

    File fileBBB = new File(OUTPUT_PATH + "BBB_2014J" + Constants.CSV_FILE_EXTENSION);
    File fileFFF = new File(OUTPUT_PATH + "FFF_2013B" + Constants.CSV_FILE_EXTENSION);
    assertTrue(fileBBB.exists());
    assertTrue(fileFFF.exists());
  }

  @Test
  void runTestWithThreshold() throws InterruptedException {
    int threadCount = Constants.CONSUMER_THREAD_NUM;
    ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

    List<DataProcessor> processors = new ArrayList<>();
    for (int i = 0; i < threadCount; i++) {
      DataProcessor processor = new DataProcessor(studentVleBlockingQueue, coursesMap, OUTPUT_PATH,
          threshold);
      processors.add(processor);
      executorService.submit(processor);
    }

    executorService.shutdown();
    executorService.awaitTermination(10, TimeUnit.SECONDS);

    File fileBBB = new File(OUTPUT_PATH + "BBB_2014J" + Constants.CSV_FILE_EXTENSION);
    File fileFFF = new File(OUTPUT_PATH + "FFF_2013B" + Constants.CSV_FILE_EXTENSION);
    assertTrue(fileBBB.exists());
    assertTrue(fileFFF.exists());
  }

  @Test
  void equalsAndHashCodeTest() {
    DataProcessor processor = new DataProcessor(studentVleBlockingQueue, coursesMap, OUTPUT_PATH);
    assertEquals(processor, processor);
    assertEquals(processor, new DataProcessor(studentVleBlockingQueue, coursesMap, OUTPUT_PATH));
    assertNotEquals(processor, new DataProcessor(studentVleBlockingQueue, coursesMap, "new_path"));
    assertNotEquals(processor, new Object());
    assertNotEquals(processor, null);

    assertEquals(processor.hashCode(),
        new DataProcessor(studentVleBlockingQueue, coursesMap, OUTPUT_PATH).hashCode());
    assertNotEquals(processor.hashCode(),
        new DataProcessor(studentVleBlockingQueue, coursesMap, "new_path").hashCode());
  }

  @Test
  void toStringTest() {
    System.out.println(
        new DataProcessor(studentVleBlockingQueue, coursesMap, OUTPUT_PATH).toString());
  }
}