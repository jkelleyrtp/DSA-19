import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {
    public static List<List<Integer>> permutations(List<Integer> A) {
        // Initialize the array
        List<List<Integer>> permutations = new LinkedList<>();

        // Getting permutations is just a graph traversal.
        Set<Integer> empty = new HashSet<>(A);
        permutation_recrusive(new LinkedList<>(), empty, permutations);
        return permutations;


 /*       for (Integer temp : A){
            empty.add(temp);
            permutation_recrusive(new LinkedList<>(), empty, permutations);
            empty.remove(temp);

        }


        return permutations;*/
    }


    private static void permutation_recrusive(LinkedList<Integer> current, Set<Integer> remaining, List<List<Integer>> permutations){
        // Base case
        if(remaining.isEmpty()){
            permutations.add(new LinkedList<>(current));
        }

        for (int temp : new LinkedList<>(remaining)) {
            current.addLast(temp);
            remaining.remove(temp);

            permutation_recrusive(current, remaining , permutations);

            current.removeLast();
            remaining.add(temp);

        }

    }

}




