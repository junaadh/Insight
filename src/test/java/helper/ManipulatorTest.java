package helper;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import forms.Person;

public class ManipulatorTest {

  @Test
  public void TestA() {
    Person p = new Person("A989898", "testing444", "testing testing", "123456789", 22, "Male", "mail@mail.com", 7787787,
        "MV",
        false, false);
    assertNotNull(p.getHeaders());
  }
}
