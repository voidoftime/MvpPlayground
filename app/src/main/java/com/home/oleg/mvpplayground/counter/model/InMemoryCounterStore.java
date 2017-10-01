package com.home.oleg.mvpplayground.counter.model;


public class InMemoryCounterStore implements CounterStore {
    private int value = 0;

    @Override
    public void clear() {
        value = 0;
    }

    @Override
    public void increase() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
