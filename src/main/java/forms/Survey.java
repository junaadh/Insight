package forms;

import helper.Manipulator;
import helper.Misc.prefix;

public class Survey implements Manipulator {
  private String surveyId;
  private String scId;

  public Survey(
      String surveyId,
      String scId) {
    this.surveyId = surveyId;
    this.scId = scId;
  }

  public Survey(String[] args) {
    this.surveyId = args[0];
    this.scId = args[1];
  }

  public Survey() {
    // dummy to get header;
  }

  public String getSurveyId() {
    return this.surveyId;
  }

  public String getScId() {
    return this.scId;
  }

  // for Session usage only
  public String getNid() {
    return null;
  }

  public String buildInfo() {
    StringBuilder sur = new StringBuilder();
    sur.append(prefix.SURVEYID.getPrefix() + this.surveyId);
    sur.append(prefix.SCID.getPrefix() + this.scId);
    return sur.toString();
  }
}
