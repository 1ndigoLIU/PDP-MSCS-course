package problem1;

import java.util.Objects;

/**
 * Represents	a	frequent flyer with their account ID, name, email address and miles balance
 *
 * @author Xihao Liu
 */
public class FrequentFlyer {

  /**
   * down limit of deposit's miles amount
   */
  private static final int DEPOSIT_MILES_DOWN = 1000;
  /**
   * up limit of deposit's miles amount
   */
  private static final int DEPOSIT_MILES_UP = 10_000;
  private String accountId;
  private FlyerName name;
  private String email;
  private MilesBalance milesBalance;
  /**
   * legal length of account ID string
   */
  private static final int ID_LENGTH = 12;

  /**
   * @param accountId the account ID
   * @param name      the name
   * @param email     the e-mail address
   * @throws IllegalArgumentException throw exception when the length of accountId doesn't valid
   */
  public FrequentFlyer(String accountId, FlyerName name, String email) {
    if (accountId.length() == ID_LENGTH) {
      this.accountId = accountId;
    } else {
      throw new IllegalArgumentException(
          "Invalid FrequentFlyer.accountId, accountId must be " + ID_LENGTH + " characters long!");
    }
    this.name = name;
    this.email = email;
    this.milesBalance = new MilesBalance();
  }

  /**
   * gets the account ID
   *
   * @return account ID
   */
  public String getAccountId() {
    return this.accountId;
  }

  /**
   * get the flyer's name
   *
   * @return the name
   */
  public FlyerName getName() {
    return this.name;
  }

  /**
   * get the flyer's email
   *
   * @return the email
   */
  public String getEmail() {
    return this.email;
  }


  /**
   * get the miles balance
   *
   * @return miles balance
   */
  public MilesBalance getMilesBalance() {
    return this.milesBalance;
  }

  /**
   * transfer miles to the recipient's flyer account
   *
   * @param deposit transfer detail including deposit amount and recipient information
   */
  public void transferMiles(Deposit deposit) {
    if (!isRecipientValid(deposit)) {
      throw new IllegalStateException("Recipient is not valid!");
    }

    int transferMiles = deposit.getMilesAmount();
    int selfExpiringMiles = this.milesBalance.getMilesExpiringThisYear();
    FrequentFlyer recipientFlyer = FlySystem.getFlySystemInstance()
        .getFlyer(deposit.getRecipientId());

    this.milesBalance.setMilesAvailable(this.milesBalance.getMilesAvailable() - transferMiles);
    if (selfExpiringMiles < transferMiles) {
      this.milesBalance.setMilesExpiringThisYear(0);
    } else {
      this.milesBalance.setMilesExpiringThisYear(selfExpiringMiles - transferMiles);
    }

    recipientFlyer.getMilesBalance()
        .setMilesAvailable(recipientFlyer.getMilesBalance().getMilesAvailable() + transferMiles);
    recipientFlyer.getMilesBalance().setMilesEarnedThisYear(
        recipientFlyer.getMilesBalance().getMilesEarnedThisYear() + transferMiles);
    recipientFlyer.getMilesBalance().setMilesExpiringThisYear(
        recipientFlyer.getMilesBalance().getMilesExpiringThisYear() + transferMiles);
  }

  /**
   * check if recipient information of this transfer is valid. step1: double-check the deposit's
   * amount even checked it in Deposit constructor; step2: check if the recipient accountId is
   * valid; step3: check if the provided recipient accountId matches recipient’s name; step4: check
   * if the source account has sufficient available miles
   *
   * @param deposit transfer detail including deposit amount and recipient information
   * @return if this transfer is valid
   */
  private boolean isRecipientValid(Deposit deposit) {
    if (deposit.getMilesAmount() < DEPOSIT_MILES_DOWN
        || deposit.getMilesAmount() > DEPOSIT_MILES_UP) {
      return false;
    }
    if (!FlySystem.getFlySystemInstance().isFlyerExists(deposit.getRecipientId())) {
      return false;
    }
    if (FlySystem.getFlySystemInstance().getFlyer(deposit.getRecipientId()).getName()
        != deposit.getRecipientName()) {
      return false;
    }
    return this.milesBalance.getMilesAvailable() >= deposit.getMilesAmount();
  }

  /**
   * override equals method the same account ID is treated as equal FrequentFlyer Object
   *
   * @param o the reference object with which to compare
   * @return true if this object is the same as the obj argument; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FrequentFlyer that)) {
      return false;
    }
    return Objects.equals(accountId, that.accountId);
  }

  /**
   * override hashCode method hash code is generated using account ID
   *
   * @return a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(accountId);
  }

  /**
   * Override toString method, returns a string representation of the object
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "FrequentFlyer{" + "accountId='" + accountId + '\'' + ", name=" + name + ", email='"
        + email + '\'' + ", milesBalance=" + milesBalance + '}';
  }
}
