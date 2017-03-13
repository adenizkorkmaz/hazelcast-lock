package com.hazelcast.challange;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HazelcastLockApplication {

    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            Runnable worker = new PrintOperation(hazelcastInstance);
            executorService.execute(worker);
        }

        executorService.shutdown();
    }
}
