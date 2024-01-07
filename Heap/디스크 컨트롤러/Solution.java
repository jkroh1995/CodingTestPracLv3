import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        //시작 시간을 기준으로 우선순위 큐
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        priorityQueue.addAll(Arrays.asList(jobs));

        // 맨 처음 시작 시간은 가장 먼저 요청이 들어온 작업
        int currentTime = priorityQueue.peek()[0];

        // 모든 일이 끝날 때까지
        while (!priorityQueue.isEmpty()) {
            // 소요 시간 기준으로 우선순위 큐
            PriorityQueue<int[]> durationPriorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

            // 현재 시간에 수행 가능한 작업이면 소요 시간 우선순위 큐에 집어넣음
            while(!priorityQueue.isEmpty()){
                if(priorityQueue.peek()[0] <= currentTime){
                    durationPriorityQueue.add(priorityQueue.poll());
                    continue;
                }
                break;
            }

            /**
             *  만일 (0,3), (4,1), (4,4) (5,1) 의 경우 첫 작업이 끝나고 currentTime이 3이 되면 나머지 작업이 duration queue에 포함되지 않음.
             *  강제로 currentTime을 초기화해주고 해당 시간에서 다시 수행 가능한 작업을 찾음.
             */
            if(durationPriorityQueue.isEmpty()){
                currentTime = priorityQueue.peek()[0];
                while(!priorityQueue.isEmpty()){
                    if(priorityQueue.peek()[0] <= currentTime){
                        durationPriorityQueue.add(priorityQueue.poll());
                        continue;
                    }
                    break;
                }
            }

            // 소요시간이 가장 짧은 일이 이번에 수행할 일
            int [] currentJob = durationPriorityQueue.poll();
            
            // 기다린 시간 + 소요 시간만큼 답에 더해줌
            answer += currentTime - currentJob[0] + currentJob[1];
            
            // 현재시간에 소요시간을 더해줌
            currentTime += currentJob[1];
            
            // duration queue에 남은 일들은 다시 시작 시간 우선 순위 큐에 넣어줌.
            while(!durationPriorityQueue.isEmpty()){
                priorityQueue.add(durationPriorityQueue.poll());
            }
        }


        return answer/jobs.length;
    }
}
