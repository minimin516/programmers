import java.util.Arrays;

class Solution {
    public int solution(int[] mats, String[][] park) {
        int rows = park.length;
        int cols = park[0].length;
        int maxSize = -1;

        Arrays.sort(mats);

        for (int m = mats.length - 1; m >= 0; m--) {
            boolean isPlace = false;

            for (int i = 0; i <= rows - mats[m]; i++) {
                for (int j = 0; j <= cols - mats[m]; j++) {
                    boolean isMinus1 = true;
                 
                    for (int x = i; x < i + mats[m]; x++) {
                        for (int y = j; y < j + mats[m]; y++) {
                            if (!park[x][y].equals("-1")) {
                                isMinus1 = false;
                                break;
                            }
                        }
                        if (!isMinus1) break;
                    }

                    if (isMinus1) {
                        isPlace = true;
                        break;
                    }
                }
                if (isPlace) break;
            }

            if (isPlace) {
                maxSize = mats[m];
                break;  
            }
        }

        return maxSize;
    }
}