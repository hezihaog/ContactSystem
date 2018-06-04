package constant;

/**
 * Package: constant
 * FileName: ContactSystemConstant
 * Date: on 2018/5/21  下午3:48
 * Auther: Wally
 * Descirbe:常量类
 */
public class ContactSystemConstant {
    /**
     * 请求参数Key
     */
    public static final class ParamsKey {
        //用户Id
        public static final String userId = "userId";
        //联系人Id
        public static final String contactId = "contactId";
        //联系人姓名
        public static final String name = "name";
        //联系人性别
        public static final String gender = "gender";
        //联系人年龄
        public static final String age = "age";
        //联系人手机号
        public static final String phone = "phone";
        //联系人电子邮箱
        public static final String email = "email";
        //联系人qq
        public static final String qq = "qq";
    }

    /**
     * 传递数据时使用的Key
     */
    public static final class DataKey {
        public static final String KEY_USER_ID = "userId";
        public static final String KEY_CONTACT_ID = "contactId";
        public static final String KEY_CONTACT_NAME = "contactName";
    }
}