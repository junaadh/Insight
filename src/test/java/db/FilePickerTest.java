package db;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;

import db.FilePicker.dbFiles;

public class FilePickerTest {

  @Test
  public void testGetDb() {
    String dirdb = FilePicker.getDbDir();
    File dir = new File(dirdb);

    assertNotNull(dir);
    assertTrue(dir.exists());
    assertTrue(dir.isDirectory());
  }

  @Test
  public void testGetPersonDb() throws IOException {
    RandomAccessFile db = FilePicker.getdbFile(dbFiles.PERSON);

    assertNotNull(db);
  }

  @Test
  public void testGetUserDb() throws IOException {
    RandomAccessFile db = FilePicker.getdbFile(dbFiles.USERS);

    assertNotNull(db);
  }

  @Test
  public void testGetAdminDb() throws IOException {
    RandomAccessFile db = FilePicker.getdbFile(dbFiles.ADMINS);

    assertNotNull(db);
  }

  @Test
  public void testGetSurveyCreatorDb() throws IOException {
    RandomAccessFile db = FilePicker.getdbFile(dbFiles.SURVEY_CREATORS);

    assertNotNull(db);
  }

  @Test
  public void testGetSurveyDb() throws IOException {
    RandomAccessFile db = FilePicker.getdbFile(dbFiles.SURVEYS);

    assertNotNull(db);
  }

  @Test
  public void testGetReviewsDb() throws IOException {
    RandomAccessFile db = FilePicker.getdbFile(dbFiles.REVIEWS);

    assertNotNull(db);
  }

  @Test
  public void testGetQuestionsDb() throws IOException {
    RandomAccessFile db = FilePicker.getdbFile(dbFiles.QUESTIONS);

    assertNotNull(db);
  }

  @Test
  public void testGetResponsesDb() throws IOException {
    RandomAccessFile db = FilePicker.getdbFile(dbFiles.RESPONSES);

    assertNotNull(db);
  }
}
