package com.home.oleg.mvpplayground.counter.model;

public interface CounterStore {
    void clear();
    void increase();
    int get();
}
