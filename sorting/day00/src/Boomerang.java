import java.util.HashMap; // import the HashMap class



public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        int num_boomerangs = 0;
        for(int i = 0; i<points.length; i++){
            HashMap<Integer, Integer> pair_counts = new HashMap<>();
            for(int j = 0; j<points.length; j++) {
                double dist = Math.pow((points[j][0] - points[i][0]) ,2) + Math.pow((points[j][1] - points[i][1]) ,2);
                if (dist != 0) {
                    if (pair_counts.containsKey((int) dist)) {
                        pair_counts.put((int) dist, pair_counts.get((int)dist)+1);
                    } else{
                        pair_counts.put((int) dist, 1);
                    }
                }
            }

            // Use the formula num_boomerangs = num_pairs * (num_pairs-1)
            for (Integer value : pair_counts.values()) {
                num_boomerangs += value * (value - 1);
            }


        }

        return num_boomerangs;
    }
}

