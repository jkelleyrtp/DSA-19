package your_code;


import java.util.Iterator;
import java.util.LinkedList;


public class MyPriorityQueue<T> {

    private LinkedList<T> ll;

    public MyPriorityQueue() {
        ll = new LinkedList<>();
    }

    // O(1)
    public void enqueue(T item) {
        ll.add(item);
    }

    public T dequeue() {
        return ll.removeFirst();
    }

    public boolean isEmpty() {
        return ll.isEmpty();
    }

    public T front() {
        return ll.getFirst();
    }

    public T dequeueMax(){
        int max = (int) ll.getFirst();;
        int idx_remove = 0;

        int idx = 0;

        Iterator<T> iterator = ll.iterator();
        while (iterator.hasNext()) {
            int temp = (int) iterator.next();
            if (temp > idx_remove){
                idx_remove = idx;
            }
            idx += 1;
        }
        try {
            return ll.get(idx_remove);
        } finally {
            ll.remove(idx_remove);
        }
    }

}