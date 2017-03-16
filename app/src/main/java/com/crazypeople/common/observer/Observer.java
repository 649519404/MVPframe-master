package com.crazypeople.common.observer;

public interface Observer<T> {
    void onUpdateUser(LoginObserver<T> observable,T data);
}