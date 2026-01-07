package study.self_repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1753_1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int V, E, K;
    static List<Edge>[] edges;
    static int[] dist;
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine()," ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        edges = new ArrayList[V+1];
        for (int i = 0; i < V; i++) edges[i+1] = new ArrayList<>();

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[from].add(new Edge(from, to, weight));
        }

    }

    static void dijkstra(int s){
        PriorityQueue<Info> q = new PriorityQueue<>(Comparator.comparingInt(o->o.dist));
        q.add(new Info(s, 0));
        dist[s] = 0;

        while (!q.isEmpty()){
            Info info = q.poll();
            if(dist[info.idx] < info.dist) continue;

            for (Edge e : edges[info.idx]) {

                if(dist[e.to] <= dist[info.idx] + e.weight) continue;

                dist[e.to] = dist[info.idx] + e.weight;
                q.add(new Info(e.to, dist[e.to]));
            }
        }
    }

    static void sol() throws Exception {
        dijkstra(K);
        for (int i = 1; i < dist.length; i++)
            System.out.println(dist[i]==Integer.MAX_VALUE?"INF":dist[i]);
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    static class Edge{
        int from, to, weight;
        Edge(int from, int to, int wegiht){
            this.from = from;
            this.to = to;
            this.weight = wegiht;
        }
    }
    static class Info{
        int idx, dist;
        public Info(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }
}
