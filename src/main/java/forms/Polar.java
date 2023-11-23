package forms;

public class Polar extends Question {

  public Polar(String surveyId, boolean isCompulsory, String qId, String qText) {
    super(surveyId, isCompulsory, qId, qText);
  }

  public Polar(String[] args) {
    super(args);
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder(super.buildInfo());
    return cur.toString();
  }
}
