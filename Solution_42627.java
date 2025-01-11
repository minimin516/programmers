import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public int solution(int[][] jobs) {
        int answer = 0;
        int size = jobs.length;
        Queue<Job> queue = new PriorityQueue<>();

        Arrays.sort(jobs, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o1[0] - o2[0];
            }
        });

        int processedCount = 0;
        int requestedCount = 0;
        int now = 0;
        while (processedCount < jobs.length) {
            for (int i = requestedCount; i < jobs.length; i++) {
                int requestedTime = jobs[i][0];
                int workingTime = jobs[i][1];

                if (requestedTime <= now) {
                    queue.add(new Job(requestedTime, workingTime));
                    requestedCount++;
                    continue;
                }

                break;
            }

            if (queue.isEmpty()) {
                now++;
                continue;
            }


            Job job = queue.poll();

            now += job.workingTime;
            answer += now - job.requestedTime;

            processedCount++;
        }

        return answer / size;
    }

    class Job implements Comparable<Job> {
        int requestedTime;
        int workingTime;

        public Job(int requestedTime, int workingTime) {
            this.requestedTime = requestedTime;
            this.workingTime = workingTime;
        }

        @Override
        public int compareTo(Job o) {
            if (this.workingTime == o.workingTime) {
                return this.requestedTime - o.requestedTime;
            }
            return this.workingTime - o.workingTime;
        }
    }

}