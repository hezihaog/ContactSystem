package constant;

/**
 * Package: constant
 * FileName: CommonConstant
 * Date: on 2018/5/24  下午4:47
 * Auther: Wally
 * Descirbe:常用常量
 */
public class CommonConstant {
    /**
     * 组件的一些初始化参数
     */
    public static class Config {
        public static final String encoding = "encoding";
    }

    public static class Key {
        //页码
        public static final String page = "page";
        //每页行数
        public static final String size = "size";
    }

    /**
     * 分页使用的常量
     */
    public static class Page {
        //第一页开始的页码
        public static final int DEFAULT_START_PAGE_INDEX = 1;
        //默认一页多少条记录
        public static final int DEFAULT_PAGE_COUNT = 15;
    }
}