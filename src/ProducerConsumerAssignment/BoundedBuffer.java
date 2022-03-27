package ProducerConsumerAssignment;

import java.util.concurrent.Semaphore;

/**
 * Author: William Noonan
 *
 * Bounded buffer for producer-consumer problem using semaphores for synchronization.
 *
 */

@SuppressWarnings("unchecked")

public class BoundedBuffer<E>
{
    private static final int BUFFER_SIZE = 7;

    static Semaphore empty = new Semaphore(BUFFER_SIZE);  // number of permits initialized to buffer size
    static Semaphore full = new Semaphore(0);  // initially zero permits (buffer starts empty)
    static Semaphore mutex = new Semaphore(1);  // binary semaphore

    private int count, in, out;
    private E[] buffer;


    public BoundedBuffer() {
        count = 0;
        in = 0;
        out = 0;
        buffer = (E[]) new Object[BUFFER_SIZE];
    }

    // the IterruptedException is needed if acquiring semaphores, as told to me by IntelliJ
    public void insertItem(E item) throws InterruptedException {

        // acquire permit from empty semaphore then lock mutex
        empty.acquire(1);  // there are permits available if there's empty slots
        mutex.acquire();

        buffer[in] = item;
        in = (in + 1) % BUFFER_SIZE;
        count++;

        System.out.println("Producer added " + item + " to the buffer.");
        displaybuffer();
        System.out.println();

        // release the mutex lock then release one permit for full semaphore
        mutex.release();
        full.release();  // increments number of full permits available

    }

    public E removeItem() throws InterruptedException {
        E item;

        // acquire permit from full semaphore then lock mutex
        full.acquire(1);  // there are permits available if there are any slots filled
        mutex.acquire();

        item = buffer[out];
        buffer[out] = null;  // nice for printout
        out = (out + 1) % BUFFER_SIZE;
        count--;

        System.out.println("Consumer removed " + item + " from the buffer.");
        displaybuffer();
        System.out.println();

        // release the mutex lock then release one permit for empty semaphore
        mutex.release();
        empty.release();  // increments number of empty permits available

        return item;
    }

    public synchronized void displaybuffer() {
        // displays the buffer

        if ( count == 0 ) {
            System.out.println("Buffer is Empty");
            return;
        }

        System.out.print("Elements in the buffer are:");
        for (E item : buffer ) {
            if (item != null)
                System.out.print(" " + item);
        }
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
        // Testing:
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>();
        buffer.insertItem(1);
        buffer.insertItem(2);
        buffer.insertItem(3);

    }

}
