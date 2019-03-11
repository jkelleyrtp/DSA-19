import java.util.Arrays;

public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        int start;
        int end;
        int triples = 0;
        int max_counter = 2 * arr.length * arr.length;
        int escape = 0;
        System.out.println("Input sum is: " + sum);

        for(int i = 0; i< arr.length; i++){
            start = i == 0 ? 1 : 0;
            end =  i == arr.length-1 ? arr.length-2 : arr.length-1;

            while(start < end && escape < max_counter){
                System.out.println("");
                System.out.println("Start: " + start + "  End: " + end);
                int current_sum = arr[start] + arr[end] + arr[i];
                System.out.println("Input values are: " +  arr[start] + ", " + arr[end] +", "+ arr[i] +" and current sum is: " + current_sum);


                if(start == i) start++;
                if(end == i) end--;

                if( current_sum == sum) {
                    triples ++;
                    //start ++;
                    end--;
                    System.out.println("Triple found! start_value: " + arr[start] + "  end_value: " + arr[end] + " sum: " + current_sum);

                } else if (current_sum < sum){
                    start ++;
                } else{
                    end --;
                }
                escape +=1 ;
            }



        }
        System.out.println("\nTotal number of triples matched: " + triples);
        return (triples+1)/3;
    }
}
