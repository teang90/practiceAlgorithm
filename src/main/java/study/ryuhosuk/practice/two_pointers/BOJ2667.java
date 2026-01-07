package study.ryuhosuk.practice.two_pointers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2667 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, answer;
    static boolean[][] visit;
    static List<Integer> adj;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static String[] group;

    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        visit = new boolean[N][N];

        adj = new ArrayList();

        group = new String[N];
        for (int i = 0; i < N; i++) {
            group[i] = br.readLine();
        }

    }
    static void dfs(int x, int y){
        answer++;
        visit[x][y] = true;

        for (int i = 0; i < 3; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if(0 > nx || nx >= N || 0 > ny || ny >= N) continue;
            if(visit[nx][ny]) continue;
            if(group[nx].charAt(ny)=='0') continue;

            dfs(nx, ny);
        }

    }
    static int bfs(int x, int y) {
        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        q.add(y);
        visit[x][y] = true;

        while (!q.isEmpty()){
            int x1 = q.poll();
            int y1 = q.poll();
            count++;

            for (int i = 0; i < 4; i++) {
                int nx = x1 + dir[i][0];
                int ny = y1 + dir[i][1];

                if(0 > nx || nx >= N || 0 > ny || ny >= N) continue;
                if(visit[nx][ny]) continue;
                if(group[nx].charAt(ny)=='0') continue;

                q.add(nx);
                q.add(ny);
                visit[nx][ny] = true;
            }
        }
        return count;
    }

    static void sol(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(group[i].charAt(j)=='1' && !visit[i][j]){
//                    adj.add(bfs(i, j));
                    dfs(i, j);
                }
            }
        }


        System.out.println(adj.size());
        Collections.sort(adj);
        for (int y : adj) System.out.println(y);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
