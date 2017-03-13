package com.hazelcast.challange;


import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class PrintOperation implements Runnable {
    private HazelcastInstance hazelcastInstance;

    public PrintOperation(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override
    public void run() {
        Lock lock = hazelcastInstance.getLock("lck_");

        try {
            if (lock.tryLock(1, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(" We are started - " + Thread.currentThread().getName());
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("We canot enter method : " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
