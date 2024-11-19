package problem1;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents	Fly System, a singleton pattern class to manage all flyers
 *
 * @author Xihao Liu
 */
public class FlySystem {

  private static FlySystem FLYER_SYSTEM;
  private Map<String, FrequentFlyer> flyersMap;

  /**
   * class FlySystem's constructor, initialize flyersMap
   */
  private FlySystem() {
    this.flyersMap = new HashMap<String, FrequentFlyer>();
  }

  /**
   * get the singleton instance FLYER_SYSTEM of the class FlySystem
   *
   * @return FLYER_SYSTEM
   */
  public static FlySystem getFlySystemInstance() {
    if (FlySystem.FLYER_SYSTEM == null) {
      FlySystem.FLYER_SYSTEM = new FlySystem();
    }
    return FlySystem.FLYER_SYSTEM;
  }

  /**
   * get the map stores all flyers
   *
   * @return flyersMap
   */
  public Map<String, FrequentFlyer> getFlyersMap() {
    return flyersMap;
  }

  /**
   * get specified flyer according to ID
   *
   * @param accountId given ID
   * @return the flyer whose ID is accountId
   */
  public FrequentFlyer getFlyer(String accountId) {
    if (isFlyerExists(accountId)) {
      return getFlySystemInstance().getFlyersMap().get(accountId);
    } else {
      throw new IllegalArgumentException(
          "GET FLYER FAILED, account ID: " + accountId + " does not exist!");
    }
  }

  /**
   * add a new flyer to system
   *
   * @param newFlyer the new flyer
   * @throws IllegalStateException throw when new flyer's account ID has already been registered
   */
  public void addFlyer(FrequentFlyer newFlyer) {
    if (isFlyerExists(newFlyer.getAccountId())) {
      throw new IllegalStateException(
          "ADD FLYER FAILED, account ID: " + newFlyer.getAccountId() + " already exists!");
    } else {
      getFlySystemInstance().getFlyersMap().put(newFlyer.getAccountId(), newFlyer);
    }
  }

  /**
   * remove a flyer from system by account ID
   *
   * @param accountId the flyer's account ID
   * @throws IllegalArgumentException throw when flyer's account ID doesn't exist in system
   */
  public void removeFlyer(String accountId) {
    if (isFlyerExists(accountId)) {
      getFlySystemInstance().getFlyersMap().remove(accountId);
    } else {
      throw new IllegalArgumentException(
          "REMOVE FLYER FAILED, account ID: " + accountId + " does not exist!");
    }
  }

  /**
   * check if the flyer exists in system
   *
   * @param accountId the flyer's account ID
   * @return result of flyer's existence, it's true if exists
   */
  public boolean isFlyerExists(String accountId) {
    return getFlySystemInstance().getFlyersMap().containsKey(accountId);
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
    FlySystem flySystem = (FlySystem) o;
    return Objects.equals(flyersMap, flySystem.flyersMap);
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(flyersMap);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "FlySystem{" + "flyersMap=" + flyersMap + '}';
  }
}
