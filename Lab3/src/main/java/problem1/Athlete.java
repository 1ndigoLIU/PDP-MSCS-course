package problem1;

import java.util.Objects;

/**
 * Class Athlete contains information about an athlete, including athlete's name, their height, weight and league.
 */
public class Athlete {
  private Name athletesName;
  private Double height;
  private Double weight;
  private String league;

  /**
   * Constructs a new athlete, based upon all the provided input parameters
   * Return object Athlete
   * @param athletesName - object Name, containing athlete's first, middle and last name
   * @param height - athlete's height, expressed as a Double in cm (e.g., 6'2'' is recorded as 187.96cm)
   * @param weight - athlete's weigh, expressed as a Double in pounds (e.g. 125, 155, 200 pounds)
   * @param league - athelete's league, expressed as String
   */
  public Athlete(Name athletesName, Double height, Double weight, String league) {
    this.athletesName = athletesName;
    this.height = height;
    this.weight = weight;
    this.league = league;
  }

  /**
   * Constructs a new athlete, based upon all the provided input parameters
   * Return object Athlete, with league field set to null
   * @param athletesName - object Name, containing athlete's first, middle and last name
   * @param height - athlete's height, expressed as a Double in cm (e.g., 6'2'' is recorded as 187.96cm)
   * @param weight - athlete's weigh, expressed as a Double in pounds (e.g. 125, 155, 200 pounds)
   */
  public Athlete(Name athletesName, Double height, Double weight) {
    this.athletesName = athletesName;
    this.height = height;
    this.weight = weight;
    this.league = null;
  }

  /**
   * Returns athlete's name as an object Name
   * @return athlete's name
   */
  public Name getAthletesName() {
    return athletesName;
  }

  /**
   * Returns athlete's height as a Double
   * @return athlete's height
   */
  public Double getHeight() {
    return height;
  }

  /**
   * Returns athlete's weight as a Double
   * @return athlete's weight
   */
  public Double getWeight() {
    return weight;
  }

  /**
   * Returns athlete's league as a String
   * @return athlete's league
   */
  public String getLeague() {
    return league;
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
    Athlete athlete = (Athlete) o;
    return Objects.equals(athletesName, athlete.getAthletesName())
        && Objects.equals(height, athlete.getHeight())
        && Objects.equals(weight, athlete.getWeight())
        && Objects.equals(league, athlete.getLeague());
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(athletesName, height, weight, league);
  }

  /**
   * Override toString method, returns a string representation of the object
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "Athlete{" +
        "name='" + athletesName.toString() + '\'' +
        ", height=" + height +
        ", weight=" + weight +
        ", league='" + league + '\'' +
        '}';
  }
}