package study.ryuhosuk.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static String[] space;
    static boolean[][] visit;
    static int[][] dist;
    static void input() throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());   // 가로
        M = Integer.parseInt(st.nextToken());   // 세로
        space = new String[N];
        visit = new boolean[N][M];
        dist = new int[N][M];
        for (int i = 0; i < N; i++) space[i] = br.readLine();
    }
    static void bfs(int x, int y) {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                dist[i][j] = -1;

        Queue<Integer> q = new LinkedList<>();
        visit[x][y] = true;
        q.add(x);
        q.add(y);
        dist[x][y]=1;

        while (!q.isEmpty()){
            int _x = q.poll();
            int _y = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = _x + dir[k][0];
                int ny = _y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visit[nx][ny]) continue;
                if(space[nx].charAt(ny) != '1') continue;

                visit[nx][ny] = true;
                q.add(nx);
                q.add(ny);
                dist[nx][ny] = dist[_x][_y] + 1;
            }
        }
    }
    static void sol() {
        bfs(0,0);
        System.out.println(dist[N-1][M-1]);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

}
