package org.example;

import java.io.*;
import java.util.*;


public class BOJ1916_1 {
//    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static int N, M, targetFrom, targetTo;
    static int[] dist;
    static List<Edge>[] adjList;
    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        dist = new int[N + 1];
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<Edge>();
        for (int i = 1; i <= M; i++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            int weight = scan.nextInt();
            adjList[from].add(new Edge(from, to, weight));
        }
        targetFrom = scan.nextInt();
        targetTo = scan.nextInt();
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

                int newCost = dist[info.idx] + adjCost;
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
    static class Edge{
        int from, to, cost;
        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    static class Info{
        int idx, dist;
        public Info(int idx, int dist){
            this.idx = idx;
            this.dist = dist;
        }
    }
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}


