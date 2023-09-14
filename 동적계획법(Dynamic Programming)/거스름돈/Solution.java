class Solution {
    public int solution(int n, int[] money) {
        // 0원의 경우(시작점)를 담기 위해 n+1
        int[] bag = new int[n + 1];

        // 0원만 담는 경우는 아무것도 담지 않는 한 가지 경우
        bag[0] = 1;

        // x 원으로 n 원을 만들 수 있는 방법은 (기존의 n 원을 만드는 방법의 수 = x 원을 사용하지 않은 경우 + n-x 원을 만드는 방법의 수 = x원을 하나만 사용한 경우)
        // 1원부터 차례대로 채워가기 때문에, x 원을 여러 개 사용하지 않는 경우부터 하나씩 값을 채울 수 있다.
        for (int coin : money) {
            for (int j = 1; j <= n; j++)
                if (coin <= j)
                    bag[j] = (bag[j] + bag[j - coin]) % 1000000007;
        }

        return bag[n];
    }
}
