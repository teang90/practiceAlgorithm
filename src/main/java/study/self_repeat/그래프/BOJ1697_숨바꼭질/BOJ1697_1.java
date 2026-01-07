package study.self_repeat.그래프.BOJ1697_숨바꼭질;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1697_1 {    // 숨바꼭질
    // bfs 최단 시간, 최단거리 등...
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K;
    static boolean[] visit = new boolean[100001];
    static int[] dist = new int[100001];
    static void input(){
        try{
            String[] arr = br.readLine().split(" ");
            N = Integer.parseInt(arr[0]);
            K = Integer.parseInt(arr[1]);
        }catch (Exception e){}
    }
    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
        dist[start] = 0;

        while (!q.isEmpty()){
            int x = q.poll();
            int y = x-1;
            if(y>=0 && y<=100000 && !visit[y]){
                visit[y] = true;
                dist[y] = dist[x]+1;
                q.add(y);
            }
            y = x+1;
            if(y>=0 && y<= 100000 && !visit[y]){
                visit[y] = true;
                dist[y] = dist[x]+1;
                q.add(y);
            }
            y = 2*x;
            if(y>=0 && y<= 100000 && !visit[y]){
                visit[y] = true;
                dist[y] = dist[x]+1;
                q.add(y);
            }
        }

    }
    static void sol(){
        bfs(N);
        System.out.println(dist[K]);
    }
    public static void main(String[] args) {
        input();
        sol();
    }
}
