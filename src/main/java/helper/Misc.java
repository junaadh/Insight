package helper;

import java.util.regex.Pattern;

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

  public static boolean isDateString(String str) {
    // sets up date template
    // basically (0-9 | 10 - 19 | 20 - 29 | 30 - 31)/(0-9 | 10 - 12)/(yyyy)
    // so matches dd/MM/yyyy
    String match = "^(0[1-9]|1\\d|2\\d|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$";
    Pattern template = Pattern.compile(match);

    // string is bascially char[]
    return template.matcher(str).matches();
  }
  
}
