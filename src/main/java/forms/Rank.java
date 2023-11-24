package forms;

import java.util.ArrayList;

import helper.Misc;
import helper.Misc.prefix;

public class Rank extends Question {
  private ArrayList<String> options;

  public Rank(String qId, boolean isCompulsory, String surveyId, String qText, String qType,
      ArrayList<String> options) {
    super(surveyId, isCompulsory, qId, qText, qType);
    this.options = options;
  }

  public Rank(String[] args) {
    super(args);
    this.options = Misc.stringArrayList(args[5]);
  }

  public ArrayList<String> getOptions() {
    return this.options;
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder(super.buildInfo());
    cur.append(prefix.OPTIONS.getPrefix() + Misc.arrayListString(this.options));
    return cur.toString();
  }
}
