import java.util.*;
class Solution {
    int answer;
    List<Set<String>> list = new ArrayList<>();

    public int solution(String[] user_id, String[] banned_id) {
        answer = 0;
        Map<String, List<String>> bannedIdMap = new HashMap<>();

        // 각 밴 id 마다 가능한 user id 를 할당
        makeBannedIdMap(user_id, banned_id, bannedIdMap);

        // 재귀적으로 가능한 경우 검색
        dfs(banned_id, bannedIdMap, 0, new HashSet<>());
        return answer;
    }

    private static void makeBannedIdMap(String[] user_id, String[] banned_id, Map<String, List<String>> idMap) {
        for (String ban : banned_id) {
            List<String> mappedId = new ArrayList<>();
            String[] banArr = ban.split("");
            for (String user : user_id) {
                if (ban.length() == user.length()) {
                    String[] userArr = user.split("");
                    boolean isMatch = true;
                    for (int i = 0; i < userArr.length; i++) {
                        if (banArr[i].equals("*")) {
                            continue;
                        }
                        if (!banArr[i].equals(userArr[i])) {
                            isMatch = false;
                            break;
                        }
                    }
                    if (isMatch) {
                        mappedId.add(user);
                    }
                }
            }
            idMap.put(ban, mappedId);
        }
    }

    private void dfs(String[] bannedId, Map<String, List<String>> idMap, int index, Set<String> idSet) {
        if (index >= bannedId.length) {
            if (idSet.size() == bannedId.length) {
                for(Set<String>set : list){
                    // 이미 한 번 거쳤던 루트면 정답에 포함하지 않음.
                    if(set.containsAll(idSet)){
                        return;
                    }
                }
                answer++;
                list.add(idSet);
            }
            return;
        }

        List<String> mappedIdList = idMap.get(bannedId[index]);

        for (String mappedId : mappedIdList) {
            if (!idSet.contains(mappedId)) {
                Set<String> nextIdSet = new HashSet<>(idSet);
                nextIdSet.add(mappedId);
                int nextIndex = index + 1;
                dfs(bannedId, idMap, nextIndex, nextIdSet);
            }
        }
    }
}
