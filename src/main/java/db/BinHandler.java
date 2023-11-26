package db;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import db.FilePicker.dbFiles;
import forms.Admin;
import forms.Person;
import forms.Question;
import forms.Response;
import forms.Review;
import forms.Survey;
import forms.SurveyCreator;
import forms.User;
import helper.Manipulator;
import helper.Misc;
import helper.RecordInfo;
import helper.Misc.prefix;

public class BinHandler implements Manipulator {

  @Override
  public String buildInfo() {
    return null;
  }

  // add wrapper methods
  public String addUser(User user) {
    String data = user.buildInfo();
    if (usernameExists(user.getUsername(), dbFiles.USERS) || nidExists(user.getNid(), dbFiles.USERS)) {
      return null;
    } else {
      try {
        create(dbFiles.PERSON, data);
        return create(dbFiles.USERS, data) ? "User added successfully" : null;
      } catch (IOException e) {
        System.out.println("ERROR: Failed to create user: " + e.getMessage());
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
      create(dbFiles.PERSON, data);
      return create(dbFiles.ADMINS, data) ? "Admin added successfully" : null;
    } catch (IOException e) {
      System.out.println("ERROR: Failed to create admin: " + e.getMessage());
    }
    return null;
  }

  public String addSurveyCreator(SurveyCreator sc) {
    String data = sc.buildInfo();
    if (usernameExists(sc.getUsername(), dbFiles.SURVEY_CREATORS) || nidExists(sc.getNid(), dbFiles.SURVEY_CREATORS)) {
      return null;
    }
    try {
      create(dbFiles.PERSON, data);
      return create(dbFiles.SURVEY_CREATORS, data) ? "SurveyCreator added successfully" : null;
    } catch (IOException e) {
      System.out.println("ERROR: Failed to create survey creator: " + e.getMessage());
    }
    return null;
  }

  public String addSurvey(Survey s) {
    String data = s.buildInfo();
    try {
      if (read(dbFiles.SURVEYS, s.getSurveyId()) == null) {
        return create(dbFiles.SURVEYS, data) ? "Survey added successfully" : null;
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to create survey");
    }
    return null;
  }

  public String addReviews(Review r) {
    String data = r.buildInfo();
    try {
      if (read(dbFiles.REVIEWS, r.getRevId()) == null) {
        return create(dbFiles.REVIEWS, data) ? "Review added successfully" : null;
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to create review");
    }
    return null;
  }

  public <T extends Question> String addQuestions(T q) {
    String data = q.buildInfo();
    // System.out.println(data);
    try {
      if (read(dbFiles.QUESTIONS, q.getQId()) == null) {
        return create(dbFiles.QUESTIONS, data) ? "Question added succesfully" : null;
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to create questions");
    }
    return null;
  }

  public String addResponse(Response r) {
    String data = r.buildInfo();
    try {
      if (read(dbFiles.RESPONSES, r.getResponseId()) == null) {
        return create(dbFiles.RESPONSES, data) ? "Response saved succussfully" : null;
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to create response");
    }
    return null;
  }

  // delete wrapper methods
  public boolean deleteUser(User user) {
    String data = prefix.NID.getPrefix() + user.getNid();
    try {
      delete(dbFiles.PERSON, data);
      return delete(dbFiles.USERS, data);
    } catch (IOException e) {
      System.out.println("ERROR: Failed to remove user: " + e.getMessage());
    }
    return false;
  }

  public boolean deleteAdmin(Admin admin) {
    String data = prefix.NID.getPrefix() + admin.getNid();
    try {
      delete(dbFiles.PERSON, data);
      return delete(dbFiles.ADMINS, data);
    } catch (IOException e) {
      System.out.println("ERROR: Failed to remove admin: " + e.getMessage());
    }
    return false;
  }

  public boolean deleteSurveyCreator(SurveyCreator sc) {
    String data = prefix.NID.getPrefix() + sc.getNid();
    try {
      delete(dbFiles.PERSON, data);
      return delete(dbFiles.SURVEY_CREATORS, data);
    } catch (IOException e) {
      System.out.println("ERROR: Failed to remove surevey creator: " + e.getMessage());
    }
    return false;
  }

  public boolean deleteSurvey(Survey s) {
    String data = prefix.SURVEYID.getPrefix() + s.getSurveyId();
    try {
      return delete(dbFiles.SURVEYS, data);
    } catch (IOException e) {
      System.out.println("ERROR: Failed to remove survey: " + e.getMessage());
    }
    return false;
  }

  public boolean deleteReviews(Review r) {
    String data = prefix.REVID.getPrefix() + r.getRevId();
    try {
      return delete(dbFiles.REVIEWS, data);
    } catch (IOException e) {
      System.out.println("ERROR: Failed to remove review: " + e.getMessage());
    }
    return false;
  }

  public boolean deleteQuestions(Question q) {
    String data = prefix.QID.getPrefix() + q.getQId();
    try {
      return delete(dbFiles.QUESTIONS, data);
    } catch (IOException e) {
      System.out.println("ERROR: Failed to remove question: " + e.getMessage());
    }
    return false;
  }

  public boolean deleteResponse(Response r) {
    String data = prefix.RESPONSEID.getPrefix() + r.getResponseId();
    try {
      return delete(dbFiles.RESPONSES, data);
    } catch (IOException e) {
      System.out.println("ERROR: Failed to remove response: " + e.getMessage());
    }
    return false;
  }

  // search wrapper methods
  public Person searchPerson(prefix field, String query) {
    String queryStr = field.getPrefix() + query;
    try {
      String[] results = Misc.destructure(read(dbFiles.PERSON, queryStr));
      return Misc.constructPerson(results, Person.class);
    } catch (IOException e) {
      System.out.println("ERROR: Failed fetching data from db: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed fetching data from db: " + er.getMessage());
    }
    return null;
  }

  public User searchUsers(prefix field, String query) {
    String queryStr = field.getPrefix() + query;
    try {
      String[] results = Misc.destructure(read(dbFiles.USERS, queryStr));
      return (User) Misc.constructPerson(results, User.class);
    } catch (IOException e) {
      System.out.println("ERROR: Failed fetching data from db: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed fetching data from db: " + er.getMessage());
    }
    return null;
  }

  public Admin searchAdmins(prefix field, String query) {
    String queryStr = field.getPrefix() + query;
    try {
      String[] results = Misc.destructure(read(dbFiles.ADMINS, queryStr));
      return (Admin) Misc.constructPerson(results, Admin.class);
    } catch (IOException e) {
      System.out.println("ERROR: Failed fetcing data from db: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed fetching data from db: " + er.getMessage());
    }
    return null;
  }

  public SurveyCreator searchSurveyCreator(prefix field, String query) {
    String queryStr = field.getPrefix() + query;
    try {
      String[] results = Misc.destructure(read(dbFiles.SURVEY_CREATORS, queryStr));
      return (SurveyCreator) Misc.constructPerson(results, SurveyCreator.class);
    } catch (IOException e) {
      System.out.println("ERROR: Failed fetching data from db: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed fetching data from db: " + er.getMessage());
    }
    return null;
  }

  public Survey searchSurveys(prefix field, String query) {
    String queryStr = field.getPrefix() + query;
    try {
      String data = read(dbFiles.SURVEYS, queryStr);
      String[] results = Misc.destructure(data);
      return (Survey) Misc.construct(results, Survey.class);
    } catch (IOException e) {
      System.out.println("ERROR: Failed fetching data from db: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed fetching datay from db: " + er.getLocalizedMessage());
    }
    return null;
  }

  public Review searchReviews(prefix field, String query) {
    String queryStr = field.getPrefix() + query;
    try {
      String[] results = Misc.destructure(read(dbFiles.REVIEWS, queryStr));
      return Misc.construct(results, Review.class);
    } catch (IOException e) {
      System.out.println("ERROR: Failed fetching data from db: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed fetching datay from db: " + er.getMessage());
    }
    return null;
  }

  public <T extends Question> T searchQuestions(prefix field, String query, Class<T> className) {
    String queryStr = field.getPrefix() + query;
    try {
      String[] results = Misc.destructure(read(dbFiles.QUESTIONS, queryStr));
      return Misc.construct(results, className);
    } catch (IOException e) {
      System.out.println("ERROR: Failed fetching data from db: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed fetching data from db: " + er.getMessage());
    }
    return null;
  }

  public Response searchResponse(prefix field, String query) {
    String queryStr = field.getPrefix() + query;
    try {
      String[] results = Misc.destructure(read(dbFiles.RESPONSES, queryStr));
      return Misc.construct(results, Response.class);
    } catch (IOException e) {
      System.out.println("ERROR: Failed fetching data from db: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed fetching datay from db: " + er.getMessage());
    }
    return null;

  }

  // update wrapper methods
  public User updateUser(User user, String oldStr, String newString, prefix field) {
    User usr = null;
    String pref = field.getPrefix();
    try {
      Boolean bool = update(dbFiles.USERS, pref + oldStr, pref + newString, user.getNid());
      Boolean bool2 = update(dbFiles.PERSON, pref + oldStr, pref + newString, user.getNid());
      // System.out.println("1");
      if (bool && bool2) {
        // System.out.println("2");
        usr = searchUsers(prefix.NID, user.getNid());
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to update data: " + e.getMessage());
    }
    return usr;
  }

  public Admin updateAdmin(Admin admin, String oldStr, String newString, prefix field) {
    Admin adn = null;
    String pref = field.getPrefix();
    try {
      Boolean bool = update(dbFiles.ADMINS, pref + oldStr, pref + newString, admin.getNid());
      Boolean bool2 = update(dbFiles.PERSON, pref + oldStr, pref + newString, admin.getNid());
      if (bool && bool2) {
        adn = searchAdmins(prefix.NID, admin.getNid());
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to update data: " + e.getMessage());
    }
    return adn;
  }

  public SurveyCreator updateSurveyCreator(SurveyCreator sc, String oldStr, String newString, prefix field) {
    SurveyCreator scr = null;
    String pref = field.getPrefix();
    try {
      Boolean bool = update(dbFiles.SURVEY_CREATORS, pref + oldStr, pref + newString, sc.getNid());
      Boolean bool2 = update(dbFiles.PERSON, pref + oldStr, pref + newString, sc.getNid());
      if (bool && bool2) {
        scr = searchSurveyCreator(prefix.NID, sc.getNid());
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to update data: " + e.getMessage());
    }
    return scr;
  }

  public Survey updateSurvey(Survey s, String oldStr, String newStr, prefix field) {
    Survey sc = null;
    String pref = field.getPrefix();
    try {
      Boolean bool = update(dbFiles.SURVEYS, pref + oldStr, pref + newStr, s.getSurveyId());
      if (bool) {
        sc = searchSurveys(prefix.SURVEYID, s.getSurveyId());
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to update data: " + e.getMessage());
    }
    return sc;
  }

  public Review updateReview(Review r, String oldStr, String newStr, prefix field) {
    Review rw = null;
    String pref = field.getPrefix();
    try {
      Boolean bool = update(dbFiles.REVIEWS, pref + oldStr, pref + newStr, r.getRevId());
      if (bool) {
        rw = searchReviews(prefix.REVID, r.getRevId());
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to update data: " + e.getMessage());
    }
    return rw;
  }

  public Question updateQuestion(Question q, String oldStr, String newStr, prefix field) {
    String pref = field.getPrefix();
    try {
      Boolean bool = update(dbFiles.QUESTIONS, pref + oldStr, pref + newStr, q.getQId());
      if (bool) {
        return searchQuestions(prefix.QID, q.getQId(), Question.class);
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to update data: " + e.getMessage());
    }
    return null;
  }

  public Response updateResponse(Response r, String oldStr, String newStr, prefix field) {
    String pref = field.getPrefix();
    try {
      Boolean bool = update(dbFiles.RESPONSES, pref + oldStr, pref + newStr, r.getResponseId());
      if (bool) {
        return searchResponse(prefix.RESPONSEID, r.getResponseId());
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to update data: " + e.getMessage());
    }
    return null;

  }

  // // Misc generic searches
  public boolean usernameExists(String username, dbFiles type) {
    String queryStr = prefix.USERNAME.getPrefix() + username;
    try {
      String result = read(type, queryStr);
      // System.out.println(result + " " + username);
      return result != null ? true : false;
    } catch (IOException e) {
      System.out.println("ERROR: Failed to fetch data from db: " + e.getMessage());
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
      System.out.println("ERROR: Failed to fetch data from db: " + e.getMessage());
    }
    return false;
  }

  // Id Calculations
  public String genUserId() {
    String id;
    try {
      ArrayList<String> ids = exportinfo(dbFiles.USERS);
      Collections.sort(ids);
      String last = ids.isEmpty() ? "0" : ids.get(ids.size() - 1);
      if (Misc.isIntegar(last)) {
        id = String.format("%05d", Integer.parseInt(last) + 1);
        return "UX" + id;
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to generate id: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed to generate id" + er.getMessage());
    }
    return null;
  }

  public String genAdminId() {
    String id;
    try {
      ArrayList<String> ids = exportinfo(dbFiles.ADMINS);
      Collections.sort(ids);
      String last = ids.isEmpty() ? "0" : ids.get(ids.size() - 1);
      if (Misc.isIntegar(last)) {
        id = String.format("%05d", Integer.parseInt(last) + 1);
        return "AX" + id;
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to generate id: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed to generate id" + er.getMessage());
    }
    return null;
  }

  public String genSurveyCreatorId() {
    String id;
    try {
      ArrayList<String> ids = exportinfo(dbFiles.SURVEY_CREATORS);
      Collections.sort(ids);
      String last = ids.isEmpty() ? "0" : ids.get(ids.size() - 1);
      if (Misc.isIntegar(last)) {
        id = String.format("%05d", Integer.parseInt(last) + 1);
        return "SC" + id;
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to generate id: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed to generate id" + er.getMessage());
    }
    return null;
  }

  public String genSurveyId() {
    String id;
    try {
      ArrayList<String> ids = exportinfo(dbFiles.SURVEYS);
      Collections.sort(ids);
      String last = ids.isEmpty() ? "0" : ids.get(ids.size() - 1);
      if (Misc.isIntegar(last)) {
        id = String.format("%05d", Integer.parseInt(last) + 1);
        return "SX" + id;
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to generate id: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed to generate id" + er.getMessage());
    }
    return null;
  }

  public String genReviewId() {
    String id;
    try {
      ArrayList<String> ids = exportinfo(dbFiles.REVIEWS);
      Collections.sort(ids);
      String last = ids.isEmpty() ? "0" : ids.get(ids.size() - 1);
      if (Misc.isIntegar(last)) {
        id = String.format("%05d", Integer.parseInt(last) + 1);
        return "RX" + id;
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to generate id: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed to generate id" + er.getMessage());
    }
    return null;
  }

  public String genQuestionId() {
    String id;
    try {
      ArrayList<String> ids = exportinfo(dbFiles.QUESTIONS);
      Collections.sort(ids);
      String last = ids.isEmpty() ? "0" : ids.get(ids.size() - 1);
      if (Misc.isIntegar(last)) {
        id = String.format("%05d", Integer.parseInt(last) + 1);
        return "QX" + id;
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to generate id: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed to generate id" + er.getMessage());
    }
    return null;
  }

  public String genResponseId() {
    String id;
    try {
      ArrayList<String> ids = exportinfo(dbFiles.RESPONSES);
      Collections.sort(ids);
      String last = ids.isEmpty() ? "0" : ids.get(ids.size() - 1);
      if (Misc.isIntegar(last)) {
        id = String.format("%05d", Integer.parseInt(last) + 1);
        return "RS" + id;
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to generate id: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed to generate id" + er.getMessage());
    }
    return null;
  }

  // loading wrappers
  public static Map<String, Person> loadPerson() {
    Map<String, Person> userMap = new HashMap<String, Person>();
    try {
      ArrayList<Person> data = exporter(dbFiles.PERSON);
      for (Person u : data) {
        userMap.put(u.getNid() + "," + u.getFullname() + "," + u.getUsername(), u);
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to process data: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed to process data: " + er.getMessage());
    }
    return userMap;
  }

  public static Map<String, User> loadUser() {
    Map<String, User> userMap = new HashMap<String, User>();
    try {
      ArrayList<User> data = exporter(dbFiles.USERS);

      for (User u : data) {
        userMap.put("NID: " + u.getNid() + " Fullname: " + u.getFullname() + " Username: " + u.getUsername()
            + " UserId: " + u.getUserId(), u);
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to process data: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed to process data: " + er.getMessage());
    }
    return userMap;
  }

  public static Map<String, Admin> loadAdmin() {
    Map<String, Admin> adminMap = new HashMap<String, Admin>();
    try {
      ArrayList<Admin> data = exporter(dbFiles.ADMINS);

      for (Admin u : data) {
        adminMap.put("NID: " + u.getNid() + " Fullname: " + u.getFullname() + " Username: " + u.getUsername()
            + " AdminId: " + u.getAdminId(), u);
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to process data: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed to process data: " + er.getMessage());
    }
    return adminMap;
  }

  public static Map<String, SurveyCreator> loadSurveyCreator() {
    Map<String, SurveyCreator> scMap = new HashMap<String, SurveyCreator>();
    try {
      ArrayList<SurveyCreator> data = exporter(dbFiles.SURVEY_CREATORS);

      for (SurveyCreator sc : data) {
        scMap.put(sc.getNid() + sc.getUsername() + sc.getScId() + sc.getFullname(), sc);
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to process data: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed to process data: " + er.getMessage());
    }
    return scMap;
  }

  public static Map<String, Survey> loadSurvey() {
    Map<String, Survey> sMap = new HashMap<String, Survey>();
    try {
      ArrayList<Survey> data = exporter(dbFiles.SURVEYS);

      for (Survey s : data) {
        sMap.put(s.getSurveyId() + "," + s.getScId(), s);
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to process data: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed to process data: " + er.getMessage());
    }
    return sMap;
  }

  public static Map<String, Review> loadReview() {
    Map<String, Review> rMap = new HashMap<String, Review>();
    try {
      ArrayList<Review> data = exporter(dbFiles.REVIEWS);

      for (Review r : data) {
        rMap.put(r.getRevId() + "," + r.getUserId(), r);
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to process data: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed to process data: " + er.getMessage());
    }
    return rMap;
  }

  public static Map<String, Question> loadQuestion() {
    Map<String, Question> qMap = new HashMap<String, Question>();
    try {
      ArrayList<Question> data = exporter(dbFiles.QUESTIONS);

      for (Question q : data) {
        qMap.put("QustionID: " + q.getQId() + "SurveyID: " + q.getSurveyId(), q);
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to process data: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed to process data: " + er.getMessage());
    }
    return qMap;
  }

  public static Map<String, Response> loadResponse() {
    Map<String, Response> qMap = new HashMap<String, Response>();
    try {
      ArrayList<Response> data = exporter(dbFiles.RESPONSES);

      for (Response q : data) {
        qMap.put(q.buildInfo(), q);
      }
    } catch (IOException e) {
      System.out.println("ERROR: Failed to process data: " + e.getMessage());
    } catch (Exception er) {
      System.out.println("ERROR: Failed to process data: " + er.getMessage());
    }
    return qMap;
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

  private static ArrayList<String> exportinfo(dbFiles type) throws IOException, Exception {
    long currentPosition = 0;
    RandomAccessFile db = FilePicker.getdbFile(type);
    ArrayList<String> id = new ArrayList<String>();

    while (currentPosition < db.length()) {
      db.seek(currentPosition);

      byte[] infobytes = new byte[RECORD_INFO_BUFFER];
      db.read(infobytes);
      int recordSize = Integer.parseInt(new String(infobytes).trim());

      currentPosition += RECORD_INFO_BUFFER;
      db.seek(currentPosition);

      StringBuilder cur = new StringBuilder();
      for (int i = 0; i < recordSize; i += 3) {
        byte[] hex = new byte[3];
        db.read(hex);
        int charv = Integer.parseInt(new String(hex), 16);
        cur.append((char) charv);
      }

      switch (type) {
        case USERS:
          User u = (User) Misc.constructPerson(Misc.destructure(cur.toString()), User.class);
          id.add(u.getUserId().substring(2));
          break;

        case ADMINS:
          Admin a = (Admin) Misc.constructPerson(Misc.destructure(cur.toString()), Admin.class);
          id.add(a.getAdminId().substring(2));
          break;

        case SURVEY_CREATORS:
          SurveyCreator sc = (SurveyCreator) Misc.constructPerson(Misc.destructure(cur.toString()),
              SurveyCreator.class);
          id.add(sc.getScId().substring(2));
          break;

        case SURVEYS:
          Survey s = Misc.construct(Misc.destructure(cur.toString()), Survey.class);
          id.add(s.getSurveyId().substring(2));
          break;

        case REVIEWS:
          Review r = Misc.construct(Misc.destructure(cur.toString()), Review.class);
          id.add(r.getRevId().substring(2));
          break;

        case QUESTIONS:
          Question q = Misc.construct(Misc.destructure(cur.toString()), Question.class);
          id.add(q.getQId().substring(2));
          break;

        case RESPONSES:
          Response rs = Misc.construct(Misc.destructure(cur.toString()), Response.class);
          id.add(rs.getResponseId().substring(2));
          break;

        default:
          break;
      }

      currentPosition += recordSize;
    }
    return id;

  }

  @SuppressWarnings("unchecked")
  private static <T> ArrayList<T> exporter(dbFiles type) throws IOException, Exception {
    long currentPosition = 0;
    RandomAccessFile db = FilePicker.getdbFile(type);
    ArrayList<T> obj = new ArrayList<T>();

    while (currentPosition < db.length()) {
      db.seek(currentPosition);

      byte[] infobytes = new byte[RECORD_INFO_BUFFER];
      db.read(infobytes);
      int recordSize = Integer.parseInt(new String(infobytes).trim());

      currentPosition += RECORD_INFO_BUFFER;
      db.seek(currentPosition);

      StringBuilder cur = new StringBuilder();
      for (int i = 0; i < recordSize; i += 3) {
        byte[] hex = new byte[3];
        db.read(hex);
        int charv = Integer.parseInt(new String(hex), 16);
        cur.append((char) charv);
      }

      switch (type) {
        case PERSON:
          Person p = Misc.constructPerson(Misc.destructure(cur.toString()), Person.class);
          obj.add((T) p);
          break;

        case USERS:
          User u = (User) Misc.constructPerson(Misc.destructure(cur.toString()), User.class);
          obj.add((T) u);
          break;

        case ADMINS:
          Admin a = (Admin) Misc.constructPerson(Misc.destructure(cur.toString()), Admin.class);
          obj.add((T) a);
          break;

        case SURVEY_CREATORS:
          SurveyCreator sc = (SurveyCreator) Misc.constructPerson(Misc.destructure(cur.toString()),
              SurveyCreator.class);
          obj.add((T) sc);
          break;

        case SURVEYS:
          Survey s = Misc.construct(Misc.destructure(cur.toString()), Survey.class);
          obj.add((T) s);
          break;

        case REVIEWS:
          Review r = Misc.construct(Misc.destructure(cur.toString()), Review.class);
          obj.add((T) r);
          break;

        case QUESTIONS:
          Question q = Misc.construct(Misc.destructure(cur.toString()), Question.class);
          obj.add((T) q);
          break;

        case RESPONSES:
          Response rs = Misc.construct(Misc.destructure(cur.toString()), Response.class);
          obj.add((T) rs);
          break;

        default:
          break;
      }

      currentPosition += recordSize;
    }
    return obj;

  }

}
