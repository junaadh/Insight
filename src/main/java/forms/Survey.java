package forms;

import java.util.ArrayList;

import helper.Misc;
import helper.Misc.prefix;

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
    this.surveyId = surveyId;
    this.scId = scId;
    this.isPublic = isPublic;
    this.participants = participants;
  }

  public Survey(String[] args) {
    this.surveyId = args[0];
    this.scId = args[1];
    this.isPublic = args[2].equals("true") ? true : false;
    this.participants = Misc.stringArrayList(args[3]);
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
    return this.participants;
  }

  public void setIsPublic() {
    this.isPublic = true;
  }

  public String buildInfo() {
    StringBuilder sur = new StringBuilder();
    sur.append(prefix.SURVEYID.getPrefix() + this.surveyId);
    sur.append(prefix.SCID.getPrefix() + this.scId);
    sur.append(prefix.ISPUBLIC.getPrefix() + Misc.boolString(this.isPublic));
    sur.append(prefix.PARTICIPANTS.getPrefix() + Misc.arrayListString(this.getparticipants()));
    return sur.toString();
  }
}
