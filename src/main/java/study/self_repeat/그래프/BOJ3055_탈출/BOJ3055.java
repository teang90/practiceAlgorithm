package study.self_repeat.그래프.BOJ3055_탈출;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3055 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int R, C;    // R: 행, C: 열
//    static int[][] dist;
    static boolean[][] visit;
    static int[][] dist_water, dist_hedgehog, dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static String[] forest;
    static StringTokenizer st;

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        forest = new String[R];
        dist_water = new int[R][C];
        dist_hedgehog = new int[R][C];
        visit = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            forest[i] = br.readLine();
        }

    }
    static void bfs_water(){
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dist_water[i][j] = -1;
                if(forest[i].charAt(j)=='*'){
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
            for (int i = 0; i < 4; i++) {
                int nX = dir[i][0] + x;
                int nY = dir[i][1] + y;

                if(0 > nX || nX >= R || 0 > nY || nY >= C) continue;
                if(visit[nX][nY]) continue;
                if(forest[nX].charAt(nY)=='X' || forest[nX].charAt(nY) == 'D') continue;
                visit[nX][nY] = true;
                dist_water[nX][nY] = dist_water[x][y]+1;
                q.add(nX);
                q.add(nY);
            }
        }
    }

    static void bfs_hedgergoh(){
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                visit[i][j] = false;
                dist_hedgehog[i][j] = -1;
                if(forest[i].charAt(j)=='S'){
                    visit[i][j] = true;
                    dist_hedgehog[i][j] = 0;
                    q.add(i);
                    q.add(j);
                }
            }
        }

        while (!q.isEmpty()){
            int x = q.poll();
            int y = q.poll();
            for (int i = 0; i < 4; i++) {
                int nX = dir[i][0] + x;
                int nY = dir[i][1] + y;

                if(0 > nX || nX >= R || 0 > nY || nY >= C) continue;
                if(visit[nX][nY]) continue;
                if(forest[nX].charAt(nY) != '.' && forest[nX].charAt(nY) != 'D') continue;
                if(dist_water[nX][nY] != -1 && dist_hedgehog[x][y]+1 >= dist_water[nX][nY]) continue;
                visit[nX][nY] = true;
                dist_hedgehog[nX][nY] = dist_hedgehog[x][y]+1;
                q.add(nX);
                q.add(nY);
            }
        }
    }
    static void sol() throws Exception {
        bfs_water();
        bfs_hedgergoh();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(forest[i].charAt(j)=='D'){
                    if(dist_hedgehog[i][j]==-1) System.out.println("KAKTUS");
                    else System.out.println(dist_hedgehog[i][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
