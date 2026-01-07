package study.self_repeat.그래프.BOJ2667_단지번호;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2667_5 {
    private static int N;
    private static boolean[][] visit;
    private static List<Integer> group = new ArrayList<>();
    private static String[] map;
    private static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    private static int roomCnt = 0;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.valueOf(br.readLine().trim());
        visit = new boolean[N][N];
        map = new String[N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().trim();
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
        Collections.sort(group);
        System.out.println(group.size());
        for (int y: group) {
            System.out.println(y);
        }
    }
    static void sol(){
        // 처음에 아래처럼 시작하는 의미를 이해하자 (구현력)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++){
                if(map[i].charAt(j)=='1' && !visit[i][j]){
                    roomCnt=0;

                    bfs(i,j);
                    group.add(roomCnt);
                }
            }
        }
    }

    static void bfs(int x, int y){
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        q.add(y);
        visit[x][y]=true;
        roomCnt++;

        while (!q.isEmpty()){
            Integer _x = q.poll();
            Integer _y = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = _x + dir[k][0];
                int ny = _y + dir[k][1];
                if(nx<0 || ny<0 || nx > N-1 || ny > N-1 ) continue;
                if(visit[nx][ny]) continue;
                if(map[nx].charAt(ny) != '1') continue;

                visit[nx][ny]= true;
                q.add(nx);
                q.add(ny);
                roomCnt++;
            }
        }
    }

    static void dfs(int x, int y){
        roomCnt++;
        visit[x][y]=true;
        for (int k=0; k<4; k++){
            int nx = x + dir[k][0];
            int ny = y + dir[k][1];
            if(nx<0 || ny<0 || nx > N-1 || ny > N-1 ) continue;
            if(visit[nx][ny]) continue;
            if(map[nx].charAt(ny) != '1') continue;
            visit[nx][ny] = true;
            dfs(nx, ny);
        }
    }
}
