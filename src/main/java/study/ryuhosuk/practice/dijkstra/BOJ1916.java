package study.ryuhosuk.practice.dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1916 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, targetFrom, targetTo;   // N: 도시의 갯수 - [1,1000], M: 버스의 갯수 - [1, 10^5]
    static List<Edge>[] adjList;
    static int[] dist;
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N+1];        // 최소 버스 비용만 넣을 비용 배열
        adjList = new List[N+1];
        for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            // 버스의 정보 [출발지, 도착지, 버스 비용]
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList[from].add(new Edge(from, to, cost));
        }
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        targetFrom  = Integer.parseInt(st.nextToken());
        targetTo    = Integer.parseInt(st.nextToken());
    }

    static void dijkstra(int start){
        for (int i = 1; i <= N; i++) dist[i] = Integer.MAX_VALUE;

        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        pq.add(new Info(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()){
            Info prev = pq.poll();
            if(dist[prev.idx] < prev.dist) continue;    // prev.dist의 최단거리 가치가(더 가깝다면) 사용

            for (Edge edge : adjList[prev.idx]) {   // TODO : check point 여기서 info 객체의 idx, dist의 의미 다시 잡기(from, 비용) ???
                if(dist[edge.to] <= dist[prev.idx] + edge.cost) continue;

                // e.to 까지 갈 수 있는 더 짧은 거리를 찾았다면, 이 거리를 갱신하고, PQ에 기록한다.
                dist[edge.to] = dist[prev.idx]+edge.cost;
                pq.add(new Info(edge.to, dist[edge.to]));
            }
        }

    }
    static void sol(){
        dijkstra(targetFrom);
        System.out.println(dist[targetTo]);
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
class Edge{
    int from ;
    int to ;
    int cost;
    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}

class Info{ // 정점, 간선의 정보 객체
    public int idx ;
    public int dist ;
    public Info(){}
    public Info(int idx, int dist) {
        this.idx = idx;
        this.dist = dist;
    }
}

