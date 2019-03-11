import java.util.ArrayList;
import java.util.Arrays;

public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        if(rightChild(i) >= size ){
            // Case where both children don't exist
            if (leftChild(i) >= size){
                //System.out.println("Bad sink");
                return;
            }
            // Case where left child exists
            if(heap[leftChild(i)]>=heap[i]) {
                int current = heap[i];
                heap[i] = heap[leftChild(i)];
                heap[leftChild(i)] = current;
            }
            return;
        }

        int max = (heap[leftChild(i)] >= heap[rightChild(i)]) ? leftChild(i) : rightChild(i);
        int current = heap[i];

        if(heap[max] >= heap[i]) {
            heap[i] = heap[max];
            heap[max] = current;
            sink(max);
        }
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;
        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);
        if(size == 0)
            return heap;

        if(size == 1)
            return heap;

        if(size == 2){
            if(heap[1] < heap[0]) {
                int temp = heap[0];
                heap[0] = heap[1];
                heap[1] = temp;
            }
            return heap;
        }


        System.out.println(Arrays.toString(heap));

        for (int i=size-1; i>0; i--) {
            int x0 = heap[0];
            heap[0] = heap[i];
            heap[i] = x0;
            size --;
            sink(0);
        }
        //sink(0);
        return heap;
    }
}
