class Solution {
    public long solution(int[] sequence) {
        // 펄스 수열이 나오는 경우의 수는 두 개밖에 없다.
        int[] ordinalPulse = new int[sequence.length];
        int[] reversePulse = new int[sequence.length];

        int pulse = 1;

        // 두 펄스 수열 생성
        for (int i = 0; i < ordinalPulse.length; i++) {
            ordinalPulse[i] = sequence[i] * pulse;
            pulse *= -1;
            reversePulse[i] = sequence[i] * pulse;
        }

        // 부분 수열의 합을 구할 dp배열 생성
        long[] ordinalDp = new long[sequence.length];
        long[] reverseDp = new long[sequence.length];

        ordinalDp[0] = ordinalPulse[0];
        reverseDp[0] = reversePulse[0];

        // 앞에 값까지 더하는게 이득이면 더해서 부분수열 합을 구하고, 그렇지 않으면 새롭게 부분수열 합을 시작
        for (int i = 1; i < ordinalPulse.length; i++) {
            if (ordinalPulse[i] < ordinalDp[i - 1] + ordinalPulse[i]) {
                ordinalDp[i] = ordinalDp[i - 1] + ordinalPulse[i];
            } else {
                ordinalDp[i] = ordinalPulse[i];
            }
            if (reversePulse[i] < reverseDp[i - 1] + reversePulse[i]) {
                reverseDp[i] = reverseDp[i - 1] + reversePulse[i];
            } else {
                reverseDp[i] = reversePulse[i];
            }
        }

        // 최댓값 구하기
        long max = ordinalDp[0];
        for(long num : ordinalDp){
            if(num>max){
                max = num;
            }
        }

        for(long num : reverseDp){
            if (num > max){
                max = num;
            }
        }

        return max;
    }
}
