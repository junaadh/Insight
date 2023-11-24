package forms;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import db.BinHandler;
import helper.Misc;
import helper.Misc.prefix;

public class QuestionTest {

  @Test
  public void TestA() {
    BinHandler handler = new BinHandler();
    Question A = new Question("SX99901", false, "QX99901", "Hello", null);
    String test = handler.addQuestions(A);
    assertTrue(test != null);
    boolean bool = handler.deleteQuestions(A);
    assertTrue(bool);
  }

  @Test
  public void TestB() {
    BinHandler handler = new BinHandler();
    Question A = new Question("SX99901", false, "QX999001", "Hello", null);
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
    Question A = new Question("SX99901", false, "QX99991", "Hello", null);
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
    Rank r = new Rank("SX99991", false, "QX99901", "Hello", "Rank", opt);
    String test = handler.addQuestions(r);
    assertTrue(test != null);
    boolean bool = handler.deleteQuestions(r);
    assertTrue(bool);
  }
}
