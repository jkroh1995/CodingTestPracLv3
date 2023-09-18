class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];
        
        // 1행 및 1열 초기화
        for (int i = 0; i < n; i++) {
            map[i][0] = 1;
        }
        for (int i = 0; i < m; i++) {
            map[0][i] = 1;
        }

        // 갈 수 없는 위치는 -1로 처리
        for (int[] puddle : puddles) {
            map[puddle[1] - 1][puddle[0] - 1] = -1;
        }

        // 만일 1열 및 1행에서 갈 수 없는 지역이 있으면 이후로도 갈 수 있는 최소 경로는 없음
        for (int i = 1; i < n; i++) {
            if (map[i - 1][0] == -1) {
                map[i][0] = -1;
            }
        }
        for (int i = 1; i < m; i++) {
            if (map[0][i - 1] == -1) {
                map[0][i] = -1;
            }
        }

        // 바로 위 또는 바로 왼쪽 까지 갈 수 있는 최적 경로의 합이 특정 위치까지 갈 수 있는 최적 경로 수
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (map[i][j] == 0) {
                    if (map[i - 1][j] == -1) {
                        map[i][j] = map[i][j - 1];
                        continue;
                    }
                    if (map[i][j - 1] == -1) {
                        map[i][j] = map[i - 1][j];
                        continue;
                    }
                    map[i][j] = (map[i - 1][j] + map[i][j - 1]) % 1000000007;
                }
            }
        }
        
        // 만일 갈 수 없으면 0 반환
        if (map[n - 1][m - 1] == -1) {
            return 0;
        }
        return map[n - 1][m - 1];
    }
}
