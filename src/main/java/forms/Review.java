package forms;

import helper.Manipulator;
import helper.Misc.prefix;

public class Review implements Manipulator {

  private String revId;
  private String revContent;
  private String dateCreated;
  private String userId;

  public Review(String revId, String revContent, String dateCreated, String userId) {
    this.revId = revId;
    this.revContent = revContent;
    this.dateCreated = dateCreated;
    this.userId = userId;
  }

  public Review(String[] args) {
    this.revId = args[0];
    this.revContent = args[1];
    this.dateCreated = args[2];
    this.userId = args[3];
  }

  public String getRevId() {
    return this.revId;
  }

  public String getRevContent() {
    return this.revContent;
  }

  public String getDateCreated() {
    return this.dateCreated;
  }

  public String getUserId() {
    return this.getUserId();
  }

  public String buildInfo() {
    StringBuilder cur = new StringBuilder();
    cur.append(prefix.REVID.getPrefix() + this.revId);
    cur.append(prefix.REVCONTENT.getPrefix() + this.revContent);
    cur.append(prefix.DATACREATED.getPrefix() + this.dateCreated);
    cur.append(prefix.USERID.getPrefix() + this.userId);
    return cur.toString();
  }
}
