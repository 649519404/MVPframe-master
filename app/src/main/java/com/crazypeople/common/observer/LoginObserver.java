package com.crazypeople.common.observer;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by 曲志强 on 2017/3/15.
 */

public class LoginObserver<T> {

    List<Observer<T>> mObservers=new ArrayList<Observer<T>>();

    public void register(Observer<T> observer) {
        if (observer == null) {
            throw new NullPointerException("observer == null");
        }
        synchronized (this) {
            if (!mObservers.contains(observer))
                mObservers.add(observer);
        }
    }

    public synchronized void unregister(Observer<T> observer) {
        mObservers.remove(observer);
    }

    public void notifyObservers(T data) {
        for (Observer<T> observer : mObservers) {
            observer.onUpdateUser(this, data);
        }
    }

}
