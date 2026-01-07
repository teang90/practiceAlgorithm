package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1753 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int V, E, start; // V: [1, 20000] , E: [1, 300000]
    static List<Edge>[] edges ;
    static int[] dist;
    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V =Integer.parseInt(st.nextToken());
        E =Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        edges = new ArrayList[V+1];
        dist = new int[V+1];
        for (int i = 0; i < V; i++) edges[i+1] = new ArrayList<>();
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[from].add(new Edge(from, to, weight));
        }
    }

    static void dijkstra(int start) {
        for (int i = 0; i < V; i++) dist[i+1] = Integer.MAX_VALUE;
        PriorityQueue<Info> q = new PriorityQueue<Info>(Comparator.comparingInt(o->o.dist));
        dist[start] = 0;
        q.add(new Info(start, 0));

        while (!q.isEmpty()){
            Info info = q.poll();
            if(dist[info.idx] < info.dist) continue;
//            if(dist[info.idx] != info.dist) continue;

            for (Edge edge : edges[info.idx]) {
                if(dist[edge.to] <= dist[info.idx] + edge.weight) continue;

                dist[edge.to] = dist[info.idx] + edge.weight;
                q.add(new Info(edge.to, dist[edge.to]));
            }
        }
    }

    static void sol(){
        dijkstra(start);
        for (int i = 1; i <= V; i++) {
            if(dist[i] == Integer.MAX_VALUE){
                System.out.println("INF");
            }else{
                System.out.println(dist[i]);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}

class Edge{
    int from, to, weight;
    public Edge(int from, int to, int weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

class Info{
    int idx, dist;
    public Info(int idx, int dist){
        this.dist = dist;
        this.idx = idx;
    }
}
