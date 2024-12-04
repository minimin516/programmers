import java.util.*;
import java.util.stream.*;

class Solution {
    public String[] solution(String[][] plansArr) {
        Plan[] plans = new Plan[plansArr.length];
        for(int i = 0; i < plansArr.length; i++) {
            plans[i] = new Plan(plansArr[i]);
        }
        Arrays.sort(plans, (a, b) -> a.start - b.start); 

        Stack<Plan> stop = new Stack<>();
        List<String> answer = new ArrayList<>();
        for(int i = 0; i < plans.length - 1; i++) {
            Plan nowPlan = plans[i];
            Plan nextPlan = plans[i + 1];

            if(nowPlan.getEndTime() > nextPlan.start) {
                nowPlan.playTime = nowPlan.getEndTime() - nextPlan.start;
                stop.push(nowPlan);
                continue;
            }
            answer.add(nowPlan.name);

            int stopTime = nextPlan.start - nowPlan.getEndTime();
            
            while(stopTime > 0 && !stop.isEmpty()) {
                Plan stopPlan = stop.peek();
                int timeDiff = stopPlan.playTime - stopTime;
                stopPlan.playTime = timeDiff;
                stopTime = timeDiff * -1;
                if(timeDiff > 0) break;
                answer.add(stop.pop().name);
            }
        }

        answer.add(plans[plans.length - 1].name); 
        while(!stop.isEmpty()) answer.add(stop.pop().name);
        return answer.toArray(new String[answer.size()]);
    }
}

class Plan {
    String name;
    int start;
    int playTime;

    public Plan(String name, String start, String playTime) {
        this.name = name;
        String[] time = start.split(":");
        this.start = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]); // 시간*60+분
        this.playTime = Integer.parseInt(playTime);
    }

    public Plan(String[] plan) {
        this(plan[0], plan[1], plan[2]);
    }

    public int getEndTime() {
        return start + playTime;
    }
}