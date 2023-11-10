package db;

import java.io.File;
import java.io.IOException;

public class FilePicker {

  public enum dbFiles {
    USERS("users.bin"),
    ADMINS("admins.bin"),
    SURVEY_CREATORS("surveycreators.bin"),
    SURVEYS("surveys.bin"),
    REVIEWS("reviews.bin");

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

  public static File getdbFile(dbFiles type) {
    String dbDir = getDbDir();
    String filename = type.getFilename();
    File db = new File(dbDir + filename);

    if (!db.exists()) {
      try {
        db.createNewFile();
        db.setReadable(true);
        db.setWritable(true);
      } catch (IOException e) {
        System.out.println("Error creating db: " + e.getMessage());
      }
    }

    return db;
  }

}
