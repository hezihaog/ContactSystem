package entity.base;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Package: entity.base
 * FileName: ArrayContent
 * Date: on 2018/5/21  下午10:12
 * Auther: Wally
 * Descirbe:
 */
public class ArrayContent<T> extends ArrayList<T> implements IContent {
    public ArrayContent() {
    }

    public ArrayContent(Collection<? extends T> list) {
        super(list);
    }
}