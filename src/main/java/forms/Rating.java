package forms;

public class Rating extends Question {

  public Rating(String surveyId, boolean isCompulosry, String qId, String qText, String qType) {
    super(surveyId, isCompulosry, qId, qText, qType);
  }

  public Rating(String[] args) {
    super(args);
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder(super.buildInfo());
    return cur.toString();
  }
}
