package study.programmers;

import java.util.*;

public class Problem_49189 {
    List<Integer>[] adjList;
    int[] dist;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        adjList = new ArrayList[n+1];
        dist = new int[n+1];
        Arrays.fill(dist, -1);
        for (int i = 1; i <= n; i++) adjList[i] = new ArrayList<>();

        for (int i = 0; i < edge.length; i++) {
            int from = edge[i][0];
            int to = edge[i][1];
            adjList[from].add(to);
            adjList[to].add(from);
        }

        for (int i = 1; i <= n; i++) Collections.sort(adjList[i]);

        bfs(1);

        int maxWeight = Integer.MIN_VALUE;
        int maxIdx = 0;
        for (int i = 1; i < n+1; i++) {
            if(maxWeight <= dist[i]){
                maxIdx = i;
                maxWeight = dist[i];
            }
        }


        for (int i = 1; i <= n; i++) {
            if(maxWeight==dist[i]) answer++;
        }

        return answer;
    }
    public void bfs(int s) {
        Queue<Integer> q = new LinkedList<>();
        dist[s] = 0;
        q.add(s);

        while (!q.isEmpty()){
            int x = q.poll();
            for (int y : adjList[x]) {
                if(dist[y] > -1) continue;
                dist[y] = dist[x]+1;
                q.add(y);
            }
        }

    }
}
