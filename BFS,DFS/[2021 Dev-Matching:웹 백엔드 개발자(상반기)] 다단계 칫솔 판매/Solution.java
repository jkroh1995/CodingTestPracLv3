import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        Map<String, String> enrollReferralMap = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            enrollReferralMap.put(enroll[i], referral[i]);
        }

        Map<String, Integer> sellerAmountMap = new HashMap<>();
        for (String name : enroll) {
            sellerAmountMap.put(name, 0);
        }

        for (int i = 0; i < seller.length; i++) {
            String name = seller[i];
            int benefit = amount[i] * 100;
            while (!name.equals("-") && benefit != 0) {
                int get = benefit - benefit * 10 / 100;
                sellerAmountMap.put(name, sellerAmountMap.get(name) + get);
                benefit = benefit * 10 / 100;
                name = enrollReferralMap.get(name);
            }
        }

        for (int i = 0; i < enroll.length; i++) {
            answer[i] = sellerAmountMap.get(enroll[i]);
        }
        return answer;
    }
}
