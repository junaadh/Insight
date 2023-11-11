package db;

import java.io.IOException;
import java.io.RandomAccessFile;

import db.FilePicker.dbFiles;
import forms.Admin;
import forms.SurveyCreator;
import forms.User;
import helper.Misc;
import helper.RecordInfo;
import helper.Misc.prefix;

public class BinHandler {

  // add wrapper methods
  public String addUser(User user) {
    String data = user.buildInfo();
    if (usernameExists(user.getUsername(), dbFiles.USERS) || nidExists(user.getNid(), dbFiles.USERS)) {
      return null;
    } else {
      try {
        return create(dbFiles.USERS, data) ? "User added successfully" : null;
      } catch (IOException e) {
        System.out.println("ERROR: Failed to create user: " + e.getStackTrace());
      }
      return null;
    }
  }

  public String addAdmin(Admin admin) {
    String data = admin.buildInfo();
    if (usernameExists(admin.getUsername(), dbFiles.ADMINS) || nidExists(admin.getNid(), dbFiles.ADMINS)) {
      return null;
    }
    try {
      return create(dbFiles.ADMINS, data) ? "Admin added successfully" : null;
    } catch (IOException e) {
      System.out.println("ERROR: Failed to create admin: " + e.getStackTrace());
    }
    return null;
  }

  public String addSurveyCreator(SurveyCreator sc) {
    String data = sc.buildInfo();
    if (usernameExists(sc.getUsername(), dbFiles.SURVEY_CREATORS) || nidExists(sc.getNid(), dbFiles.SURVEY_CREATORS)) {
      return null;
    }
    try {
      return create(dbFiles.SURVEY_CREATORS, data) ? "SurveyCreator added successfully" : null;
    } catch (IOException e) {
      System.out.println("ERROR: Failed to create survey creator: " + e.getStackTrace());
    }
    return null;
  }

  public String addSurvey() {
    // TODO:: create survey class and visit here
    return null;
  }

  public String addReviews() {
    // TODO:: create reviews class and visit here
    return null;
  }

  // delete wrapper methods
  public boolean deleteUser(User user) {
    String data = prefix.NID.getPrefix() + user.getNid();
    try {
      return delete(dbFiles.USERS, data);
    } catch (IOException e) {
      System.out.println("ERROR: Failed to remove user: " + e.getStackTrace());
    }
    return false;
  }

  public boolean deleteAdmin(Admin admin) {
    String data = prefix.NID.getPrefix() + admin.getNid();
    try {
      return delete(dbFiles.ADMINS, data);
    } catch (IOException e) {
      System.out.println("ERROR: Failed to remove admin: " + e.getStackTrace());
    }
    return false;
  }

  public boolean deleteSurveyCreator(SurveyCreator sc) {
    String data = prefix.NID.getPrefix() + sc.getNid();
    try {
      return delete(dbFiles.SURVEY_CREATORS, data);
    } catch (IOException e) {
      System.out.println("ERROR: Failed to remove surevey creator: " + e.getStackTrace());
    }
    return false;
  }

  public boolean deleteSurvey() {
    // TODO:: create surveys class
    return false;
  }

  public boolean deleteReviews() {
    // TODO:: create reviews class
    return false;
  }

  // search wrapper methods
  public User searchUsers(prefix field, String query) {
    String queryStr = field.getPrefix() + query;
    try {
      String[] results = Misc.destructure(read(dbFiles.USERS, queryStr));
      return (User) Misc.constructPerson(results, User.class);
    } catch (IOException e) {
      System.out.println("ERROR: Failed fetching data from db: " + e.getStackTrace());
    } catch (Exception er) {
      System.out.println("ERROR: Failed fetching data from db: " + er.getStackTrace());
    }
    return null;
  }

  public Admin searchAdmins(prefix field, String query) {
    String queryStr = field.getPrefix() + query;
    try {
      String[] results = Misc.destructure(read(dbFiles.ADMINS, queryStr));
      return (Admin) Misc.constructPerson(results, Admin.class);
    } catch (IOException e) {
      System.out.println("ERROR: Failed fetcing data from db: " + e.getStackTrace());
    } catch (Exception er) {
      System.out.println("ERROR: Failed fetching data from db: " + er.getStackTrace());
    }
    return null;
  }

  public SurveyCreator searchSurveyCreator(prefix field, String query) {
    String queryStr = field.getPrefix() + query;
    try {
      String[] results = Misc.destructure(read(dbFiles.SURVEY_CREATORS, queryStr));
      return (SurveyCreator) Misc.constructPerson(results, SurveyCreator.class);
    } catch (IOException e) {
      System.out.println("ERROR: Failed fetching data from db: " + e.getStackTrace());
    } catch (Exception er) {
      System.out.println("ERROR: Failed fetching data from db: " + er.getStackTrace());
    }
    return null;
  }

  @SuppressWarnings("unused")
  public String searchSurveys(prefix field, String query) {
    String queryStr = field.getPrefix() + query;
    // TODO:: create survey class and appropriate fields in prefix enum
    // FIXME:: need to return a survey obj
    return null;
  }

  @SuppressWarnings("unused")
  public String searchReviews(prefix field, String query) {
    String queryStr = field.getPrefix() + query;
    // TODO:: create reviews class and appropraite fields in prefix enum
    // FIXME:: needs to return a review obj
    return null;
  }

