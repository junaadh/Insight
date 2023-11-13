package forms;

import helper.Misc.prefix;

public class SurveyCreator extends Person {

  private String scId;
  private String scDept;

  public SurveyCreator(
      String nid,
      String username,
      String fullname,
      String password,
      int age,
      String gender,
      String email,
      int phoneNo,
      String nationality,
      String scId,
      String scDept) {
    super(nid, username, fullname, password, age, gender, email, phoneNo, nationality, false, false);
    this.scId = scId;
    this.scDept = scDept;
  }

  public SurveyCreator(String[] args) {
    super(args);
    this.scId = args[12];
    this.scDept = args[13];
  }

  public String getScId() {
    return this.scId;
  }

  public String getScDept() {
    return this.scDept;
  }

  public String buildInfo() {
    StringBuilder fd = new StringBuilder(super.buildInfo());
    fd.append(prefix.SCID.getPrefix() + this.scId);
    fd.append(prefix.SCDEPT.getPrefix() + this.scDept);
    return fd.toString();
  }
}
