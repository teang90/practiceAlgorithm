package study.self_repeat.그래프.BOJ3055_탈출;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ3055_1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int R, C;
    static String[] a;
    static boolean[][] visit;
    static int[][] dist_water;
    static int[][] dist_hdg;
    static int[][] dir = {{1,0},{-1,0},{0,-1},{0,1}};
    static void input() throws Exception {
        String[] arr = br.readLine().split(" ");
        R = Integer.parseInt(arr[0]);
        C = Integer.parseInt(arr[1]);
        a = new String[R];
        for (int i = 0; i < R; i++) a[i] = br.readLine();
        visit = new boolean[R][C];
        dist_water = new int[R][C];
        dist_hdg = new int[R][C];
    }
    static void bfs_water(){
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dist_water[i][j] = -1;
                if(a[i].charAt(j)=='*'){
                    visit[i][j] = true;
                    dist_water[i][j] = 0;
                    q.add(i);
                    q.add(j);
                }
            }
        }

        while (!q.isEmpty()){
            int x = q.poll();
            int y = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if(nx<0||ny<0||nx>=R||ny>=C) continue;
                if(visit[nx][ny]) continue;
                if(a[nx].charAt(ny)!='.') continue;
                visit[nx][ny] = true;
                dist_water[nx][ny] = dist_water[x][y]+1;
                q.add(nx);
                q.add(ny);
            }
        }
    }
    static void bfs_hdg(){
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dist_hdg[i][j] = -1;
                visit[i][j] = false;
                if(a[i].charAt(j)=='S'){
                    visit[i][j] = true;
                    dist_hdg[i][j] = 0;
                    q.add(i);
                    q.add(j);
                }
            }
        }
        while (!q.isEmpty()){
            int x = q.poll();
            int y = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if(nx<0||ny<0||nx>=R||ny>=C) continue;
                if(visit[nx][ny]) continue;
                if(a[nx].charAt(ny)!='.' && a[nx].charAt(ny)!='D') continue;
                if(dist_water[nx][ny]!=-1 && dist_hdg[x][y]>=dist_water[nx][ny]-1) continue;
                visit[nx][ny] = true;
                q.add(nx);
                q.add(ny);
                dist_hdg[nx][ny] = dist_hdg[x][y]+1;
            }
        }
    }
    static void sol(){
        bfs_water();
        bfs_hdg();
        for (int i=0;i<R;i++){
            for (int j=0;j<C;j++){
                if (a[i].charAt(j) == 'D'){
                    if (dist_hdg[i][j] == -1) System.out.println("KAKTUS");
                    else System.out.println(dist_hdg[i][j]);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
