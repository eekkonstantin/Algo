import java.util.*;

public class BM {
  public static void main(String[] args) {
    String pattern = args[0];
    String text = args[1];

    HashMap<Character, Integer> badCharTable = buildBadChar(text, pattern);
    System.out.println("Bad Character Table: " + badCharTable.toString() + "\n");

    bm(text, pattern, badCharTable);
  }

  public static HashMap<Character, Integer> buildBadChar(String text, String pattern) {
    HashMap<Character, Integer> ret = new HashMap<>();

    char[] pat = pattern.toCharArray();
    char[] reverse = new char[pattern.length()];
    int a=0;
    for (int p=pattern.length()-1; p>-1; p--) {
      reverse[a++] = pat[p];
    }
    pattern = new String(reverse);

    for (char t : text.toCharArray()) {
      if (ret.containsKey(t))
        continue;
      int last = pattern.indexOf(t);
      ret.put(t, (last == -1 ? pattern.length() : last));
    } // O(m)
    return ret;
  }

  public static void bm(String text, String pattern, HashMap<Character, Integer> table) {
    int i = pattern.length() - 1; // text
    int j = pattern.length() - 1; // pattern

    char[] t = text.toCharArray();
    char[] p = pattern.toCharArray();

    boolean match = false;

    while (i < text.length()) {
      System.out.println(text);
      for (int x=0;x<(i - pattern.length() + 1); x++)
        System.out.print(" ");
      System.out.println(pattern);
      while (t[i] == p[j] && !match) {
        i--;
        j--;

        if (j == 0 && t[i] == p[j])
          match = true;
      }

      if (match)
        break;

      j = pattern.length() - 1;
      i += table.get(t[i]);
    }

    System.out.println("\n" + (match ? "Match found at " + i : "No match found."));

  }
}
