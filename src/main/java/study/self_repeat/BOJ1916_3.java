package study.self_repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1916_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, start, end;    //N: 정점(도시) , M: 간선(버스),
    static List<Edge>[] adj;
    static int[] dist;
    static void input() throws Exception {
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        adj = new ArrayList[N+1];
        for (int i = 0; i < N; i++)
            adj[i+1] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adj[from].add(new Edge(from, to, weight));
        }
        st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }
    static void dikstra(int start){
        Queue<Info> q = new PriorityQueue<>(Comparator.comparingInt(o->o.dist));
        q.add(new Info(start, 0));
        dist[start] = 0;

        while (!q.isEmpty()) {
            Info info = q.poll();
            if(dist[info.idx] < info.dist) continue;
            for (Edge e : adj[info.idx]) {
                if(dist[e.to] <= e.weight + dist[e.from]) continue;
                dist[e.to] = e.weight + dist[e.from];
                q.add(new Info(e.to, dist[e.to]));
            }
        }
    }
    static void sol() {    // A->B로의 경로를 최소화하려고한다.
        dikstra(start);
        System.out.println(dist[end]);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
    static class Edge {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    static class Info {
        int idx, dist;
        Info(int idx, int dist){
            this.idx = idx;
            this.dist = dist;
        }
    }
}