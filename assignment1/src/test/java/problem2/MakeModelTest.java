package problem2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MakeModelTest {
  private String make;
  private String model;
  private MakeModel makeModel;

  @BeforeEach
  void setUp() {
    make = "Ford";
    model = "Ford Mustang";
    makeModel = new MakeModel(make, model);
  }

  @Test
  void getMake() {
    assertEquals(make, makeModel.getMake());
  }

  @Test
  void getModel() {
    assertEquals(model, makeModel.getModel());
  }

  @Test
  void testEquals() {
    assertTrue(makeModel.equals(makeModel));
    Object obj1 = null;
    assertFalse(makeModel.equals(obj1));
    Object obj2 = new Object();
    assertFalse(makeModel.equals(obj2));
    MakeModel sameMakeModel = new MakeModel(make, model);
    assertTrue(makeModel.equals(sameMakeModel));
    MakeModel diffMakeModel1 = new MakeModel("diff" + make, model);
    assertFalse(makeModel.equals(diffMakeModel1));
    MakeModel diffMakeModel2 = new MakeModel(make, "diff" + model);
    assertFalse(makeModel.equals(diffMakeModel2));
  }

  @Test
  void testHashCode() {
    MakeModel sameMakeModel = new MakeModel(make, model);
    assertEquals(makeModel.hashCode(), sameMakeModel.hashCode());
  }
}