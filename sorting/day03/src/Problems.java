import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        // TODO

        for(int i = 0; i<A.length; i++)
            A[i] += 100;
        RadixSort.radixSort(A, 10);
        for(int i = 0; i<A.length; i++)
            A[i] -= 100;

    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }





    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        //System.out.println("Sorting with base of: " + b + " and index digit of: " + n);
        LinkedList<String>[] L = new LinkedList[26];
        for (int i = 0; i < 26; i++)
            L[i] = new LinkedList<>();


        for (String i : A) {
            // TODO: Extract the relevant digit from i, and add i to the corresponding Linked List.
            //System.out.println("Current value is: " + i);
            L[getNthCharacter(i, n)].add(i);
        }
        int j = 0; // index in A to place numbers
        for (LinkedList<String> list : L) {
            // TODO: Put all numbers in the linked lists into A
            ///System.out.println(list.toString());
            while(!list.isEmpty()){
                A[j] = list.removeFirst();
                j ++;
            }
        }
    }



    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        for(int i = 0; i< stringLength; i++){
            countingSortByCharacter(S, i);
        }
    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        // TODO
        return 0;
    }

}
