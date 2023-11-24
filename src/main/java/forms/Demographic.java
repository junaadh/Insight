package forms;

public class Demographic extends Question {

  public Demographic(String surveyId, boolean isCompulosry, String qId, String qText, String qType) {
    super(surveyId, isCompulosry, qId, qText, qType);
  }

  public Demographic(String[] args) {
    super(args);
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder(super.buildInfo());
    return cur.toString();
  }

}
