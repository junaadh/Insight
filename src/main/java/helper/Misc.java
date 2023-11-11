package helper;

import java.lang.reflect.Constructor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import forms.Person;

public class Misc {

  public static boolean isIntegar(String str) {
    if (str == null) {
      return false;
    }
    try {
      Integer.parseInt(str);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  public static boolean isDouble(String str) {
    if (str == null) {
      return false;
    }

    try {
      Double.parseDouble(str);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  public static boolean isEmail(String str) {
    String match = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
    Pattern template = Pattern.compile(match);

    return template.matcher(str).matches();
  }

  public static boolean isDateString(String str) {
    // sets up date template
    // basically (0-9 | 10 - 19 | 20 - 29 | 30 - 31)/(0-9 | 10 - 12)/(yyyy)
    // so matches dd/MM/yyyy
    String match = "^(0[1-9]|1\\d|2\\d|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$";
    Pattern template = Pattern.compile(match);

    // string is bascially char[]
    return template.matcher(str).matches();
  }

  public static String boolString(boolean bool) {
    return bool ? "true" : "false";
  }

  public static String[] destructure(String record) {
    String regex = ":\\d+:";
    Pattern template = Pattern.compile(regex);
    Matcher matches = template.matcher(record);

    String result = matches.replaceAll(":").substring(1);
    return result.split(":");
  }

  public static Person constructPerson(String[] info, Class<? extends Person> classname) throws Exception {
    Constructor<? extends Person> constructor = classname.getConstructor(String[].class);

    return constructor.newInstance((Object) info);
  }

  public enum prefix {
    NID("0:"),
    USERNAME(":1:"),
    FULLNAME(":2:"),
    PASSWORD(":3:"),
    AGE(":4:"),
    GENDER(":5:"),
    EMAIL(":6:"),
    PHONENO(":7:"),
    ISADMIN(":8:"),
    ISSURVEYCREATOR(":9:"),
    ADMINID(":10:"),
    USERID(":11:"),
    SCID(":12:"),
    SCDEPT(":13");

    private final String field;

    prefix(String field) {
      this.field = field;
    }

    public String getPrefix() {
      return field;
    }
  }

}
