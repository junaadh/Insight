package forms;

public class Opinion extends Question {

  public Opinion(String surveyId, boolean isCompulosry, String qId, String qText, String qtype) {
    super(surveyId, isCompulosry, qId, qText, qtype);
  }

  public Opinion(String[] args) {
    super(args);
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder(super.buildInfo());
    return cur.toString();
  }
}
