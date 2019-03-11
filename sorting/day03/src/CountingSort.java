public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: O(n + k)
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int k = findMax(A);

        int[] sortingArray = new int[k+1];

        for(int i = 0; i<A.length; i++){
            sortingArray[A[i]]++;
        }
        int i = 0;
        for(int j = 0; j<k+1; j++){
            while(sortingArray[j] > 0){
                A[i] = j;
                sortingArray[j] --;
                i++;
            }
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



/*
def countingSort(A):
	k = findMax(A)
	counts = new int[k+1]
for e in A:
		counts[e]++
	i = 0
	for j from 0 to k+1:
		while count[j] > 0:
			A[i] = j
			count[j]--
			i++


 */