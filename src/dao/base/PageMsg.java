package dao.base;

/**
 * Package: dao.base
 * FileName: PageMsg
 * Date: on 2018/5/24  下午5:34
 * Auther: Wally
 * Descirbe:计算前端发送过来的分页请求参数，分页需要用到的信息
 */
public class PageMsg {
    private int startIndex;
    private int count;

    public PageMsg(int startIndex, int count) {
        this.startIndex = startIndex;
        this.count = count;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}