package forms;

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

  public String getGender() {
    return this.gender;
  }

  public String getEmail() {
    return this.email;
  }

  public int getPhoneNo() {
    return this.phoneNo;
  }

  public boolean getIsAdmin() {
    return this.isAdmin;
  }

  public boolean getIsSurveyCreator() {
    return this.isSurveyCreator;
  }
}
