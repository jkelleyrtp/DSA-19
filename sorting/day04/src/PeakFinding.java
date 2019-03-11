public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }
    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }
    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }

    public static int findOneDPeak(int[] nums) {
        int current_n = (int) nums.length - nums.length/2;
        int current_state = peakOneD(current_n, nums);
        int break_length = 0;

        int low = 0;
        int high = nums.length;


        while (current_state != 0 && break_length++ <= nums.length){
            int mid = low + (high - low)/2;  /* (low + high)/2 */
            System.out.println(mid);
            System.out.println(peakOneD(current_n, nums) + "\n");

            if(mid == nums.length-1){
                return nums.length-1;
            }
            if(mid == 1 && nums[0]>nums[1]){
                return 0;
            }

            switch (peakOneD(current_n, nums)) {
                case -1:
                    high = mid;

                    break;

                case 1:
                    low = mid;
                    break;

                case 0:
                    return current_n;
            }

        }
        return current_n;
    }

    public static int[] findTwoDPeak(int[][] nums) {
        int loX = 0;
        int hiX = nums[0].length;
        int loY = 0;
        int hiY = nums.length;
        boolean middleColumn = true;


        while (hiX > loX && hiY > loY) {
            if (middleColumn) {
                int midX = (hiX + loX) / 2;
                int yMax = maxYIndex(midX, loY, hiY, nums);
                int peak = peakX(midX, yMax, nums);

                switch(peak){
                    case 0:
                        // Peak found
                        return new int[]{yMax, midX};
                    case -1:
                        // Peak is to the left, split left
                        hiX = midX;
                        break;
                    case 1:
                        // Peak is to the right, split right
                        loX = midX +1;
                        break;
                }

            } else {
                int midY = (hiY + loY) / 2;
                int xMax = maxXIndex(midY, loX, hiX, nums);
                int peak = peakY(xMax, midY, nums);

                switch(peak){
                    case 0:
                        // Peak found
                        return new int[]{midY, xMax};
                    case -1:
                        // Peak is to the left, split left
                        hiY = midY;
                        break;
                    case 1:
                        // Peak is to the right, split right
                        loY = midY + 1;
                        break;

                }
            }
            middleColumn = !middleColumn;
        }
        return null;
    }



}
