package study.self_repeat.그래프.BOJ1697_숨바꼭질;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697_4 {
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
        dist[start]=0;

        while (!q.isEmpty()){
            int x = q.poll();

            int newX = 0;
            //1) 2배로 뛰는 경우
            newX = x*2;
            if(X_min <= newX && newX <= X_Max && !visit[newX]){
                q.add(newX);
                visit[newX] = true;
                dist[newX] = dist[x] + 1;
            }

            // 2) 한칸만 더 가는 경우
            newX = x+1;
            if(X_min <= newX && newX <= X_Max && !visit[newX]){
                q.add(newX);
                visit[newX] = true;
                dist[newX] = dist[x] + 1;
            }

            // 3) 좌측으로 한칸 가는 경우
            newX = x-1;
            if(X_min <= newX && newX <= X_Max && !visit[newX]){
                q.add(newX);
                visit[newX] = true;
                dist[newX] = dist[x] + 1;
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
