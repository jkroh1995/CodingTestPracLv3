
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] billMap = initBillMap(n, fares);

        /**
         *  플로이드 와셜 알고리즘으로 전체 그래프에서 최단 거리를 계산
         */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (billMap[j][k] > billMap[j][i] + billMap[i][k]) {
                        billMap[j][k] = billMap[j][i] + billMap[i][k];
                    }
                }
            }
        }

        /**
         *  합승을 하지 않은 경우를 디폴트로 계산
         *  (s->a) + (s->b)
         */
        int bill = billMap[s - 1][a - 1] + billMap[s - 1][b - 1];

        /**
         *  합승을 한 경우
         *  (s->i) + (i->a) + (i->b) < (s->a) + (s->b) 이면 합승한 경우를 최저 값으로 계산
         */
        for (int i = 0; i < n; i++) {
            if (bill > billMap[s - 1][i] + billMap[i][a - 1] + billMap[i][b - 1]) {
                bill = billMap[s - 1][i] + billMap[i][a - 1] + billMap[i][b - 1];
            }
        }

        return bill;
    }

    private int[][] initBillMap(int n, int[][] fares) {
        int[][] billMap = new int[n][n];
        for (int[] fare : fares) {
            billMap[fare[0] - 1][fare[1] - 1] = fare[2];
            billMap[fare[1] - 1][fare[0] - 1] = fare[2];
        }

        for (int i = 0; i < billMap.length; i++) {
            for (int j = 0; j < billMap[i].length; j++) {
                if (i != j && billMap[i][j] == 0) {
                    billMap[i][j] = n * 100000 + 1;
                }
            }
        }

        return billMap;
    }
}
