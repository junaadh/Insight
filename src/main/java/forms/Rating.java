package forms;

import helper.Misc;

import java.util.ArrayList;

public class Rating extends Question {

  private double options;
  public Rating(String surveyId, boolean isCompulosry, String qId, String qText, String qType) {
    super(surveyId, isCompulosry, qId, qText, qType);
    //this.options = value;
  }

  public Rating(String[] args) {
    super(args);
    //this.options = (int) Double.parseDouble(args[5]);
  }

  public double getvalue() {
    return this.options;
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder(super.buildInfo());
    //cur.append(this.options);
    return cur.toString();
  }
}
