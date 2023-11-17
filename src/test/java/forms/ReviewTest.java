package forms;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import db.BinHandler;
import helper.Misc.prefix;

public class ReviewTest {

  @Test
  public void TestA() {
    BinHandler handler = new BinHandler();
    Review r = new Review("RX99999", "Hello", "21/12/2023", "UX00001");
    String test = handler.addReviews(r);
    assertTrue(test != null);
    boolean bool = handler.deleteReviews(r);
    assertTrue(bool);
  }

  @Test
  public void TestB() {
    BinHandler handler = new BinHandler();
    Review r = new Review("RX99999", "Hello", "21/12/2023", "UX00001");
    String test = handler.addReviews(r);
    assertTrue(test != null);
    Review rb = handler.searchReviews(prefix.REVID, r.getRevId());
    assertEquals(rb.buildInfo(), r.buildInfo());
    boolean bool = handler.deleteReviews(r);
    assertTrue(bool);
  }

  @Test
  public void TestC() {
    BinHandler handler = new BinHandler();
    Review r = new Review("RX99999", "Hello", "21/12/2023", "UX00001");
    String test = handler.addReviews(r);
    assertTrue(test != null);
    Review rb = handler.updateReview(r, "Hello", "Hello, World", prefix.REVCONTENT);
    assertTrue(rb.getRevContent().equals("Hello, World"));
    boolean bool = handler.deleteReviews(r);
    assertTrue(bool);
  }
}
