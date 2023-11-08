package db;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;

public class BinHandler {

	private static int RECORD_BUFFER = 100; // Record size of 100bytes for now; Subject to change

  private RandomAccessFile dbFile;

  protected BinHandler(String dbFilePath) throws IOException {
    File file = new File(dbFilePath);

    if (!file.exists()) {
      file.createNewFile();
    }

    dbFile = new RandomAccessFile(file, "rw");
  }

  protected void close() throws IOException {
    if (dbFile != null) {
      dbFile.close();
    }
  }

}
