import java.util.ArrayList;
import java.util.List;

public class RadioTowers {
    static class Tower {
        double x, y;
        Tower(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static double getDist(Tower t1, Tower t2) {
        double xDiff = t1.x - t2.x;
        double yDiff = t1.y - t2.y;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    private static boolean isWithin(Tower t1, Tower t2, int dist) {
        return getDist(t1, t2) <= dist;
    }

    // Strip contains a list of Towers sorted by x-coordinate, whose y-coordinates all fall in a 2-mile strip
    // Return true if no two towers are within 1 mile
    public static boolean checkStrip(List<Tower> strip) {
        // TODO
        return check(strip, 0, 1);
    }

    // Return true if no two towers are within distance 1 of each other
    public static boolean validTowers(List<Tower> Lx, List<Tower> Ly) {
        assert Lx.size() == Ly.size();

        ///int front = 1;

        //List<RadioTowers.Tower> bad_pairs = new ArrayList<>(towers);
        

        //int back = 0;


        return check(Lx, 0, 1);
    }


    public static boolean check(List<Tower> Lx, int idx_tower1, int idx_tower2){
        Tower tower1 = Lx.get(idx_tower1);
        Tower tower2 = Lx.get(idx_tower2);
        System.out.println("\nChecking towers: "+idx_tower1 + " and "+idx_tower2);
        System.out.println("Checking towers, a ("+tower1.x + " , "+tower1.y+") | b ("+tower2.x + " , "+tower2.y +")");
        System.out.println("Distance between two: "+getDist(tower1,tower2));


        // Check if the two towers share the same dynamic slice
        if( Math.abs(tower1.x - tower2.x) <= 1){
            // Check if they are within 1 km   each other
            if(getDist(tower1, tower2) <=1){
                System.out.println("Towers are too close");
                return false;

            } else {
                // They share the same x but are far apart, so lets check the current tower against the next one if it exists
                System.out.println("Sharing similar x's ");
                if(idx_tower2+1 < Lx.size()) {
                    return (check(Lx, idx_tower1, idx_tower2 + 1));
                }
                // This is the last pair in the list
                return true;
            }
        } else{
            if(idx_tower2-1 > idx_tower1){
                idx_tower1 = idx_tower2 -1;
                return(check(Lx, idx_tower1, idx_tower2));}

            if(idx_tower2+1 < Lx.size())
                return(check(Lx, idx_tower1+1, idx_tower2+1));
            return true;
        }
    }
}
