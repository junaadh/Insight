package forms;

public class Admin extends Person {

  private String adminId;

  public Admin(
      String nid,
      String username,
      String fullname,
      String password,
      int age,
      String gender,
      String email,
      int phoneNo,
      String adminId) {
    super(nid, username, fullname, password, age, gender, email, phoneNo, true, false);
    this.adminId = adminId;
  }

  public String getAdminId() {
    return this.adminId;
  }

}
