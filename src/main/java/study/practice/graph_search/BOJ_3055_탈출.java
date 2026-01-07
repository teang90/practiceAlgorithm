package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_3055_탈출 {

    static int R, C;
    static String[] map;    // R
    static boolean[][] visited;
    static int[][] dist_hog, dist_water;
    static int[][] dir ={{0,1},{0,-1},{1,0},{-1,0}};

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        R = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);
        map = new String[R];
        for (int i = 0; i < R; i++) map[i] = br.readLine();
        dist_hog = new int[R][C];
        dist_water = new int[R][C];
        visited = new boolean[R][C];
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    static void sol() {
        bfs_water();
        bfs_hog();
        print();
    }

    static void bfs_water(){
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                visited[i][j] = false;
                dist_water[i][j]=-1;
                if(map[i].charAt(j)=='*'){
                    q.add(i);
                    q.add(j);
                    visited[i][j]=true;
                    dist_water[i][j]=0;
                }
            }
        }

        while (!q.isEmpty()){
            Integer x = q.poll();
            Integer y = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if(nx<0 || ny<0 || nx>R-1 || ny>C-1) continue;
                if(visited[nx][ny]) continue;
                if(map[nx].charAt(ny)!='.') continue;
                q.add(nx);
                q.add(ny);
                visited[nx][ny]=true;
                dist_water[nx][ny] = dist_water[x][y]+1;
            }
        }
    }

    static void bfs_hog(){
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                visited[i][j] = false;
                dist_hog[i][j]=-1;
                if(map[i].charAt(j)=='S'){
                    q.add(i);
                    q.add(j);
                    visited[i][j]=true;
                    dist_hog[i][j]=0;
                }
            }
        }

        while (!q.isEmpty()){
            Integer x = q.poll();
            Integer y = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];

                if(nx<0 || ny<0 || nx>R-1 || ny>C-1) continue;
                if(visited[nx][ny]) continue;
//                if(map[nx].charAt(ny) != '.' && map[nx].charAt(ny)!='D') continue;
                if(map[nx].charAt(ny)=='*' || map[nx].charAt(ny)=='X') continue;
                if(dist_water[nx][ny]!=-1 && dist_water[nx][ny] <= dist_hog[x][y] + 1) continue; // 이미 물이 차있으면 못간다.

                q.add(nx);
                q.add(ny);
                visited[nx][ny]=true;
                dist_hog[nx][ny] = dist_hog[x][y]+1;
            }
        }
    }

    static void print(){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i].charAt(j)=='D'){
                    System.out.println(dist_hog[i][j]==-1? "KAKTUS" : dist_hog[i][j]);
                }
            }
        }
    }

}
