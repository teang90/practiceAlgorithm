package study.self_repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1753_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int V, E, K;    // V: 정점, E: 간선, K: 시작점
    static List<Edge>[] adjList;
    static int[] dist;
    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        adjList = new List[V+1];
        for (int i = 1; i < V+1; i++) adjList[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            Edge edge = new Edge(from, to, weight);
            adjList[from].add(edge);
        }
    }
    static void dikjstra(int start) {
        Queue<Info> q = new PriorityQueue<>(Comparator.comparingInt(o-> o.dist));
        q.add(new Info(start, 0));
        dist[start] = 0;

        while (!q.isEmpty()) {
            Info info = q.poll();
            if(dist[info.idx] < info.dist) continue;

            for (Edge e : adjList[info.idx]) {
                if(dist[e.to] <= e.weight + dist[info.idx]) continue;

                dist[e.to] = e.weight + dist[info.idx];
                q.add(new Info(e.to, dist[e.to]));
            }
        }
    }
    static void sol() {
        dikjstra(K);
        for (int i = 0; i < V; i++) {
            System.out.println(dist[i+1]==Integer.MAX_VALUE? "INF":dist[i+1]);
        }
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
    static class Edge {
        int from, weight, to;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    static class Info {
        int idx, dist;
        public Info(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }
}
