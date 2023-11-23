package forms;

import java.util.ArrayList;

import helper.Misc;
import helper.Misc.prefix;

public class Mcq extends Question {
  private ArrayList<String> options;
  private String choice;

  public Mcq(String surveyId, boolean isCompulsory, String qId, String qText, ArrayList<String> mcqoptions,
      String choice) {
    super(surveyId, isCompulsory, qId, qText);
    this.options = mcqoptions;
    this.choice = choice;
  }

  public Mcq(String[] args) {
    super(args);
    this.options = Misc.stringArrayList(args[4]);
    this.choice = args[5];
  }

  public ArrayList<String> getMcqOptions() {
    return this.options;
  }

  public String getChoice() {
    return this.choice;
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder(super.buildInfo());
    cur.append(prefix.OPTIONS + Misc.arrayListString(this.options));
    cur.append(prefix.CHOICE + this.choice);
    return cur.toString();
  }
}
