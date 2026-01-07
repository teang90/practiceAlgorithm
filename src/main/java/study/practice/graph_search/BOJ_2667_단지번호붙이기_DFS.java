package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2667_단지번호붙이기_DFS {
    static int N;
    static String[] map;
    static boolean[][] visited;
    static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
    static int answer, eachCellCnt;
    static List<Integer> group;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];
        group = new ArrayList<>();
        map = new String[N];
        for (int i = 0; i < N; i++)
            map[i] = br.readLine();

    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    static void sol() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i].charAt(j)=='1' && !visited[i][j]){
                    eachCellCnt=0;
                    dfs(i, j);
                    answer++;
                    group.add(eachCellCnt);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Collections.sort(group);
        for (int i = 0; i < group.size(); i++)
            sb.append(group.get(i)).append("\n");

        System.out.println(answer);
        System.out.println(sb);
    }

    static void dfs(int x, int y){
        visited[x][y] = true;
        eachCellCnt++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if(nx<0 || ny <0 || nx > N-1 || ny > N-1) continue;
            if(map[nx].charAt(ny)!='1') continue;
            if(visited[nx][ny]) continue;

            dfs(nx, ny);
            // 여기서 eachCellCnt++l 했다가 틀림
        }
    }
}
