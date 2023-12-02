package helper;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import forms.Person;


public class Misc {
  public static boolean inResponse = false;

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

  public static String arrayListString(ArrayList<String> list) {
    String cur = String.join(",", list);
    return cur;
  }

  public static ArrayList<String> stringArrayList(String concat) {
    ArrayList<String> cur = new ArrayList<String>();
    String[] results = concat.split(",");
    cur.addAll(Arrays.asList(results));
    return cur;
  }

  public static String hashMapString(HashMap<String, String> hashmap) {
    String result = hashmap.entrySet()
        .stream()
        .map(entry -> entry.getKey() + ";" + entry.getValue())
        .collect(Collectors.joining(","));
    return result;
  }

  public static HashMap<String, String> stringHashMap(String data) {
    HashMap<String, String> hashmap = new HashMap<>();
    String[] entries = data.split(",");

    for (String entry : entries) {
      String[] key = entry.split(";");
      hashmap.put(key[0].trim(), key[1].trim());
    }
    return hashmap;
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

  public static <T> T construct(String[] info, Class<T> className) throws Exception {
    Constructor<T> constructor = className.getConstructor(String[].class);

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
    NATIONALITY(":8:"),
    ISADMIN(":9:"),
    ISSURVEYCREATOR(":10:"),
    ADMINID(":11:"),
    USERID(":12:"),
    SCID(":13:"),
    SCDEPT(":14:"),
    SURVEYID(":15:"),
    ISPUBLIC(":16:"),
    PARTICIPANTS(":17:"),
    REVID(":18:"),
    REVCONTENT(":19:"),
    DATACREATED(":20:"),
    QID(":21:"),
    ISCOMPULSORY(":22:"),
    QTEXT(":23:"),
    OPTIONS(":24:"),
    RANKED(":25:"),
    ANSWER(":26:"),
    CHOICE(":27:"),
    BOOLCHOICE(":28:"),
    RATING(":29:"),
    RESPONSES(":30:"),
    RESPONSEID(":31:"),
    QTYPE(":32:");

    private final String field;

    prefix(String field) {
      this.field = field;
    }

    public String getPrefix() {
      return field;
    }
  }
}
