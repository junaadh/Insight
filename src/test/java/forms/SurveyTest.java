package forms;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import db.BinHandler;
import helper.Misc.prefix;

public class SurveyTest {

  @Test
  public void TestA() {
    BinHandler handler = new BinHandler();
    Survey s = new Survey("SX99999", "SC00001");
    String test = handler.addSurvey(s);
    assertTrue(test != null);
    boolean test1 = handler.deleteSurvey(s);
    assertTrue(test1);
  }

  @Test
  public void TestB() {
    BinHandler handler = new BinHandler();
    Survey s1 = new Survey("SX99998", "SC00001");
    String test = handler.addSurvey(s1);
    assertTrue(test != null);
    boolean test1 = handler.deleteSurvey(s1);
    assertTrue(test1);
  }

  @Test
  public void TestC() {
    BinHandler handler = new BinHandler();
    Survey s1 = new Survey("SX99998", "SC00001");
    String test = handler.addSurvey(s1);
    assertTrue(test != null);
    Survey survey = handler.searchSurveys(prefix.SURVEYID, "SX99998");
    boolean test1 = handler.deleteSurvey(survey);
    assertTrue(test1);
  }

}
