package helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface Manipulator {

  String buildInfo();

  @SuppressWarnings("unchecked")
  default String toStr() {
    StringBuilder str = new StringBuilder();

    Class<?> className = this.getClass().getSuperclass();
    Field[] fields = className.getDeclaredFields();

    for (Field field : fields) {
      field.setAccessible(true);

      try {
        Object val = field.get(this);
        String cur = null;

        if (val != null) {
          if (val instanceof ArrayList) {
            ArrayList<String> list = (ArrayList<String>) val;
            cur = '{' + String.join(",", list) + '}';
          } else if (val instanceof Map || val instanceof HashMap) {
            Map<Object, Object> map = (Map<Object, Object>) val;
            cur = '{' + mapValue(map) + '}';
          } else {
            cur = '{' + val.toString() + '}';
          }
        } else {
          cur = "";
        }

        str.append(cur).append(",");
      } catch (IllegalAccessException e) {
        e.printStackTrace();
        ;
      }
    }

    if (str.length() > 0) {
      str.setLength(str.length() - 1);
      str.append("\n");
    }

    return str.toString();
  }

  default String[] parse(String line) {
    ArrayList<String> fields = new ArrayList<String>();
    StringBuilder str = new StringBuilder();
    boolean lexInsideQuote = false;

    for (char c : line.toCharArray()) {
      if (c == '{' || c == '}') {
        lexInsideQuote = !lexInsideQuote;
      } else if (c == ',' && !lexInsideQuote) {
        fields.add(str.toString().trim());
        str.setLength(0);
      } else {
        str.append(c);
      }
    }

    fields.add(str.toString().trim());

    return fields.toArray(new String[0]);
  }

  default <K, V> ArrayList<V> valuesToList(HashMap<K, V> map) {
    return new ArrayList<V>(map.values());
  }

  default <K, V> ArrayList<V> valuesToList(Map<K, V> map) {
    return new ArrayList<V>(map.values());
  }

  default <K, V> ArrayList<Map.Entry<K, V>> keysToList(HashMap<K, V> map) {
    return new ArrayList<>(map.entrySet());
  }

  default <K, V> ArrayList<Map.Entry<K, V>> keysToList(Map<K, V> map) {
    return new ArrayList<>(map.entrySet());
  }

  default String[] getHeaders() {
    Class<?> currentClass = this.getClass();
    Class<?> superClass = currentClass.getSuperclass();

    List<String> list = new ArrayList<>();

    while (superClass != null && superClass != Object.class) {
      Field[] fields = superClass.getDeclaredFields();
      for (Field field : fields) {
        field.setAccessible(true);
        list.add(field.getName());
      }
    }

    Field[] fields = currentClass.getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      list.add(field.getName());
    }

    list.removeIf(li -> li.equals("password"));

    return list.toArray(new String[0]);
  }

  default <T, U extends Manipulator> ArrayList<U> filter(Map<T, U> map, String search) {
    ArrayList<U> filtered = new ArrayList<>();

    for (U li : map.values()) {
      if (li.buildInfo().toLowerCase().contains(search.toLowerCase())) {
        filtered.add(li);
      }
    }

    return filtered;
  }

  private <T, U> String mapValue(Map<T, U> map) {
    return map.entrySet().stream()
        .map(entry -> entry.getKey() + ":" + entry.getValue())
        .collect(Collectors.joining(","));
  }
}
