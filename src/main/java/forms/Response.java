package forms;

import java.util.ArrayList;

import helper.Manipulator;
import helper.Misc;
import helper.Misc.prefix;

public class Response implements Manipulator {
  private String responseId;
  private String nid;
  private String surveyId;
  private ArrayList<String> reponses;

  public Response(String responseId, String nid, String surveyId, ArrayList<String> response) {
    this.responseId = responseId;
    this.nid = nid;
    this.surveyId = surveyId;
    this.reponses = response;
  }

  public Response(String[] args) {
    this.responseId = args[0];
    this.nid = args[1];
    this.surveyId = args[2];
    this.reponses = Misc.stringArrayList(args[3]);
  }

  public String getResponseId() {
    return this.responseId;
  }

  public String getNid() {
    return this.nid;
  }

  public String getSurveyId() {
    return this.surveyId;
  }

  public ArrayList<String> getReponses() {
    return this.reponses;
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder();
    cur.append(prefix.RESPONSEID + this.responseId);
    cur.append(prefix.NID + this.nid);
    cur.append(prefix.SURVEYID + this.surveyId);
    cur.append(prefix.RESPONSES + Misc.arrayListString(this.reponses));
    return cur.toString();
  }
}
