package study.ryuhosuk.practice.two_pointers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1260 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, V;
    static List<Integer>[] adjList;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();
    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        visit = new boolean[N+1];

        adjList = new ArrayList[N+1];
        for (int i = 0; i < N; i++) adjList[i+1] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            adjList[s].add(e);
            adjList[e].add(s);
        }

        for (int i = 1; i <= N; i++) Collections.sort(adjList[i]);

    }
    static void dfs(int start){
        sb.append(start).append(" ");
        visit[start] = true;

        for (int y : adjList[start]) {
            if(visit[y]) continue;

            dfs(y);
        }
    }
    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;

        while (!q.isEmpty()){
            int x = q.poll();
            sb.append(x).append(" ");

            for (int y : adjList[x]) {
                if(visit[y]) continue;

                q.add(y);
                visit[y] = true;
            }
        }

    }
    static void sol() {
        dfs(V);
        System.out.println(sb);
        Arrays.fill(visit, false);

        sb = new StringBuilder();
        bfs(V);
        System.out.println(sb);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
