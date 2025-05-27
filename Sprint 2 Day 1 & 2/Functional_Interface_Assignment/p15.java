public class p15 {

}

interface StringUtils {
    static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    static String capitalize(String s) {
        if (isNullOrEmpty(s))
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
