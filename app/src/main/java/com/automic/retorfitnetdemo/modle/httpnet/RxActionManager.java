package com.automic.retorfitnetdemo.modle.httpnet;

import rx.Subscription;

/**
 * 类注释：
 * Created by sujingtai on 2017/8/10 0010 下午 2:26
 */

public interface RxActionManager<T> {
    void add(T tag, Subscription subscription);
    void remove(T tag);
    void cancel(T tag);
    void cancelAll();
}
