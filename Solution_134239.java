import java.util.*;
class Solution {
    static List<Integer> point=new ArrayList<>();
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        int cnt=0;
        point.add(k);
        while (k>1){
            if (k%2==0) k=k/2;
            else k=(k*3)+1;

            point.add(k);
            cnt++;
        }

        for (int i=0;i<ranges.length;i++){
            int x = ranges[i][0];
            int y = cnt+ranges[i][1];
            if(x==y) answer[i]=0.0;
            else if(x>y) answer[i]=-1.0;
            else {
                answer[i]=getDefinite(x,y);
            }
        }
        return answer;
    }

    public static double getDefinite(int x,int y){
        double sum=0.0;
        for (int i=x;i<y;i++){
            double point1 = point.get(i);
            double point2 = point.get(i + 1);
            sum+=(point1+point2)/2;
        }
        return sum;
    }
}