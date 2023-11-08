package forms;

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
      String scId,
      String scDept) {
    super(nid, username, fullname, password, age, gender, email, phoneNo, false, false);
    this.scId = scId;
    this.scDept = scDept;
  }

  public String getScId() {
    return this.scId;
  }

  public String getScDept() {
    return this.scDept;
  }
}
