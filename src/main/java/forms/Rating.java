package forms;

import helper.Misc.prefix;

public class Rating extends Question {
  private int rating;

  public Rating(String surveyId, boolean isCompulosry, String qId, String qText, int rating) {
    super(surveyId, isCompulosry, qId, qText);
    this.rating = rating;
  }

  public Rating(String[] args) {
    super(args);
    this.rating = Integer.parseInt(args[4]);
  }

  public int getRating() {
    return this.rating;
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder(super.buildInfo());
    cur.append(prefix.RATING + String.valueOf(this.rating));
    return cur.toString();
  }
}
