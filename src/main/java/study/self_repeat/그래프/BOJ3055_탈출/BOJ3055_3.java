package study.self_repeat.그래프.BOJ3055_탈출;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int R, C;
    static boolean[][] visit;
    static int[][] dist_h, dist_w, dir = {{1,0},{-1,0},{0,-1},{0,1}};
    static String[] map;
    static void input() throws Exception {
        // R 행, C 열 / 빈곳 . / 물 * / 돌 X (통과 X)/ 비버 굴 D / 고슴도치 S
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new String[R];
        for (int i = 0; i < R; i++) map[i] = br.readLine();
        visit = new boolean[R][C];
        dist_h = new int[R][C];
        dist_w = new int[R][C];
    }
    static void bfs_water() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dist_w[i][j] = -1;
                if(map[i].charAt(j)=='*'){
                    dist_w[i][j] = 0;
                    visit[i][j] = true;
                    q.add(i);
                    q.add(j);
                }
            }
        }

        while (!q.isEmpty()) {
            int _x = q.poll();
            int _y = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = _x + dir[k][0];
                int ny = _y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if(visit[nx][ny]) continue;
                if(map[nx].charAt(ny) == 'X' || map[nx].charAt(ny) == 'D') continue;
                visit[nx][ny] = true;
                q.add(nx);
                q.add(ny);
                dist_w[nx][ny] = dist_w[_x][_y] + 1;
            }
        }
    }

    static void bfs_hedgehog(){
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dist_h[i][j] = -1;
                visit[i][j] = false;
                if(map[i].charAt(j)=='S'){
                    dist_h[i][j] = 0;
                    visit[i][j] = true;
                    q.add(i);
                    q.add(j);
                }
            }
        }

        while (!q.isEmpty()) {
            int _x = q.poll();
            int _y = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = _x + dir[k][0];
                int ny = _y + dir[k][1];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if(visit[nx][ny]) continue;
                if(map[nx].charAt(ny) != '.' && map[nx].charAt(ny) != 'D') continue;
                if(dist_w[nx][ny] != -1 && dist_h[_x][_y] + 1 >= dist_w[nx][ny]) continue;
                visit[nx][ny] = true;
                q.add(nx);
                q.add(ny);
                dist_h[nx][ny] = dist_h[_x][_y] + 1;
            }

        }

    }
    static void sol() {
        bfs_water();
        bfs_hedgehog();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i].charAt(j)=='D'){
                    System.out.println(dist_h[i][j]==-1?"KAKTUS":dist_h[i][j]);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();

    }
}
