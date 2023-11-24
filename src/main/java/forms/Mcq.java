package forms;

import java.util.ArrayList;

import helper.Misc;
import helper.Misc.prefix;

public class Mcq extends Question {
  private ArrayList<String> options;

  public Mcq(String surveyId, boolean isCompulsory, String qId, String qText, String qType,
      ArrayList<String> mcqoptions) {
    super(surveyId, isCompulsory, qId, qText, qType);
    this.options = mcqoptions;
  }

  public Mcq(String[] args) {
    super(args);
    this.options = Misc.stringArrayList(args[5]);
  }

  public ArrayList<String> getMcqOptions() {
    return this.options;
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder(super.buildInfo());
    cur.append(prefix.OPTIONS.getPrefix() + Misc.arrayListString(this.options));
    return cur.toString();
  }
}
