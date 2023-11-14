package forms;

import java.util.ArrayList;

public class Question {

  private String qId;
  private boolean isCompulsory;
  private String surveyId;
  private String qText;

  public Question(
      String surveyId,
      boolean isPublic,
      boolean isCompulsory,
      String qId,
      String qText,
      ArrayList<String> qType) {
    this.qId = qId;
    this.isCompulsory = isCompulsory;
    this.surveyId = surveyId;
    this.qText = qText;

  }

  public Question(String[] args) {

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
}
