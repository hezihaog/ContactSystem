package entity.base;

/**
 * Package: entity.base
 * FileName: IPager
 * Date: on 2018/5/24  下午4:45
 * Auther: Wally
 * Descirbe:
 */
public interface IPager {
    /**
     * 设置所有页的总条数
     */
    void setTotalCount(int totalCount);

    /**
     * 获取所有页的总条数
     */
    int getTotalCount();

    /**
     * 设置总页数
     */
    void setTotalPage(int totalPage);

    /**
     * 获取总页数
     */
    int getTotalPage();
}