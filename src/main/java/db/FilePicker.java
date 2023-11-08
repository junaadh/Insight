package db;

import java.io.File;

public class FilePicker {

  // private String filePath;

  public FilePicker() {
  }

  public enum dbFiles {

  }

  private File getDbDir() {
    String dbDir = "/database";

    File dir = new File(dbDir);

    if (!dir.exists()) {
      try {
        new File(dbDir).mkdir();
      } catch (SecurityException e) {
        // TODO 
        System.out.println("Hi tis a checkpoint");
      }
    }

    return dir;
  }

}
