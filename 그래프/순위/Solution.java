import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] map = new int[n][n];

        for (int[] result : results) {
            map[result[0] - 1][result[1] - 1] = 1;
            map[result[1] - 1][result[0] - 1] = -1;
        }

        for (int i = 0; i < n; i++) {
            // i 번째 선수를 이긴 선수들
            List<Integer> winners = new ArrayList<>();

            // i 번째 선수에게 진 선수들
            List<Integer> losers = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 1){
                    losers.add(j);
                }
                if(map[i][j] == -1){
                    winners.add(j);
                }
            }

            // 승패 결과를 다시 갱신
            for(int winner : winners){
                for(int loser : losers){
                    map[winner][loser] = 1;
                    map[loser][winner] = -1;
                }
            }
        }

        // 모든 경기 결과를 파악할 수 있으면 정답
        for(int i = 0 ;i<n;i++){
            boolean isAnswer = true;
            for(int j = 0;j<n;j++){
                if(i!=j && map[i][j]==0){
                    isAnswer =false;
                    break;
                }
            }
            if(isAnswer){
                answer++;
            }
        }

        return answer;
    }
}
