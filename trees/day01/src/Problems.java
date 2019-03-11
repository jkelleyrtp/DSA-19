import java.util.Arrays;

public class Problems {

    public static int leastSum(int[] A) {
        if(A.length ==0)
            return 0;

        countingSort(A);
        String num1 = "";
        String num2 = "";
        if(A.length % 2 != 0){
            num1 = "0";
        }
        for(int i = 0; i<A.length; i+=2){
            num1 += A[i];
            if(i+1<A.length)
                num2 += A[i+1];
        }


        return (num1=="" ? 0 : Integer.parseInt(num1))  +  (num2=="" ? 0 : Integer.parseInt(num2));
    }

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

