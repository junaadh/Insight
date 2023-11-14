package forms;

import java.util.ArrayList;

public class Survey {
  private String surveyId;
  private String scId;
  private boolean isPublic;
  private ArrayList<String> participants;

  public Survey(
      String surveyId,
      String scId,
      boolean isPublic,
      ArrayList<String> participants) {

  }

  public String getsurveyId() {
    return this.surveyId;
  }

  public String getscId() {
    return this.scId;
  }

  public boolean getisPublic() {
    return this.isPublic;
  }

  public ArrayList<String> getparticipants() {
    return participants;
  }

  public void setIsPublic() {
    this.isPublic = true;
  }

}
