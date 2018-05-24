package entity.base;

/**
 * Package: entity.base
 * FileName: ResultWithPage
 * Date: on 2018/5/24  下午6:05
 * Auther: Wally
 * Descirbe:带分页的结果
 */
public class ResultWithPage extends Result implements IPager {
    //所有页加起来的总记录数
    private int totalCount;
    //总页数
    private int totalPage = 0;

    @Override
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public int getTotalCount() {
        return this.totalCount;
    }

    @Override
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public int getTotalPage() {
        return this.totalPage;
    }
}