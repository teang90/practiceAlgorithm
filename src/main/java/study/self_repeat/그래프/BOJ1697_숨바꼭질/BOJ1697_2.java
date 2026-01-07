package study.self_repeat.그래프.BOJ1697_숨바꼭질;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;    // N: [0, 100000], K: [0,]
    static int[] dist;
    static boolean[] visit;
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new int[100001];
        visit = new boolean[100001];
    }
    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            dist[i] = -1;
            if(i==start) {
                dist[start] = 0;
                visit[start] = true;
            }
        }
        q.add(start);

        while (!q.isEmpty()){
            int x = q.poll();

            // 현재 x 위치에서 다음으로 옮겨 갈 수 있는 곳
            int y = x-1;
            if(0 <= y && !visit[y]){
                q.add(y);
                visit[y] = true;
                dist[y] = dist[x]+1;
            }
            y = x+1;
            if(0 <= y  && y <= 100000 && !visit[y]){
                q.add(y);
                visit[y] = true;
                dist[y] = dist[x]+1;
            }

            y = 2*x;
            if(0 <= y && y <= 100000 && !visit[y]){
                q.add(y);
                visit[y] = true;
                dist[y] = dist[x]+1;
            }
        }

    }
    static void sol(){
        bfs(N);
        System.out.println(dist[K]);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
