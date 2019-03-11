import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    private void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i+1);
            swap(array, i, randIndex);
        }
    }

    /**
     * TODO
     * Best-case runtime: nlogn
     * Worst-case runtime: n^2
     * Average-case runtime: nlogn
     *
     * Space-complexity: logn
     */
    @Override
    public int[] sort(int[] array) {
        // TODO: Sort the array. Make sure you avoid the O(N^2) runtime worst-case
        shuffleArray(array);
        System.out.println(array);
        quickSort(array, 0, array.length-1);
        return array;
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int lo, int hi) {
        //System.out.println(Arrays.toString(a));
        // Terminating condition is when lo = hi
        if (lo < hi) {
            int p_index = partition(a, lo, hi);
            quickSort(a, lo, p_index-1);
            quickSort(a, p_index+1, hi);
        }
    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int lo, int hi) {
        int pivot = array[lo];
        int i = lo+1; // index of lower element

        for (int j=lo +1; j<=hi; j++) {
            if (array[j] < pivot) {
                swap(array, i, j);
                i++;

            }
        }

        swap(array, lo, i-1);
        return i-1;
    }

}
