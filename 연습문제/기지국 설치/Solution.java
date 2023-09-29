class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        // 하나의 기지국이 커버할 수 있는 범위
        int cover = 2 * w + 1;

        // index 로 접근하기 위해 1씩 빼줌
        for (int i = 0; i < stations.length; i++) {
            stations[i] -= 1;
        }

        // 0 ~ 첫 번째 기지국의 왼쪽 끝까지
        answer += calc(0, stations[0] - w - 1, cover);
        
        // n-1 번째 기지국의 오른쪽 끝부터 n 번째 기지국의 왼쪽 끝까지
        for (int i = 1; i < stations.length; i++) {
            answer += calc(stations[i - 1] + w +1, stations[i] - w - 1, cover);
        }
        
        // n번째 기지국의 오른쪽 끝부터 마지막 아파트까지
        answer += calc(stations[stations.length - 1] + w + 1, n - 1, cover);
        return answer;
    }

    private int calc(int start, int end, int cover) {
        int num = end - start + 1;
        
        // 첫 번째 기지국이 0번째 아파트 까지 커버하는 경우
        if (num <= 0) {
            return 0;
        }
        
        // 커버해야 하는 아파트의 수가 기지국이 커버할 수 있는 범위로 나누어 떨어질 때
        if (num % cover == 0) {
            return num / cover;
        }
        
        // 나누어 떨어지지 않으면 하나를 더 설치해야 한다.
        return num / cover + 1;
    }
}
