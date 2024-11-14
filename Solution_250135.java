import java.util.*;

class Solution_250135 {
   public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = -1;
       
        int start = parseToTime(h1, m1, s1);
        int end = parseToTime(h2, m2, s2);

        answer = cntAlrams(end) - cntAlrams(start);
        answer += alramZero(start) ? 1 : 0; // 0시 0분 0초 or 12시 0분 0초를 위한 카운팅

        return answer;
    }

    private int parseToTime(int hour, int minute, int second) {
        return (hour*60*60) + (minute*60) + second;
    }

    private int cntAlrams(int seconds) {
        int minuteAlrams = seconds*59/(60*60); //60초 제외
        int hourAlrams = seconds*719/(12*60*60); //정각 제외
        int dupAlrams = (12*60*60) <= seconds ? 2 : 1;

        return minuteAlrams + hourAlrams - dupAlrams;
    }

    private boolean alramZero(int seconds) {
        return seconds == 0 || seconds == 43200;
    }
}