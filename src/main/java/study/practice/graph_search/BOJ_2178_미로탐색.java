package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2178_미로탐색 {
    static int[][] dir = {{0,1},{0,-1},{-1,0},{1,0}};
    static String[] map ;
    static int[][] distance;
    static int N, M;
    static boolean[][] visited;
    // 시작위치, 도착위치도 한칸으로 샘한다.
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        distance = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];
        map = new String[N+1];
        for (int i = 1; i <= N; i++) {
            map[i] = " "+br.readLine();
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
        System.out.println(distance[N][M]);
    }
    
    static void sol(){
        bfs(1,1);
    }

    static void bfs(int x, int y){
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        q.add(y);
        visited[x][y] = true;
        distance[x][y] = 1;

        while (!q.isEmpty()){
            Integer nx = q.poll();
            Integer ny = q.poll();
            for (int i = 0; i < 4; i++) {
                int _nx = nx + dir[i][0];
                int _ny = ny + dir[i][1];
                if(_nx<1||_ny<1||_nx>N||_ny>M) continue;
                if(visited[_nx][_ny]) continue;
                if(map[_nx].charAt(_ny)!='1') continue;
                q.add(_nx);
                q.add(_ny);
                visited[_nx][_ny] = true;
                distance[_nx][_ny] = distance[nx][ny]+1;
            }

        }

    }


}
