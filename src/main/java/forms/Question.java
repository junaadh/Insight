package forms;

import helper.Manipulator;
import helper.Misc;
import helper.Misc.prefix;

public class Question implements Manipulator {

  private String surveyId;
  private boolean isCompulsory;
  private String qId;
  private String qText;

  public Question(
      String surveyId,
      boolean isCompulsory,
      String qId,
      String qText) {
    this.surveyId = surveyId;
    this.isCompulsory = isCompulsory;
    this.qId = qId;
    this.qText = qText;

  }

  public Question(String[] args) {
    this.surveyId = args[0];
    this.isCompulsory = args[1].equals("true") ? true : false;
    this.qId = args[2];
    this.qText = args[3];
  }

  public String getQId() {
    return this.qId;
  }

  public boolean getIsCompulsory() {
    return this.isCompulsory;
  }

  public String getSurveyId() {
    return this.surveyId;
  }

  public String getQText() {
    return this.qText;
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder();
    cur.append(prefix.SURVEYID.getPrefix() + this.surveyId);
    cur.append(prefix.ISCOMPULSORY.getPrefix() + Misc.boolString(this.isCompulsory));
    cur.append(prefix.QID.getPrefix() + this.qId);
    cur.append(prefix.QTEXT.getPrefix() + this.qText);
    return cur.toString();
  }
}
