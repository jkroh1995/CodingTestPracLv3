import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        int sum = 0;
        
        // 힙을 사용하기 위해 우선순위 큐를 사용합니다.
        // 큰 수부터 뽑아써야 하기 때문에 Collections.reverserOrder()를 활용하여 역순으로 수가 저장되도록 합니다.
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (int work : works) {
            priorityQueue.add(work);
            sum += work;
        }

        // 만일 전체 잔업량보다 남은 시간이 더 많다면 0을 반환합니다.
        if(sum <= n){
            return 0;
        }

        // 가장 많이 남은 잔업을 1시간 먼저 진행하고, 다시 우선순위 큐에 집어넣습니다.
        for (int i = 0; i < n; i++) {
            int next = priorityQueue.poll();
            next--;
            priorityQueue.add(next);
        }

        while(!priorityQueue.isEmpty()){
            answer += (long) Math.pow(priorityQueue.poll(), 2);
        }

        return answer;
    }
}
