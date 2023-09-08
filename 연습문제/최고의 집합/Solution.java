import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
        // 자연수 집합이기 때문에 n이 더 크면 집합을 만들 수 없음.
        if (s < n) {
            return new int[]{-1};
        }

        int[] answer = new int[n];

        // 만일 s가 n으로 나누어 떨어지면 s를 n으로 나눠 균일한 값을 집합에 채움
        if (s % n == 0) {
            Arrays.fill(answer, s / n);
            return answer;
        }

        int remainder = s % n;
        s -= remainder;
        Arrays.fill(answer, s / n);

        // remainder는 n보다 작을 수밖에 없기 때문에, 1씩 숫자를 늘려서 숫자 간 격차를 최소화 함
        for (int i = n - 1; i >= n - remainder; i--) {
            answer[i]++;
        }

        return answer;
    }
}
