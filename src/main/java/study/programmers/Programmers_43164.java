package study.programmers;

import java.util.*;

public class Programmers_43164 {
    void test(){
        Map map = new HashMap();
//        map.computeIfAbsent("", "");




    }
    // 이해할것
    // https://tosuccess.tistory.com/36
    // https://school.programmers.co.kr/learn/courses/30/lessons/43164/solution_groups?language=java&type=all
    public String[] solution(String[][] tickets) {
        Map<String, Set<String>> airportMap = new HashMap<>();
        Map<String, Integer> visit = new HashMap<>();

        for (int i = 0; i < tickets.length; i++) {
            for (int j = 0; j < tickets[i].length; j++) {
                Set<String> set = airportMap.getOrDefault(tickets[i][0], new TreeSet<>());
                set.add(tickets[i][1]);
                airportMap.put(tickets[i][0], set);
            }
        }

        List<String> answer = new LinkedList<>();
        // start 도시부터 시작
        Stack<String> st = new Stack<>();
        st.add("ICN");
        answer.add("ICN");

        while (!st.isEmpty()) {
            String from = st.peek();
            for (String destAirport : airportMap.get(from)) {
                if(visit.get(from+":"+destAirport)!=null) continue;
                visit.put(from+":"+destAirport, 1);
                answer.add(destAirport);
                st.add(destAirport);
            }
        }

        return answer.toArray(new String[visit.size()]);
    }

    void dfs(int k, String start, Map<String, Set<String>> airportMap, String[][] tickets, String[] route, Map<String, Integer> visit) {
        if(k==route.length) return ;

        route[k]=start;



//        for (int i = 0; i < tickets.length; i++) {
//            if(tickets[i][0].equals(start)){
//                // to로 갈 수 있는 공항을 from ~ to로 넣어준다.
//                // put(start, List.add(to)) -> 반복 ***
////                if(visit.get(tickets[i][1])) continue;
//                System.out.println(start+"~ "+tickets[i][0]+"->"+tickets[i][1]);
//            }
//        }
//        for (int i = 0; i < tickets.length; i++) {
//            // 위의 ***에서 넣어준 것을 여기서 순회... 정렬해서
//            dfs(k+1, tickets[i][1], visit, tickets, route);
//        }

    }
//    class Airport{
//        String from;
//
//        Airport(String from, String[] to){
//            this.from = from;
//            if(to!=null){
//                to = new String[]
//            }
//
//        }
//    }
    public static void main(String[] args) {
        Programmers_43164 p = new Programmers_43164();
        String[] res = p.solution(new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}});
//        String[] res = p.solution(new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}});
        System.out.println(Arrays.toString(res));
    }
}
/*
import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        List<String> answer = new ArrayList<>();
        Map<String, PriorityQueue<String>> map = new HashMap<>();

        // 각 공항마다 가능한 도착지들을 우선순위 큐로 저장합니다.
        for (String[] ticket : tickets) {
            map.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).offer(ticket[1]);
        }

        // DFS 알고리즘으로 경로를 탐색합니다.
        Stack<String> stack = new Stack<>();
        stack.push("ICN");
        while (!stack.isEmpty()) {
            String curr = stack.peek();
            if (!map.containsKey(curr) || map.get(curr).isEmpty()) {
                answer.add(stack.pop());
            } else {
                stack.push(map.get(curr).poll());
            }
        }

        // 경로를 거꾸로 반환합니다.
        Collections.reverse(answer);

        return answer.toArray(new String[0]);
    }
}

* */