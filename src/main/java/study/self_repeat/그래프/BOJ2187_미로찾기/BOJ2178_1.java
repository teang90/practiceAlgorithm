package study.self_repeat.그래프.BOJ2187_미로찾기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2178_1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[][] visit;
    static int dist[][];
    static int N, M;
    static String[] a;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static void input() throws Exception {
        String[] arr = br.readLine().split(" ");
        N = Integer.parseInt(arr[0]);   // 행
        M = Integer.parseInt(arr[1]);   // 열
        visit = new boolean[N][M];
        dist = new int[N][M];
        a = new String[N];
        for (int i = 0; i < N; i++)
            a[i] = br.readLine();

    }
    static void bfs(int sX, int sY){
        Queue<Integer> q = new LinkedList<>();
        q.add(sX);
        q.add(sY);
        visit[sX][sY]=true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dist[i][j] = -1;
            }
        }
        dist[sX][sY] = 1;   // 시작위치, 도착위치도 포함한다 (s 시작 -> 시작도 1개)

        while (!q.isEmpty()){
            int x = q.poll();
            int y = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visit[nx][ny]) continue;
                if(a[nx].charAt(ny)!='1') continue;

                visit[nx][ny] = true;
                dist[nx][ny] = dist[x][y]+1;
                q.add(nx);
                q.add(ny);
            }
        }
    }
    static void sol(){
        bfs(0,0);
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= M; j++) {
//                System.out.println(dist[i][j]);
//            }
//        }
        System.out.println(dist[N-1][M-1]);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
