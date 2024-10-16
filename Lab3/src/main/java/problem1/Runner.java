package problem1;

import java.util.Objects;

/**
 * Represent runner containing runner's
 * the best 5K time, the best half-marathon time, and favorite running event
 * @author Xihao Liu
 */
public class Runner extends Athlete {
  private Double best5kTime;
  private Double bestHalfMaraTime;
  private String favRunEvent;

  /**
   * Constructs a new runner, based upon all the provided input parameters
   * Return Runner object
   * @param name - object Name, containing runner's first, middle and last name
   * @param height - runner's height, expressed as a Double
   * @param weight - runner's weigh, expressed as a Double
   * @param league - runner's league, expressed as String
   * @param best5kTime - runner's best 5K time, expressed as Double
   * @param bestHalfMaraTime - runner's best half-marathon time, expressed as Double
   * @param favRunEvent - runner's favorite running event, expressed as String
   */
  public Runner(Name name, Double height, Double weight, String league,
      Double best5kTime, Double bestHalfMaraTime, String favRunEvent) {
    super(name, height, weight, league);
    this.best5kTime = best5kTime;
    this.bestHalfMaraTime = bestHalfMaraTime;
    this.favRunEvent = favRunEvent;
  }

  /**
   * Constructs a new runner, based upon all the provided input parameters
   * @param name - object Name, containing runner's first, middle and last name
   * @param height - runner's height, expressed as a Double
   * @param weight - runner's weigh, expressed as a Double
   * @param best5kTime - runner's best 5K time, expressed as Double
   * @param bestHalfMaraTime - runner's best half-marathon time, expressed as Double
   * @param favRunEvent - runner's favorite running event, expressed as String
   */
  public Runner(Name name, Double height, Double weight,
      Double best5kTime, Double bestHalfMaraTime, String favRunEvent) {
    super(name, height, weight);
    this.best5kTime = best5kTime;
    this.bestHalfMaraTime = bestHalfMaraTime;
    this.favRunEvent = favRunEvent;
  }

  /**
   * Getter method to get the best 5K time
   * @return the best 5K time
   */
  public Double getBest5kTime() {
    return best5kTime;
  }

  /**
   * Getter method to get the best half-marathon time
   * @return the best half-marathon time
   */
  public Double getBestHalfMaraTime() {
    return bestHalfMaraTime;
  }

  /**
   * Getter method to get the favorite running event
   * @return the favorite running event
   */
  public String getFavRunEvent() {
    return favRunEvent;
  }

  /**
   * Override equals method, indicates whether some other object is "equal to" this one
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
    if (!super.equals(o)) {
      return false;
    }
    Runner runner = (Runner) o;
    return Objects.equals(best5kTime, runner.getBest5kTime())
        && Objects.equals(bestHalfMaraTime, runner.getBestHalfMaraTime())
        && Objects.equals(favRunEvent, runner.getFavRunEvent());
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), best5kTime, bestHalfMaraTime, favRunEvent);
  }

  /**
   * Override toString method, returns a string representation of the object
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "Runner{" +
        "name='" + getAthletesName().toString() + '\'' +
        ", height=" + getHeight() +
        ", weight=" + getWeight() +
        ", league='" + getLeague() + '\'' +
        ", best5kTime=" + best5kTime +
        ", bestHalfMaraTime=" + bestHalfMaraTime +
        ", favRunEvent='" + favRunEvent + '\'' +
        '}';
  }
}
