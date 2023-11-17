package db;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FilePicker {

  public enum dbFiles {
    PERSON("person.bin"),
    USERS("users.bin"),
    ADMINS("admins.bin"),
    SURVEY_CREATORS("surveycreators.bin"),
    SURVEYS("surveys.bin"),
    REVIEWS("reviews.bin"),
    QUESTIONS("questions.bin");

    private final String fileName;

    dbFiles(String filename) {
      this.fileName = filename;
    }

    public String getFilename() {
      return fileName;
    }
  }

  public static String getDbDir() {
    String root = System.getProperty("user.dir");
    String dbDir = root + "/database";

    File dir = new File(dbDir);

    if (!dir.exists()) {
      try {
        dir.mkdir();
      } catch (SecurityException e) {
        System.out.println("Error creating directory: " + e.getMessage());
      }
    }

    return dbDir + "/";
  }

  public static RandomAccessFile getdbFile(dbFiles type) throws IOException {
    String dbDir = getDbDir();
    String filename = type.getFilename();
    File db = new File(dbDir + filename);

    if (!(db.exists())) {
      try {
        db.createNewFile();
        db.setReadable(true);
        db.setWritable(true);
      } catch (IOException e) {
        System.out.println("Error creating db: " + e.getMessage());
      }
    }

    RandomAccessFile rdb = new RandomAccessFile(db, "rw");
    return rdb;
  }

}
