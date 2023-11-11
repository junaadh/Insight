package forms;

import helper.Misc;
import helper.Misc.prefix;

public class Person {

  private String nid;
  private String username;
  private String fullname;
  private String password;
  private int age;
  private String gender;
  private String email;
  private int phoneNo;
  private boolean isAdmin;
  private boolean isSurveyCreator;

  public Person(
      String nid,
      String username,
      String fullname,
      String password,
      int age,
      String gender,
      String email,
      int phoneNo,
      boolean isAdmin,
      boolean isSurveyCreator) {
    // can be passport/myKad
    this.nid = nid;
    this.username = username;
    this.fullname = fullname;
    this.password = password;
    this.age = age;
    this.gender = gender;
    this.email = email;
    this.phoneNo = phoneNo;
    this.isAdmin = isAdmin;
    this.isSurveyCreator = isSurveyCreator;
  }

  public Person(String[] args) {
    this.nid = args[1];
    this.username = args[2];
    this.fullname = args[3];
    this.password = args[4];
    this.age = Integer.parseInt(args[5]);
    this.gender = args[6];
    this.email = args[7];
    this.phoneNo = Integer.parseInt(args[8]);
    this.isAdmin = args[9].equals("true") ? true : false;
    this.isSurveyCreator = args[10].equals("true") ? true : false;
  }

  public String getNid() {
    return this.nid;
  }

  public String getUsername() {
    return this.username;
  }

  public String getFullname() {
    return this.fullname;
  }

  public String getPassword() {
    return this.password;
  }

  public int getAge() {
    return this.age;
  }

  public String getAgeString() {
    return String.valueOf(this.age);
  }

  public String getGender() {
    return this.gender;
  }

  public String getEmail() {
    return this.email;
  }

  public int getPhoneNo() {
    return this.phoneNo;
  }

  public String getPhoneNoString() {
    return String.valueOf(this.phoneNo);
  }

  public boolean getIsAdmin() {
    return this.isAdmin;
  }

  public boolean getIsSurveyCreator() {
    return this.isSurveyCreator;
  }

  public String buildInfo() {
    StringBuilder data = new StringBuilder();
    data.append(prefix.NID.getPrefix() + this.nid);
    data.append(prefix.USERNAME.getPrefix() + this.username);
    data.append(prefix.FULLNAME.getPrefix() + getFullname());
    data.append(prefix.PASSWORD.getPrefix() + this.password);
    data.append(prefix.AGE.getPrefix() + getAgeString());
    data.append(prefix.GENDER.getPrefix() + this.gender);
    data.append(prefix.EMAIL.getPrefix() + this.email);
    data.append(prefix.PHONENO.getPrefix() + getPhoneNoString());
    data.append(prefix.ISADMIN.getPrefix() + Misc.boolString(this.isAdmin));
    data.append(prefix.ISSURVEYCREATOR.getPrefix() + Misc.boolString(this.isSurveyCreator));
    return data.toString();
  }
}
