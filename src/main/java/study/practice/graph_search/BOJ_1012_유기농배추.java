package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class BOJ_1012_유기농배추 {
    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    static int M, N, K;
    static int[][] map;
    static boolean[][] visited;
    static int testCastCnt, answer;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCastCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < testCastCnt; i++) {
            String[] input = br.readLine().split(" ");
            M = Integer.parseInt(input[0]);
            N = Integer.parseInt(input[1]);
            K = Integer.parseInt(input[2]);
            map = new int[M][N];
            visited = new boolean[M][N];
            for (int j = 0; j < K; j++) {
                String[] temp = br.readLine().split(" ");
                map[Integer.valueOf(temp[0])][Integer.valueOf(temp[1])] = 1;
            }
            answer = 0;
            sol();
        }
    }

    static void sol() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 1 && !visited[i][j]){
                    dfs(i, j);
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    static void dfs(int x, int y){
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if(nx <0 || ny < 0 || nx > M-1 || ny > N-1) continue;
            if(visited[nx][ny]) continue;
            if(map[nx][ny] != 1) continue;

            dfs(nx, ny);
        }

    }

    public static void main(String[] args) throws Exception {
        input();
    }
}
