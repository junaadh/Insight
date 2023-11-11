package helper;

public class RecordInfo {
  public int record;
  public long position;

  public RecordInfo(int record, long position) {
    this.record = record;
    this.position = position;
  }

  public RecordInfo() {
    this.record = 0;
    this.position = 0;
  }

  public int getRecord() {
    return this.record;
  }

  public long getPosition() {
    return this.position;
  }

  public boolean isNull() {
    if (this.record == 0 && this.record == 0) {
      return true;
    }
    return false;
  }
}
