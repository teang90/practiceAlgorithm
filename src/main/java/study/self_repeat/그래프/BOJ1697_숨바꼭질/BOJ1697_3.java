package study.self_repeat.그래프.BOJ1697_숨바꼭질;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K, X_Max, X_min ;    // N: 수빈 위치, K: 동생 위치
    static boolean[] visit;
    static int[] dist;
    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new int[100000+1];
        Arrays.fill(dist, -1);
        visit = new boolean[100000+1];
        X_min = 0;
        X_Max = 100000;
    }
    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
        dist[start] = 0;

        while (!q.isEmpty()) {
            int x = q.poll();

            int nx = 0;
            nx = 2*x;
            if(X_min <= nx && nx <= X_Max && !visit[nx]){
                q.add(nx);
                visit[nx] = true;
                dist[nx] = dist[x]+1;
            }

            nx = x+1;
            if(X_min <= nx && nx <= X_Max && !visit[nx]){
                q.add(nx);
                visit[nx] = true;
                dist[nx] = dist[x]+1;
            }

            nx = x-1;
            if(X_min <= nx && nx <= X_Max && !visit[nx]){
                q.add(nx);
                visit[nx] = true;
                dist[nx] = dist[x]+1;
            }
        }

    }
    static void sol() {
        bfs(N);
        System.out.println(dist[K]);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
