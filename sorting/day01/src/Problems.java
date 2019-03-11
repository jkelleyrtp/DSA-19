import java.util.*;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];

        // Establish the numbers on the left side of the median
        PriorityQueue<Integer> top_cut = minPQ();

        // Establish the numbers on the right side of the median
        PriorityQueue<Integer> bottom_cut = maxPQ();



        double last_median = -99999;

        // Iterate through the input stream
        for(int i = 0; i< inputStream.length; i++){
            int current_val = inputStream[i];

            // Check if we need to add our value to the top stack
            if(current_val>last_median){
                // Check if we need to readjust the arrays
                if( top_cut.size() > bottom_cut.size()){
                    bottom_cut.offer(top_cut.peek());
                    top_cut.poll();
                }
                top_cut.add(inputStream[i]);
            }

            // Also check if we need to add our value to the bottom stack
            if(current_val<=last_median){
                if( top_cut.size() < bottom_cut.size()){
                    top_cut.offer(bottom_cut.peek());
                    bottom_cut.poll();
                }
                bottom_cut.add(inputStream[i]);
            }


            runningMedian[i] =
                    top_cut.size() == bottom_cut.size() ? (top_cut.peek() + bottom_cut.peek())/2.0
                                    : (top_cut.size() > bottom_cut.size() ? top_cut.peek() : bottom_cut.peek());
            last_median = runningMedian[i];
            System.out.println(last_median);


        }

        return runningMedian;
    }

}
