package forms;

import java.util.ArrayList;

import helper.Misc;
import helper.Misc.prefix;

public class Likert extends Question {
  private ArrayList<String> options;

  public Likert(String surveyId, boolean isCompulosry, String qId, String qText, String qType) {
    super(surveyId, isCompulosry, qId, qText, qType);
    //this.options = value;
  }

  public Likert(String[] args) {
    super(args);
    //this.options = Misc.stringArrayList(args[5]);
  }

  public ArrayList<String> getOptions() {
    return this.options;
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder(super.buildInfo());
    //cur.append(this.options);
    return cur.toString();
  }
}
