package util;

/**
 * Package: util
 * FileName: TextUtil
 * Date: on 2018/5/22  上午10:43
 * Auther: Wally
 * Descirbe:
 */
public class TextUtil {
    /**
     * 是否为空
     */
    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }

    /**
     * 是否不为空
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    public static boolean isNull(CharSequence str) {
        return str == null;
    }

    public static boolean isNotNull(CharSequence str) {
        return !isNull(str);
    }

    public static boolean isAllStringNoNull(CharSequence... strs) {
        for (CharSequence str : strs) {
            if (isNull(str)) {
                return false;
            }
        }
        return true;
    }
}