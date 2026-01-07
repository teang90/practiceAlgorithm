package study.self_repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class BOJ1916_1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, targetFrom, targetTo;
    static int[] dist;
    static List<Edge>[] adjList;
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjList = new List[N+1];
        dist = new int[N+1];
        for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            Edge edge = new Edge(from, to, cost);
            adjList[from].add(edge);
        }
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        targetFrom  = Integer.parseInt(st.nextToken());
        targetTo    = Integer.parseInt(st.nextToken());
    }
    static void dijkstra(int start) {
        for (int i = 1; i <= N; i++) dist[i] = Integer.MAX_VALUE;
        PriorityQueue<Info> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        q.add(new Info(start, 0));  // 자기자신한테
        dist[start] = 0;

        while (!q.isEmpty()){
            Info info = q.poll();

            if(dist[info.idx] < info.dist) continue;
            for (Edge adjEdge : adjList[info.idx]) {
                int adjFrom  = adjEdge.from;
                int adjTo  = adjEdge.to;
                int adjCost  = adjEdge.cost;

                int newCost = dist[adjFrom] + adjCost;
                if(dist[adjTo] < newCost) continue;
                dist[adjTo] = newCost;
                q.add(new Info(adjTo, newCost));
            }
        }


    }

    public static void main(String[] args) throws Exception {
        input();
        dijkstra(targetFrom);
        System.out.println(dist[targetTo]);
    }
}


class Edge{
    int from, to, cost;
    public Edge(int from, int to, int cost){
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}
class Info{
    int idx, dist;
    public Info(int idx, int dist){
        this.idx = idx;
        this.dist = dist;
    }
}