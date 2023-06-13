import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        List<String[]> list = new ArrayList<>(Arrays.asList(tickets));
        List<String> routeList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i)[0].equals("ICN")){
                List<String[]> dfsList = new ArrayList<>(list);
                String [] ticket = dfsList.remove(i);
                String route = "ICN," + ticket[1]+",";
                dfs(dfsList, ticket, route, routeList);
            }
        }

        Collections.sort(routeList);

        return routeList.get(0).split(",");
    }

    private void dfs(List<String[]> dfsList, String[] startingTicket, String route, List<String> routeList) {
        if(dfsList.size()==0){
            routeList.add(route);
            return;
        }
        for(String [] nextTicket : dfsList){
            if(startingTicket[1].equals(nextTicket[0])){
                List<String[]> nextList = new ArrayList<>(dfsList);
                nextList.remove(nextTicket);
                String nextRoute = route+nextTicket[1]+",";
                dfs(nextList, nextTicket, nextRoute, routeList);
            }
        }
    }
}
