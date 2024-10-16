package problem1;

import java.util.Objects;

/**
 * Represent baseball player contains information including
 * player's team, average batting, and season home runs
 * @author Xihao Liu
 */
public class BaseballPlayer extends Athlete {
  private String team;
  private Double avgBatting;
  private Integer seasonHomeRuns;

  /**
   * Constructs a new baseball player, based upon all the provided input parameters
   * Return object BaseballPlayer
   * @param name - object Name, containing player's first, middle and last name
   * @param height - player's height, expressed as a Double
   * @param weight - player's weigh, expressed as a Double
   * @param league - player's league, expressed as String
   * @param team - player's team, expressed as a String
   * @param avgBatting - baseball player's average batting, expressed as a Double
   * @param seasonHomeRuns - baseball player's season home runs, expressed as an Integer
   */
  public BaseballPlayer(Name name, Double height, Double weight, String league,
      String team, Double avgBatting, Integer seasonHomeRuns) {
    super(name, height, weight, league);
    this.team = team;
    this.avgBatting = avgBatting;
    this.seasonHomeRuns = seasonHomeRuns;
  }

  /**
   * Constructs a new baseball player, based upon all the provided input parameters
   * Return object BaseballPlayer
   * @param name - object Name, containing player's first, middle and last name
   * @param height - player's height, expressed as a Double
   * @param weight - player's weigh, expressed as a Double
   * @param team - player's team, expressed as a String
   * @param avgBatting - baseball player's average batting, expressed as a Double
   * @param seasonHomeRuns - baseball player's season home runs, expressed as an Integer
   */
  public BaseballPlayer(Name name, Double height, Double weight,
      String team, Double avgBatting, Integer seasonHomeRuns) {
    super(name, height, weight);
    this.team = team;
    this.avgBatting = avgBatting;
    this.seasonHomeRuns = seasonHomeRuns;
  }

  /**
   * Getter method to get the team
   * @return the team
   */
  public String getTeam() {
    return team;
  }

  /**
   * Getter method to get the average batting
   * @return the average batting
   */
  public Double getAvgBatting() {
    return avgBatting;
  }

  /**
   * Getter method to get the season home runs
   * @return the season home runs
   */
  public Integer getSeasonHomeRuns() {
    return seasonHomeRuns;
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
    BaseballPlayer baseballPlayer = (BaseballPlayer) o;
    return Objects.equals(team, baseballPlayer.getTeam())
        && Objects.equals(avgBatting, baseballPlayer.getAvgBatting())
        && Objects.equals(seasonHomeRuns, baseballPlayer.getSeasonHomeRuns());
  }

  /**
   * Override hashCode method, returns a hash code value for the object
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), team, avgBatting, seasonHomeRuns);
  }

  /**
   * Override toString method, returns a string representation of the object
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "BaseballPlayer{" +
        "name='" + getAthletesName().toString() + '\'' +
        ", height=" + getHeight() +
        ", weight=" + getWeight() +
        ", league='" + getLeague() + '\'' +
        ", team='" + team + '\'' +
        ", avgBatting=" + avgBatting +
        ", seasonHomeRuns=" + seasonHomeRuns +
        '}';
  }
}
