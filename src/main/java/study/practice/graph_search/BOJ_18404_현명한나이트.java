package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;

public class BOJ_18404_현명한나이트 {
    private static int[][] dir = {{1,2},{2,1},{-1,2},{-2,1},{1,-2},{2,-1},{-1,-2},{-2,-1}};
    private static int N, M;
    static int[][] enemy;
    static int knightX, knightY;
    static int dist[][];

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        String[] split2 = br.readLine().split("\\s+");
        knightX = Integer.parseInt(split2[0]);
        knightY = Integer.parseInt(split2[1]);

        dist = new int[N+1][N+1];
        for (int i = 0; i < N+1; i++) dist[i][i] = -1;

        enemy = new int[M][2];
        for (int i = 0; i < M; i++) {
            String[] split1 = br.readLine().split("\\s+");
            enemy[i][0]=Integer.parseInt(split1[0]);
            enemy[i][1]=Integer.parseInt(split1[1]);
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    static void sol(){
        bfs();
        StringBuilder sb = new StringBuilder();
        for (int[] enemyLocation : enemy)
            sb.append(dist[enemyLocation[0]][enemyLocation[1]]).append(" ");

        System.out.println(sb);
    }

    static void bfs(){
        Queue<Integer> q = new java.util.LinkedList<>();
        q.add(knightX);
        q.add(knightY);
        dist[knightX][knightY] = 0;

        while (!q.isEmpty()){
            int x = q.poll();
            int y = q.poll();

            for (int[] dir : dir) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if(nx < 1 || ny < 1 || nx > N || ny > N) continue;
                if(dist[nx][ny] > 0) continue;
                dist[nx][ny] = dist[x][y]+1;
                q.add(nx);
                q.add(ny);
            }
        }


    }

}
