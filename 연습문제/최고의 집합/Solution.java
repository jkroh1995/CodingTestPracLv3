import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
        if (s < n) {
            return new int[]{-1};
        }

        int[] answer = new int[n];

        if (s % n == 0) {
            Arrays.fill(answer, s / n);
            return answer;
        }

        int remainder = s % n;
        s -= remainder;
        Arrays.fill(answer, s / n);

        for (int i = n - 1; i >= n - remainder; i--) {
            answer[i]++;
        }

        return answer;
    }
}
