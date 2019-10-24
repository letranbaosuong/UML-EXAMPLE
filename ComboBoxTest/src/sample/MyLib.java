package sample;

import java.util.List;

public class MyLib {
    public static boolean isInt(String s) {
        try {
            int n = Integer.parseInt(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static int getInt(String s) {
        try {
            int n = Integer.parseInt(s);
            return n;
        } catch (Exception e) {
            return 0;
        }
    }

    public static float getFloat(String s) {
        try {
            float f = Float.parseFloat(s);
            return f;
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean isInList(String s, List<String> strList) {
        for (String ss : strList) {
            if (ss.equals(s)) return true;
        }
        return false;
    }
}
