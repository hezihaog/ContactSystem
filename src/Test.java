import java.util.LinkedHashSet;

/**
 * Package: PACKAGE_NAME
 * FileName: Test
 * Date: on 2018/5/27  下午11:28
 * Auther: Wally
 * Descirbe:
 */
public class Test {
    private static final String text = "汤梼," +
            "-DANZZY," +
            "L.," +
            "Hang.," +
            "wuli土豆," +
            "September," +
            "杨初一," +
            "sue仔," +
            "灯书," +
            "小Ding秘书," +
            "小Ding助手," +
            "Josie多多," +
            "薛寒枫," +
            "阿董," +
            "阿锴," +
            "J.Cheuk," +
            "梦," +
            "小蜜秘书," +
            "小蜜助手," +
            "邱家财✅光大享游网," +
            "香小花," +
            "任阳彬," +
            "Werkey," +
            "劉," +
            "L^-^S玫," +
            "If";

    public static void main(String[] args) {
        LinkedHashSet<String> hashSet = new LinkedHashSet<String>();
        String[] splitArr = text.split(",");
        for (String str : splitArr) {
            hashSet.add(str);
        }
        StringBuffer buffer = new StringBuffer();
        for (String str : hashSet) {
            buffer.append(str);
            buffer.append("<br/>");
        }
        //String replaceStr = text.replace(",", "<br/>");
        System.out.println(buffer.toString());
        System.out.println("总个数: " + hashSet.size());
    }
}