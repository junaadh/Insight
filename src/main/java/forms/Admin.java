package forms;

import helper.Misc.prefix;

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
      String nationality,
      String adminId) {
    super(nid, username, fullname, password, age, gender, email, phoneNo, nationality, true, false);
    this.adminId = adminId;
  }

  public Admin(String[] args) {
    super(args);
    this.adminId = args[12];
  }

  public String getAdminId() {
    return this.adminId;
  }

  public String buildInfo() {
    StringBuilder fd = new StringBuilder(super.buildInfo());
    fd.append(prefix.ADMINID.getPrefix() + this.adminId);
    return fd.toString();
  }
}
