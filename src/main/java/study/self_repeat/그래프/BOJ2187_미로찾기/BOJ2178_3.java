package study.self_repeat.그래프.BOJ2187_미로찾기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2178_3 {
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
        for (int i = 0; i < N; i++) a[i] = br.readLine();
    }
    static void bfs(int sX, int sY){
      Queue<Integer> q = new LinkedList<>();
      q.add(sX);
      q.add(sY);
      visit[sX][sY] = true;
      dist[sX][sY] = 1;

      while (!q.isEmpty()){
          int _x = q.poll();
          int _y = q.poll();
          for (int i = 0; i < 4; i++) {
              int nx = _x + dir[i][0];
              int ny = _y + dir[i][1];

              if(nx <0 || ny <0 || nx>N-1 || ny>M-1) continue;
              if(visit[nx][ny]) continue;
              if(a[nx].charAt(ny)!='1') continue;

              visit[nx][ny] = true;
              dist[nx][ny] = dist[_x][_y]+1;
              q.add(nx);
              q.add(ny);
          }
      }

    }
    static void sol(){
        bfs(0,0);
        System.out.println(dist[N-1][M-1]);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
