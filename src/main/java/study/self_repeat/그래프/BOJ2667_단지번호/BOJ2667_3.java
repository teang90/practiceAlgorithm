package study.self_repeat.그래프.BOJ2667_단지번호;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2667_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, group_cnt;
    static int[][] dir = {{1,0},{-1,0},{0,-1},{0,1}};
    static boolean[][] visit;
    static String[] a;
    static List<Integer> group = new ArrayList<>();
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        visit = new boolean[N][N];
        a = new String[N];
        for (int i = 0; i < N; i++) a[i] = br.readLine().trim();
    }

    static void dfs(int x, int y){
        visit[x][y]=true;
        group_cnt++;

        for (int k=0; k<4; k++){
            int newX = x + dir[k][0];
            int newY = y + dir[k][1];
            if(newX<0 || newY <0 || newX > N-1 || newY > N-1 ) continue;
            if(visit[newX][newY]) continue;
            if(a[newX].charAt(newY)=='0') continue;

            visit[newX][newY] = true;
            dfs(newX, newY);

        }

    }

    static void bfs(int x, int y){
        Queue<Integer> q = new LinkedList<>();
        visit[x][y] = true;
        q.add(x);
        q.add(y);
        group_cnt++;

        while (!q.isEmpty()){
            int toX = q.poll();
            int toY = q.poll();

            for (int i=0; i<4; i++){
                int newX = toX + dir[i][0];
                int newY = toY + dir[i][1];
                if(newX<0 || newY <0 || newX > N-1 || newY > N-1 ) continue;
                if(visit[newX][newY]) continue;
                if(a[newX].charAt(newY)=='0') continue;

                visit[newX][newY]=true;
                q.add(newX);
                q.add(newY);
                group_cnt++;
            }
        }
    }
    static void sol(){
        // 움직이다가 처음 방문하는 room이면 bfs
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(a[i].charAt(j)=='1' && !visit[i][j]){
                    group_cnt = 0;
//                    bfs(i,j);
                    dfs(i,j);
                    group.add(group_cnt);
                }
            }
        }
        Collections.sort(group);
        System.out.println(group.size());
        for (int y: group) {
            System.out.println(y);
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
