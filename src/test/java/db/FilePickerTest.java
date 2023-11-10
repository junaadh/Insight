package db;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

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
  public void testGetUserDb() {
    File db = FilePicker.getdbFile(dbFiles.USERS);

    assertNotNull(db);
    assertTrue(db.exists());
    assertTrue(db.isFile());
    assertTrue(db.canRead());
    assertTrue(db.canWrite());
  }

  @Test
  public void testGetAdminDb() {
    File db = FilePicker.getdbFile(dbFiles.ADMINS);

    assertNotNull(db);
    assertTrue(db.exists());
    assertTrue(db.isFile());
    assertTrue(db.canRead());
    assertTrue(db.canWrite());
  }

  @Test
  public void testGetSurveyCreatorDb() {
    File db = FilePicker.getdbFile(dbFiles.SURVEY_CREATORS);

    assertNotNull(db);
    assertTrue(db.exists());
    assertTrue(db.isFile());
    assertTrue(db.canRead());
    assertTrue(db.canWrite());
  }

  @Test
  public void testGetSurveyDb() {
    File db = FilePicker.getdbFile(dbFiles.SURVEYS);

    assertNotNull(db);
    assertTrue(db.exists());
    assertTrue(db.isFile());
    assertTrue(db.canRead());
    assertTrue(db.canWrite());
  }

  @Test
  public void testGetReviewsDb() {
    File db = FilePicker.getdbFile(dbFiles.REVIEWS);

    assertNotNull(db);
    assertTrue(db.exists());
    assertTrue(db.isFile());
    assertTrue(db.canRead());
    assertTrue(db.canWrite());
  }
}
