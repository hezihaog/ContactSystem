package entity;

import entity.base.ArrayContent;

import java.util.Collection;

/**
 * Package: entity
 * FileName: ContactList
 * Date: on 2018/5/21  下午3:40
 * Auther: Wally
 * Descirbe:
 */
public class ContactList extends ArrayContent<Contact> {
    public ContactList() {
    }

    public ContactList(Collection<? extends Contact> list) {
        super(list);
    }
}