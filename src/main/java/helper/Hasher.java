package helper;

import org.mindrot.jbcrypt.BCrypt;

public class Hasher {
  public static String genSalted(String password) {
    String salt = BCrypt.gensalt(12);
    return BCrypt.hashpw(password, salt);
  }

  public static boolean verifyHash(String candidate, String hashed) {
    return BCrypt.checkpw(candidate, hashed);
  }
}