  // update wrapper methods
  public User updateUser(User user, String oldStr, String newString, prefix field) {
    User usr = null;
    String pref = field.getPrefix();
    try {
      Boolean bool = update(dbFiles.USERS, pref + oldStr, pref + newString, user.getNid());
      // System.out.println("1");
      if (bool) {
        // System.out.println("2");
        usr = searchUsers(prefix.NID, user.getNid());
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to update data: " + e.getStackTrace());
    }
    return usr;
  }

  public Admin updateAdmin(Admin admin, String oldStr, String newString, prefix field) {
    Admin adn = null;
    String pref = field.getPrefix();
    try {
      Boolean bool = update(dbFiles.ADMINS, pref + oldStr, pref + newString, admin.getNid());
      if (bool) {
        adn = searchAdmins(prefix.NID, admin.getNid());
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to update data: " + e.getStackTrace());
    }
    return adn;
  }

  public SurveyCreator updateSurveyCreator(SurveyCreator sc, String oldStr, String newString, prefix field) {
    SurveyCreator scr = null;
    String pref = field.getPrefix();
    try {
      Boolean bool = update(dbFiles.SURVEY_CREATORS, pref + oldStr, pref + newString, sc.getNid());
      if (bool) {
        scr = searchSurveyCreator(prefix.NID, sc.getNid());
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to update data: " + e.getStackTrace());
    }
    return scr;
  }

  public void updateSurvey() {
    // TODO:: implement update survey to return obj survey
  }

  public void updateReview() {
    // TODO:: implement update survey to return obj survey
  }

  // // Misc generic searches
  public boolean usernameExists(String username, dbFiles type) {
    String queryStr = prefix.USERNAME.getPrefix() + username;
    try {
      String result = read(type, queryStr);
      // System.out.println(result + " " + username);
      return result != null ? true : false;
    } catch (IOException e) {
      System.out.println("ERROR: Failed to fetch data from db: " + e.getStackTrace());
    }
    return false;
  }

  public boolean nidExists(String nid, dbFiles type) {
    String queryStr = prefix.NID.getPrefix() + nid;
    try {
      String result = read(type, queryStr);
      // System.out.println(result + " " + nid);
      return result != null ? true : false;
    } catch (IOException e) {
      System.out.println("ERROR: Failed to fetch data from db: " + e.getStackTrace());
    }
    return false;
  }

  // DO NOT CHANGE
  private static int RECORD_INFO_BUFFER = 10;

  protected static boolean create(dbFiles type, String record) throws IOException {
    RandomAccessFile db = FilePicker.getdbFile(type);
    char[] recordChars = record.toCharArray();

    int recordSize = recordChars.length * 3;

    String sizeInfo = String.format("%-" + RECORD_INFO_BUFFER + "s", recordSize);
    db.seek(db.length());
    byte[] infoStream = sizeInfo.getBytes();
    db.write(infoStream);

    db.seek(db.length());
    for (char c : recordChars) {
      db.write(String.format("%03X", (int) c).getBytes());
    }
    db.close();
    return true;
  }

  protected static String read(dbFiles type, String query) throws IOException {
    RandomAccessFile db = FilePicker.getdbFile(type);

    RecordInfo info = findRecordPostion(db, query);
    if (!info.isNull()) {
      db.seek(info.position);
      StringBuilder og = new StringBuilder();
      for (int i = 0; i < info.record; i += 3) {
        byte[] hexBytes = new byte[3];
        db.read(hexBytes);
        int charvalue = Integer.parseInt(new String(hexBytes), 16);
        og.append((char) charvalue);
      }
      db.close();
      return og.toString();
    }

    db.close();
    return null;
  }

  protected static boolean update(dbFiles type, String oldStr, String newStr, String uniqueID) throws IOException {
    String old = read(type, uniqueID);
    String newContent = old.replace(oldStr, newStr);
    if (delete(type, uniqueID) && create(type, newContent)) {
      return true;
    }
    return false;
  }

  protected static boolean delete(dbFiles type, String query) throws IOException {
    RandomAccessFile db = FilePicker.getdbFile(type);

    RecordInfo info = findRecordPostion(db, query);
    if (!info.isNull()) {
      int intTrailing;
      db.seek(info.position - RECORD_INFO_BUFFER);
      db.write(new byte[info.record + RECORD_INFO_BUFFER]);
      db.seek(info.position - RECORD_INFO_BUFFER);
      long trailingBytes = db.length() - ((info.position + info.record));
      if (trailingBytes == 0) {
        intTrailing = 0;
      } else {
        intTrailing = (int) trailingBytes;
      }
      byte[] copyBuf = new byte[intTrailing];

      db.read(copyBuf);
      db.seek(info.position - RECORD_INFO_BUFFER);
      db.write(copyBuf, 0, intTrailing);
      db.seek(info.position + trailingBytes);
      db.setLength(db.length() - (info.record + RECORD_INFO_BUFFER));
      db.close();
      return true;
    }
    db.close();
    return false;
  }

  protected static RecordInfo findRecordPostion(RandomAccessFile dbBin, String query) throws IOException {
    long currentPosition = 0;

    while (currentPosition < dbBin.length()) {
      dbBin.seek(currentPosition);

      byte[] infoBytes = new byte[RECORD_INFO_BUFFER];
      dbBin.read(infoBytes);
      int recordSize = Integer.parseInt(new String(infoBytes).trim());

      currentPosition += RECORD_INFO_BUFFER;
      dbBin.seek(currentPosition);

      StringBuilder currentRecordValue = new StringBuilder();
      for (int i = 0; i < recordSize; i += 3) {
        byte[] hex = new byte[3];
        dbBin.read(hex);
        int charv = Integer.parseInt(new String(hex), 16);
        currentRecordValue.append((char) charv);
      }

      if (currentRecordValue.toString().contains(query)) {
        return new RecordInfo(recordSize, currentPosition);
      }

      currentPosition += recordSize;
    }

    return new RecordInfo();
  }

}
