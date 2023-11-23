import java.util.*;

class Road implements Comparable<Road> {

    int distance;
    int point;

    public Road(int distance, int point) {
        this.distance = distance;
        this.point = point;
    }

    @Override
    public int compareTo(Road o) {
        return this.distance - o.distance;
    }
}

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        Set<Integer> starts = new HashSet<>();
        for (int gate : gates) {
            starts.add(gate);
        }

        Set<Integer> goals = new HashSet<>();
        for (int summit : summits) {
            goals.add(summit);
        }

        List<Map<Integer, Integer>> roadList = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            roadList.add(new HashMap<>());
        }
        for (int[] path : paths) {
            Map<Integer, Integer> timeMap = roadList.get(path[0]);
            timeMap.put(path[1], path[2]);
            timeMap = roadList.get(path[1]);
            timeMap.put(path[0], path[2]);
        }
        PriorityQueue<Road> priorityQueue = new PriorityQueue<>();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        for (int start : starts) {
            dp[start] = 0;
            priorityQueue.add(new Road(0, start));
        }
        
        // 다익스트라 알고리즘
        while (!priorityQueue.isEmpty()) {
            Road road = priorityQueue.poll();

            int point = road.point;
            if (goals.contains(point)) {
                if (answer[1] == road.distance) {
                    if (answer[0] > road.point) {
                        answer[0] = road.point;
                    }
                }
                if (answer[1] > road.distance) {
                    answer[0] = road.point;
                    answer[1] = road.distance;
                }
                continue;
            }
            if (dp[point] < road.distance) {
                continue;
            }
            Map<Integer, Integer> map = roadList.get(point);

            for (int next : map.keySet()) {
                int distance = road.distance;
                if (dp[next] > distance && !starts.contains(next)) {
                    if (distance < map.get(next)) {
                        distance = map.get(next);
                    }
                    dp[next] = distance;
                    priorityQueue.add(new Road(distance, next));
                }
            }
        }

        return answer;
    }
}
