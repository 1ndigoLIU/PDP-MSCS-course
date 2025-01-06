package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

class StudentFileProducerTest {

  BlockingQueue<ArrayList<String>> studentVleBlockingQueue = new LinkedBlockingQueue<>();
  String studentVleCsvPath = "./testFile/studentVle_test.csv";
  StudentFileProducer studentFileProducer = new StudentFileProducer(studentVleBlockingQueue,
      studentVleCsvPath);

  @Test
  void run() throws InterruptedException {
    studentFileProducer.run();
    ArrayList<String> expectedData1 = new ArrayList<>(Arrays.asList("BBB_2014J", "138", "11"));
    ArrayList<String> expectedData2 = new ArrayList<>(Arrays.asList("FFF_2013B", "38", "22"));

    ArrayList<String> data1 = studentVleBlockingQueue.take();
    assertEquals(expectedData1, data1);

    ArrayList<String> data2 = studentVleBlockingQueue.take();
    assertEquals(expectedData2, data2);
  }

  @Test
  void equalsAndHashCodeTest() {
    assertEquals(studentFileProducer, studentFileProducer);
    assertEquals(studentFileProducer,
        new StudentFileProducer(studentVleBlockingQueue, studentVleCsvPath));
    assertNotEquals(studentFileProducer, null);
    assertNotEquals(studentFileProducer, new Object());
    assertNotEquals(studentFileProducer,
        new StudentFileProducer(studentVleBlockingQueue, "new_path"));
    assertNotEquals(studentFileProducer,
        new StudentFileProducer(new LinkedBlockingQueue<>(), studentVleCsvPath));

    assertEquals(studentFileProducer.hashCode(),
        new StudentFileProducer(studentVleBlockingQueue, studentVleCsvPath).hashCode());
    assertNotEquals(studentFileProducer.hashCode(),
        new StudentFileProducer(new LinkedBlockingQueue<>(), studentVleCsvPath).hashCode());
  }

  @Test
  void toStringTest() {
    System.out.println(studentFileProducer.toString());
  }
}