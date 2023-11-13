package helper;

import forms.Person;

public final class Session {
  private static volatile Session instance;
  private Person person = null;
  private boolean isAdmin = false;
  private boolean isSurveyCreator = false;
  private boolean isUser = false;

  private Session() {
  }

  public static Session getInstance() {
    if (instance == null) {
      synchronized (Session.class) {
        if (instance == null) {
          instance = new Session();
        }
      }
    }

    return instance;
  }

  public void setPerson(Person person) {
    this.person = person;
    this.isAdmin = person.getIsAdmin();
    this.isSurveyCreator = person.getIsSurveyCreator();
    this.isUser = !this.isAdmin && !this.isSurveyCreator ? true : false;

  }

  public Person getPerson() {
    return this.person;
  }

  public boolean isAdmin() {
    return this.isAdmin;
  }

  public boolean isSurveyCreator() {
    return this.isSurveyCreator;
  }

  public boolean isUser() {
    return this.isUser;
  }

  public void initSession() {
    this.person = null;
    this.isAdmin = false;
    this.isSurveyCreator = false;
    this.isUser = false;
  }

  public String role() {
    if (this.isAdmin) {
      return "Admin";
    } else if (this.isSurveyCreator) {
      return "Survey Creator";
    } else {
      return "User";
    }
  }

  // for debugging
  public void setIsAdmin() {
    this.isAdmin = !this.isAdmin;
  }
}
