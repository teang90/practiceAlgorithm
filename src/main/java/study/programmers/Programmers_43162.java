package study.programmers;

import java.util.*;

public class Programmers_43162 {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visit = new boolean[n];

        for (int idx = 0; idx < n; idx++) {
            if(!visit[idx]){
                dfs(visit, idx, computers);
//                bfs(visit, idx, computers);
                answer++;
            }
        }

        return answer;
    }
    void dfs(boolean[] visit, int sIdx, int[][] computers) {
        visit[sIdx] = true;

        for (int i = 0; i < computers[sIdx].length; i++) {
            if(visit[i]) continue;
            if(computers[sIdx][i]==1 && sIdx!=i){
                dfs(visit, i, computers);
            }
        }

    }
    void bfs(boolean[] visit, int s, int[][] computers){
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visit[s] = true;

        while (!q.isEmpty()){
            int x = q.poll();
            for (int y = 0; y < computers[x].length; y++) {
                if(visit[y]) continue;
                if(x != y && computers[x][y] == 1){
                    q.add(y);
                    visit[y] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Programmers_43162 p = new Programmers_43162();
        int res = p.solution(3, new int[][]{{1,1,0}, {1, 1, 1}, {0,1,1}});
//        int res = p.solution(3, new int[][]{{1,1,0}, {1, 1, 0}, {0,0,1}});
        System.out.println(res);
    }

}
