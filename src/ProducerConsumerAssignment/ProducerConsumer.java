package ProducerConsumerAssignment;


/*
    Author: William Noonan
            CSCI 5362 Operating Systems
            Producer Consumer Assignment

 */


/*
  ProducerConsumer.java

  Demonstrates solution to producer-consumer problem using semaphores and mutex lock.

  Usage:
       java ProducerConsumer <sleep time> <number of producer threads> <number of consumer threads>
  where
       sleep time is how long to sleep (seconds) before terminating

 */



import java.util.Random;

public class ProducerConsumer {
    // I'm pretty sure RAND_MAX is a C or C++ thing, so I'll make my own
    static final int RAND_MAX = 100;  // assume it's this value

    static final int SLEEP_MAX = 8;  // max thread sleep time

    // initialize the buffer
    static BoundedBuffer<Integer> buffer = new BoundedBuffer<>();


    public static int getRandomInt(int max) {
        // random int generator
        Random random = new Random();
        return random.nextInt(max);
    }

    static class Producer extends Thread {
        // static class for a producer thread

        public void run() {
            try {
                while (true) {
                    // Producer sleeps
                    Thread.sleep(getRandomInt(SLEEP_MAX) * 1000);
                    // then inserts item into buffer
                    buffer.insertItem(getRandomInt(RAND_MAX));

                }
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }

    static class Consumer extends Thread {
        // static class for a consumer thread

        public void run() {
            try {
                while (true) {
                    // Consumer sleeps
                    Thread.sleep(getRandomInt(SLEEP_MAX) * 1000);
                    // then removes item from buffer
                    buffer.removeItem();
                }
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }

    public static void main(String [] args) throws InterruptedException {
        // input arg length check
        if (args.length != 3) {
            System.err.println("Usage: java MySemaphores_v2 <sleep time> " +
                    "<number of producer threads> " +
                    "<number of consumer threads>");
            System.exit(0);
        }

        // input arg parsing
        int sleep_time = Integer.parseInt(args[0]);
        int num_producer = Integer.parseInt(args[1]);
        int num_consumer = Integer.parseInt(args[2]);

        // create and start the producer threads
        for (int i = 0; i < num_producer; i++)
            new Producer().start();

        // create and start the consumer threads
        for (int i = 0; i < num_consumer; i++)
            new Consumer().start();

        // Main sleeps then terminates the program
        Thread.sleep(sleep_time * 1000);
        System.out.println("Main has awoken, exiting program.");
        System.exit(0);
    }
}
