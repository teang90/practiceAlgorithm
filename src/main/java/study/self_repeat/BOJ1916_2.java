package study.self_repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1916_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
    static int N, M, busFrom, busTo;
    static List<Edge>[] edges;
    static StringTokenizer st ;
    static int[] dist;
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N+1];    // 실제 적합한 거리를 갖고있을 거리 배열
        for (int i = 1; i <= N; i++)
            dist[i] = Integer.MAX_VALUE;

        edges = new ArrayList[N+1];
        for (int i = 1; i <= N; i++)
            edges[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            Edge edge = new Edge(from, to, weight);
            edges[from].add(edge);
        }

        st = new StringTokenizer(br.readLine(), " ");
        busFrom = Integer.parseInt(st.nextToken());
        busTo = Integer.parseInt(st.nextToken());
    }
    static void dijkstra(int start){
        PriorityQueue<Info> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        q.add(new Info(start, 0));
        dist[start] = 0;

        while (!q.isEmpty()) {
            Info info = q.poll();
            if(dist[info.idx] < info.dist) continue;

            for (Edge e : edges[info.idx]){
                if(dist[e.to] <= dist[info.idx] + e.weight) continue;

                dist[e.to] = dist[info.idx] + e.weight;
                q.add(new Info(e.to, dist[e.to]));
            }

        }



    }
    static void sol() throws Exception {
        dijkstra(busFrom);
        System.out.println(dist[busTo]);
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    static class Edge{
        int from, to, weight;
        public Edge(int from, int to, int  weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    static class Info{
        int idx, dist;
        public Info(int idx, int dist){
            this.idx = idx;
            this.dist = dist;
        }
    }
}

