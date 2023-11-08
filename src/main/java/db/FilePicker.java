package db;

import java.io.File;
import java.io.IOException;

public class FilePicker {

  public enum dbFiles {
    USERS("users.bin"),
    ADMINS("admins.bin"),
    SURVEY_CREATORS("admins.bin"),
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

  private static File getDbDir() {
    String root = System.clearProperty("user.dir");
    String dbDir = root + "/database";

    File dir = new File(dbDir);

    if (!dir.exists()) {
      try {
        dir.mkdir();
      } catch (SecurityException e) {
        // TODO
        System.out.println("Error creating directory: " + e.getMessage());
      }
    }

    return dir;
  }

  public static File getdbFile(dbFiles type) {
    File dbDir = getDbDir();
    String filename = type.getFilename();
    File db = new File(dbDir, filename);

    if (!db.exists()) {
      try {
        db.createNewFile();
      } catch (IOException e) {
        System.out.println("Error creating db: " + e.getMessage());
      }
    }

    return db;
  }

}
