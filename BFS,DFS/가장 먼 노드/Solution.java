import java.util.*;

class Solution {

    public int solution(int n, int[][] edge) {
        List<List<Integer>> graph = new ArrayList<>();

        // 노드의 개수만큼 리스트 추가
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        // a -> b 의 edge 를 인접 리스트에 추가
        for (int[] eachEdge : edge) {
            graph.get(eachEdge[0]-1).add(eachEdge[1]-1);
            graph.get(eachEdge[1]-1).add(eachEdge[0]-1);
        }

        int[] counts = new int[n];
        boolean[] visited = new boolean[n];

        // bfs로 가장 먼 노드의 개수 구해서 반환
        return bfs(counts, graph, visited);
    }

    /**
     * 1번 노드 -> 길이가 1만큼인 노드를 먼저 참색
     * 이후 길이가 1씩 늘어나는 노드들을 탐색
     */
    private int bfs(int[] counts, List<List<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        int distance = 1;
        visited[0] = true;
        queue.add(0);

        while (!queue.isEmpty()) {
            List<Integer> startingPoints = new ArrayList<>();
            while (!queue.isEmpty()) {
                startingPoints.add(queue.poll());
            }
            for (int startingPoint : startingPoints) {
                for (int next : graph.get(startingPoint)) {
                    if (!visited[next]) {
                        queue.add(next);
                        counts[next] = distance;
                        visited[next] = true;
                    }
                }
            }
            distance++;
        }
        int max = 0;
        for (int count : counts) {
            if (count > max) {
                max = count;
            }
        }

        int answer = 0;
        for (int count : counts) {
            if (count == max) {
                answer++;
            }
        }
        return answer;
    }
}
