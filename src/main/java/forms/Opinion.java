package forms;

import helper.Misc.prefix;

public class Opinion extends Question {
  private String answer;

  public Opinion(String surveyId, boolean isCompulosry, String qId, String qText, String answer) {
    super(surveyId, isCompulosry, qId, qText);
    this.answer = answer;
  }

  public Opinion(String[] args) {
    super(args);
    this.answer = args[4];
  }

  public String getAnswer() {
    return this.answer;
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder(super.buildInfo());
    cur.append(prefix.ANSWER + this.answer);
    return cur.toString();
  }
}
