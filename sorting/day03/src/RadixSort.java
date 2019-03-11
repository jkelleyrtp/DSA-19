import java.util.Arrays;
import java.util.LinkedList;

public class RadixSort {

    /**
     * @param n the digit number, 0 is least significant
     * @return
     */
    public static int getNthDigit(int number, int base, int n) {
        return number / ((int) Math.pow(base, n)) % base;
    }


    /**
     * Use counting sort to sort the integer array according to a digit
     *
     * @param b The base used in radix sort
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByDigit(int[] A, int b, int n) {
        System.out.println("Sorting with base of: " + b + " and index digit of: " + n);
        LinkedList<Integer>[] L = new LinkedList[b];
        for (int i = 0; i < b; i++)
            L[i] = new LinkedList<>();


        for (int i : A) {
            // TODO: Extract the relevant digit from i, and add i to the corresponding Linked List.
            //System.out.println("Current value is: " + i);
            L[getNthDigit(i, b, n)].add(i);
        }
        int j = 0; // index in A to place numbers
        for (LinkedList<Integer> list : L) {
            // TODO: Put all numbers in the linked lists into A
            ///System.out.println(list.toString());
            while(!list.isEmpty()){
                A[j] = list.removeFirst();
                j ++;
            }
        }
    }

    /**
     * Runtime: TODO: Express your runtime in terms of n, b, and w
     *
     * n: length of array
     * w: word length of integers A in base b (equal to log base b of k (log_b k) )
     *
     * @param b The base to use for radix sort
     */
    static void radixSort(int[] A, int b) {
        // Calculate the upper-bound for numbers in A
        int k = A[0] + 1;

        for (int i = 1; i < A.length; i++)
            k = (A[i] + 1 > k) ? A[i] + 1 : k;

        int w = (int) Math.ceil(Math.log(k) / Math.log(b)); // w = log base b of k, word length of numbers

        System.out.println("Numbers inputted have a w of: " + w + " and a base of: " + b);
        // TODO: Perform radix sort
        for(int i = 0; i< w; i++){
            countingSortByDigit(A, b, i);
        }
    }

    static int findMax(int[] a){
        int max = a[0];

        for(int i = 1; i<a.length; i++){
            if(a[i] > max)
                max = a[i];
        }
        return max;
    }
}
