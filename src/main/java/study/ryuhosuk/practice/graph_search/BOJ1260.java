package study.ryuhosuk.practice.graph_search;

import java.util.*;

public class BOJ1260 {
    static Scanner sc = new Scanner(System.in);
    static int N,M,V;
    static ArrayList<Integer>[] adjList;
    static int[][] adjMatrix;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();
    static void input(){
        String[] arr = sc.nextLine().trim().split(" ");
        N = Integer.parseInt(arr[0]);
        M = Integer.parseInt(arr[1]);
        V = Integer.parseInt(arr[2]);
        adjList = new ArrayList[N+1];
        adjMatrix = new int[N+1][N+1];
        for(int i=1; i<=N; i++) adjList[i] = new ArrayList<>();
        // adjList
//        for(int i=1; i<=N; i++){
//            String[] vertexs = sc.nextLine().trim().split(" ");
//            int x = Integer.parseInt(vertexs[0]);
//            int y = Integer.parseInt(vertexs[1]);
//            adjList[x].add(y);
//            adjList[y].add(x);
//        }
        // adjMatrix
        for(int i=1; i<=N; i++){
            String[] vertexs = sc.nextLine().trim().split(" ");
            int x = Integer.parseInt(vertexs[0]);
            int y = Integer.parseInt(vertexs[1]);
            adjMatrix[x][y]=1;
            adjMatrix[y][x]=1;
        }
        for(int i=1; i<=N; i++) Collections.sort(adjList[i]);
        visit = new boolean[N+1];
    }

    static void dfs(int x){
        visit[x]=true;
        sb.append(x).append(" ");

//        인접리스트
//        for(int y: adjList[x]){
//            if(visit[y]) continue;
//
//            dfs(y);
//        }

//      인접행렬
        for(int y=1; y<=N; y++){
            if(adjMatrix[x][y]==0) continue;
            if(visit[y]) continue;

            dfs(y);
        }
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();

        q.add(start);
        visit[start] = true;

        // 인접리스트
        while (!q.isEmpty()){
            int x = q.poll();

            sb.append(x).append(" ");
            for (int y: adjList[x]){
                if(visit[y]) continue;

                q.add(y);
                visit[y]=true;
            }
        }

        // 인접 행렬
        while (!q.isEmpty()){
            int x = q.poll();

            sb.append(x).append(" ");
            for(int y=1; y<=N; y++){
                if(adjMatrix[x][y]==0) continue;
                if(visit[y]) continue;
                
                // y에 방문해야한다면 q에 넣고, visit 처리
                q.add(y);
                visit[y]=true;
            }
        }

    }

    static void sol(){
        dfs(V);
        sb.append("\n");
        for (int i=0; i<=N; i++) visit[i]=false;
        bfs(V);
        System.out.println(sb.toString());
    }


    public static void main(String[] args) {
        input();
        sol();
    }
}
