package forms;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import db.BinHandler;
import helper.Misc;
import helper.Misc.prefix;

public class QuestionTest {

  @Test
  public void TestA() {
    BinHandler handler = new BinHandler();
    Question A = new Question("SX00001", false, "QX00001", "Hello");
    String test = handler.addQuestions(A);
    assertTrue(test != null);
    boolean bool = handler.deleteQuestions(A);
    assertTrue(bool);
  }

  @Test
  public void TestB() {
    BinHandler handler = new BinHandler();
    Question A = new Question("SX00001", false, "QX00001", "Hello");
    String test = handler.addQuestions(A);
    assertTrue(test != null);
    Question B = handler.searchQuestions(prefix.QID, A.getQId(), Question.class);
    assertEquals(B.buildInfo(), A.buildInfo());
    boolean bool = handler.deleteQuestions(B);
    assertTrue(bool);
  }

  @Test
  public void TestC() {
    BinHandler handler = new BinHandler();
    Question A = new Question("SX00001", false, "QX00001", "Hello");
    String test = handler.addQuestions(A);
    assertTrue(test != null);
    Question B = handler.updateQuestion(A, "false", "true", prefix.ISCOMPULSORY);
    assertEquals(Misc.boolString(B.getIsCompulsory()), "true");
    boolean bool = handler.deleteQuestions(B);
    assertTrue(bool);
  }

  @Test
  public void TestD() {
    BinHandler handler = new BinHandler();
    ArrayList<String> opt = new ArrayList<String>();
    opt.add("World");
    HashMap<String, String> rnk = new HashMap<>();
    Rank r = new Rank("QX00001", false, "SX00001", "Hello", opt, rnk);
    String test = handler.addQuestions(r);
    assertTrue(test != null);
    boolean bool = handler.deleteQuestions(r);
    assertTrue(bool);
  }
}
