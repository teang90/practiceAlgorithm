package study.ryuhosuk.practice.dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1753 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int V ,E, K ;
    static int[] dist;
    static List<Edge>[] adjList;
    static StringTokenizer st ;
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        adjList = new ArrayList[V+1];
        dist = new int[V+1];
        for (int i = 1; i <= V; i++) adjList[i] = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from].add(new Edge(from, to, weight));
        }
        for (int i = 1; i <= V; i++) dist[i] = Integer.MAX_VALUE;
    }
    static void dijkstra(int start){

        PriorityQueue<Info> q = new PriorityQueue<>(Comparator.comparingInt(o->o.dist));
        q.add(new Info(start, 0));
        dist[start] = 0;

        while (!q.isEmpty()){
            Info info = q.poll();
            if(dist[info.idx] < info.dist) continue;

            for (Edge edge : adjList[info.idx]) {
                if(dist[edge.to] <= dist[info.idx] + edge.weight) continue;
                dist[edge.to] = dist[info.idx] + edge.weight;
                q.add(new Info(edge.to, dist[edge.to]));
            }
        }
    }
    static void sol() {
        dijkstra(K);
        for (int i = 1; i < dist.length; i++) {
            if(dist[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
    static class Info {
        int idx, dist;
        public Info(int idx, int dist){
            this.idx = idx;
            this.dist = dist;
        }
    }
    static class Edge {
        int from, to, weight;
        public Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
