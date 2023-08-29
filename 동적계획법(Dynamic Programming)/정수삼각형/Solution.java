import java.util.Arrays;

class Solution {
    public int solution(int[][] triangle) {
        for (int i = 0; i < triangle.length - 1; i++) {
            int[] upper = triangle[i];
            int[] lower = triangle[i + 1];
            int[] copyOfLower = new int[lower.length];
            System.arraycopy(lower, 0, copyOfLower, 0, lower.length);
            for (int j = 0; j < upper.length; j++) {
                if (upper[j] + lower[j] > copyOfLower[j]) {
                    copyOfLower[j] = upper[j] + lower[j];
                }
                if (upper[j] + lower[j + 1] > copyOfLower[j + 1]) {
                    copyOfLower[j + 1] = upper[j] + lower[j + 1];
                }
            }
            System.arraycopy(copyOfLower, 0, lower, 0, lower.length);
        }
        return Arrays.stream(triangle[triangle.length - 1])
                .max()
                .getAsInt();
    }
}
