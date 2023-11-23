package forms;

import java.util.ArrayList;
import java.util.HashMap;

import helper.Misc;
import helper.Misc.prefix;

public class Rank extends Question {
  private ArrayList<String> options;
  private HashMap<String, String> ranked;

  public Rank(String qId, boolean isCompulsory, String surveyId, String qText, ArrayList<String> options,
      HashMap<String, String> ranked) {
    super(surveyId, isCompulsory, qId, qText);
    this.options = options;
    this.ranked = ranked;
  }

  public Rank(String[] args) {
    super(args);
    this.options = Misc.stringArrayList(args[4]);
    this.ranked = Misc.stringHashMap(args[5]);
  }

  public ArrayList<String> getOptions() {
    return this.options;
  }

  public HashMap<String, String> getRanked() {
    return this.ranked;
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder(super.buildInfo());
    cur.append(prefix.OPTIONS.getPrefix() + Misc.arrayListString(this.options));
    cur.append(prefix.RANKED.getPrefix() + Misc.hashMapString(this.ranked));
    return cur.toString();
  }
}
