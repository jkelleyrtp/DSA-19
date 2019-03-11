
public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 1;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime: O(nlogn)
     * Worst-case runtime: O(nlogn)
     * Average-case runtime: O(nlogn)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        int N = array.length;
        if (N <= INSERTION_THRESHOLD) return array;

        int[] a = new int[N/2];
        int[] b = new int[N - N/2];

        for (int i = 0; i < a.length; i++)
            a[i] = array[i];
        for (int i = 0; i < b.length; i++)
            b[i] = array[i + N/2];

        return merge(sort(a), sort(b));
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        // TODO
        int[] merged = new int[a.length + b.length];

        int idx_a = 0;
        int idx_b = 0;
        for(int i = 0; i<merged.length; i++){
            if (idx_a == a.length){
                merged[i] = b[idx_b];
                idx_b++;
            } else if (idx_b == b.length){
                merged[i] = a[idx_a];
                idx_a++;
            } else {
                if (a[idx_a] < b[idx_b]) {
                    merged[i] = a[idx_a];
                    idx_a++;
                } else {
                    merged[i] = b[idx_b];
                    idx_b++;
                }
            }

        }


        return merged;
    }

}
