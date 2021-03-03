package com.pardxa.tqueue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tqueue<T> {
    private final Lock lock = new ReentrantLock();
    private final Condition isEmpty = lock.newCondition();
    private final Condition isFull = lock.newCondition();
    private int capicity;
    private Map<Integer, LinkedList<T>> items;
    private Throttle tr;

    public Tqueue(int capicity, int throttleRate) {
        this.capicity = capicity;
        items = new HashMap<>();
        tr = new Throttle(throttleRate);
    }

    public void enqueue(T item, int priority) throws InterruptedException {
        lock.lock();
        try {
            while (size() + 1 > capicity) {
                isFull.await();
            }
            if (!items.keySet().contains(priority)) {
                items.put(priority, new LinkedList<>());
            }
            items.get(priority).add(item);
            isEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public T dequeue() throws InterruptedException {
        lock.lock();
        try {
            while (size() == 0) {
                isEmpty.await();
            }
            int currentPriority = tr.getOutputPriority(items.keySet());
            T item = items.get(currentPriority).poll();
            if (items.get(currentPriority).isEmpty()) {
                items.remove(currentPriority);
            }
            if (size() < capicity) {
                isFull.signalAll();
            }
            return item;
        } finally {
            lock.unlock();
        }
    }

    private int size() {
        int size = 0;
        for (Map.Entry<Integer, LinkedList<T>> item : items.entrySet()) {
            size += item.getValue().size();
        }
        return size;
    }

}
