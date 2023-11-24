package forms;

import helper.Manipulator;
import helper.Misc.prefix;

public class Response implements Manipulator {
  private String responseId;
  private String qId;
  private String nid;
  private String surveyId;
  private String reponses;

  public Response(String reponseId, String QId, String nid, String surveyId, String response) {
    this.responseId = reponseId;
    this.qId = QId;
    this.nid = nid;
    this.surveyId = surveyId;
    this.reponses = response;
  }

  public Response(String[] args) {
    this.qId = args[1];
    this.nid = args[2];
    this.surveyId = args[3];
    this.reponses = args[4];
  }

  public String getResponseId() {
    return this.responseId;
  }

  public String getQId() {
    return this.qId;
  }

  public String getNid() {
    return this.nid;
  }

  public String getSurveyId() {
    return this.surveyId;
  }

  public String getReponses() {
    return this.reponses;
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder();
    cur.append(prefix.RESPONSEID + this.responseId);
    cur.append(prefix.QID.getPrefix() + this.qId);
    cur.append(prefix.NID.getPrefix() + this.nid);
    cur.append(prefix.SURVEYID.getPrefix() + this.surveyId);
    cur.append(prefix.RESPONSES.getPrefix() + this.reponses);
    return cur.toString();
  }
}
