package forms;

import helper.Manipulator;

public class SurveyResponse implements Manipulator {

  private String username;
  private String nid;
  private String surveyId;

  public SurveyResponse() {

  }

  public SurveyResponse(String... fields) {
    this.username = fields[0];
    this.nid = fields[1];
    this.surveyId = fields[2];
  }

  public String getUsername() {
    return this.username;
  }

  public String getNid() {
    return this.nid;
  }

  public String getSurveyId() {
    return this.surveyId;
  }

  public String buildInfo() {
    return null;
  }
}
