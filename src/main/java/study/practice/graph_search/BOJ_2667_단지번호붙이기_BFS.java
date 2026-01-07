package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2667_단지번호붙이기_BFS {
    static int N;
    static String[] map;
    static boolean[][] visited;
    static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
    static int answer;
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
                    bfs(i, j);
                    answer++;
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

    static void bfs(int x, int y){
        int cellCnt = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        q.add(y);
        visited[x][y] = true;

        while (!q.isEmpty()){
            Integer _x = q.poll();
            Integer _y = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = _x + dir[i][0];
                int ny = _y + dir[i][1];

                if(nx <0 || ny <0 || nx > N-1 || ny > N-1) continue;
                if(visited[nx][ny]) continue;
                if(map[nx].charAt(ny)!='1') continue;

                q.add(nx);
                q.add(ny);
                visited[nx][ny] = true;
                cellCnt++;
            }

        }

        group.add(cellCnt);
    }
}
