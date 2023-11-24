package forms;

public class Openended extends Question {

  public Openended(String surveyId, boolean isCompulosry, String qId, String qText, String qtype) {
    super(surveyId, isCompulosry, qId, qText, qtype);
  }

  public Openended(String[] args) {
    super(args);
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder(super.buildInfo());
    return cur.toString();
  }

}
