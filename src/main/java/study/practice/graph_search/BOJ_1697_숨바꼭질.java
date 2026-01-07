package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1697_숨바꼭질 {
    static int N, K;
    static boolean[] visited;
    static int[] distance;
    private static final int MAX = 100_000;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        N = Integer.parseInt(split[0]);
        K = Integer.parseInt(split[1]);
        distance = new int[MAX + 1];
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    static void sol() {
        for (int i = 0; i <= MAX; i++)
            distance[i] = -1;

        bfs(N);

        System.out.println(distance[K]);
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        distance[start] = 0;

        while (!q.isEmpty()){
            Integer curPosition = q.poll();
            int[] nxPosArr = {curPosition-1, curPosition+1, curPosition*2};
            for (int nxtPosition : nxPosArr) {
                if(nxtPosition<0 || nxtPosition > MAX) continue;
                if(distance[nxtPosition] != -1) continue;
                distance[nxtPosition] = distance[curPosition]+1;
                q.add(nxtPosition);
            }

        }
    }

}
