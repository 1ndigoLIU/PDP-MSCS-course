package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactInfoTest {
  private Name name;
  private String address;
  private String phoneNumber;
  private String email;
  private ContactInfo contactInfo;

  @BeforeEach
  void setUp() {
    name = new Name("Xihao", "Liu");
    address = "225 Terry Ave N, Seattle, WA 98109";
    phoneNumber = "6173732462";
    email = "liu.xiha@northeastern.edu";
    contactInfo = new ContactInfo(name, address, phoneNumber, email);
  }

  @Test
  void getName() {
    assertEquals(name, contactInfo.getName());
  }

  @Test
  void getAddress() {
    assertEquals(address, contactInfo.getAddress());
  }

  @Test
  void getPhoneNumber() {
    assertEquals(phoneNumber, contactInfo.getPhoneNumber());
  }

  @Test
  void getEmail() {
    assertEquals(email, contactInfo.getEmail());
  }

  @Test
  void testEquals() {
    assertTrue(contactInfo.equals(contactInfo));
    Object obj1 = null;
    assertFalse(contactInfo.equals(obj1));
    Object obj2 = new Object();
    assertFalse(contactInfo.equals(obj2));
    ContactInfo sameContactInfo = new ContactInfo(name, address, phoneNumber, email);
    assertTrue(contactInfo.equals(sameContactInfo));
    ContactInfo diffContactInfo1 = new ContactInfo(new Name("diffFirstName", "diffLastName"), address, phoneNumber, email);
    assertFalse(contactInfo.equals(diffContactInfo1));
    ContactInfo diffContactInfo2 = new ContactInfo(name, "diff" + address, phoneNumber, email);
    assertFalse(contactInfo.equals(diffContactInfo2));
    ContactInfo diffContactInfo3 = new ContactInfo(name, address, "+1" + phoneNumber, email);
    assertFalse(contactInfo.equals(diffContactInfo3));
    ContactInfo diffContactInfo4 = new ContactInfo(name, address, phoneNumber, "diff" + email);
    assertFalse(contactInfo.equals(diffContactInfo4));
  }

  @Test
  void testHashCode() {
    ContactInfo sameContactInfo = new ContactInfo(name, address, phoneNumber, email);
    assertEquals(contactInfo.hashCode(), sameContactInfo.hashCode());
  }
}