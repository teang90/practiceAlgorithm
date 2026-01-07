package study.self_repeat.그래프.BOJ3055_탈출;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055_5 {
    private static int R, C;
    private static String[] map;
    private static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    private static boolean[][] visit;
    private static int[][] dist_water;
    private static int[][] dist_heghog;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        R = Integer.parseInt(split[0]);     // 행 갯수
        C = Integer.parseInt(split[1]);     // 열 갯수
        map = new String[R];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine();
        }

        visit = new boolean[R][C];
        dist_water = new int[R][C];
        dist_heghog = new int[R][C];
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }


    static void bfs_water() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dist_water[i][j] = -1;
                if(map[i].charAt(j)=='*'){
                    visit[i][j] = true;
                    dist_water[i][j] = 0;
                    q.add(i);
                    q.add(j);
                }
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dir[i][0] + x;
                int ny = dir[i][1] + y;
                if(nx<0 || ny <0|| nx > R-1 || ny > C-1) continue;
                if(visit[nx][ny]) continue;
                if(map[nx].charAt(ny)!='.') continue;
                visit[nx][ny] = true;
                dist_water[nx][ny] = dist_water[x][y]+1;
                q.add(nx);
                q.add(ny);
            }
        }
    }

    static void bfs_hedgehog() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                visit[i][j] = false;
                dist_heghog[i][j] = -1;
                if(map[i].charAt(j)=='S'){
                    visit[i][j] = true;
                    dist_heghog[i][j] = 0;
                    q.add(i);
                    q.add(j);
                }
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dir[i][0] + x;
                int ny = dir[i][1] + y;
                if(nx<0 || ny <0|| nx > R-1 || ny > C-1) continue;
                if(visit[nx][ny]) continue;
                if(map[nx].charAt(ny) != '.' && map[nx].charAt(ny) != 'D') continue;
                if(dist_water[nx][ny] != -1 && dist_water[nx][ny] <= dist_heghog[x][y] + 1) continue;
                visit[nx][ny] = true;
                dist_heghog[nx][ny] = dist_heghog[x][y] + 1;
                q.add(nx);
                q.add(ny);
            }
        }
    }

    static void sol() {
        bfs_water();
        bfs_hedgehog();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i].charAt(j)=='D'){
                    System.out.println(dist_heghog[i][j]==-1?"KAKTUS":dist_heghog[i][j]);
                }
            }
        }
    }
}
