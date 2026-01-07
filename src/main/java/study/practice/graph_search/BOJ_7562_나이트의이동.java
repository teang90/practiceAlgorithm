package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_7562_나이트의이동 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] dir = {{1,2},{2,1},{-1,2},{-2,1},{1,-2},{2,-1},{-1,-2},{-2,-1}};
    private static int TEST_CASE, I;
    private static int[] currentLocation, destLocation;
    private static boolean[][] visited;
    private static int[][] distance;

    static void input() throws Exception {
        I = Integer.parseInt(br.readLine());
        visited = new boolean[I][I];
        distance = new int[I][I];

        String[] currLocArr = br.readLine().split("\\s+");
        currentLocation = new int[]{Integer.parseInt(currLocArr[0]), Integer.parseInt(currLocArr[1])};

        String[] destLocArr = br.readLine().split("\\s+");
        destLocation = new int[]{Integer.parseInt(destLocArr[0]), Integer.parseInt(destLocArr[1])};
    }

    public static void main(String[] args) throws Exception {
        TEST_CASE = Integer.parseInt(br.readLine());
        for (int i = 0; i < TEST_CASE; i++){
            input();
            sol();
            System.out.println(distance[destLocation[0]][destLocation[1]]);
        }
    }
    static void sol(){
        for (int i = 0; i < I; i++)
            for (int j = 0; j < I; j++)
                distance[i][j] = -1;

        bfs(currentLocation[0], currentLocation[1]);
    }

    static void bfs(int x, int y){
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        q.add(y);
        visited[x][y] = true;
        distance[x][y] = 0;

        while (!q.isEmpty()){
            int nx = q.poll();
            int ny = q.poll();

            for (int[] dir : dir) {
                int _nx = nx + dir[0];
                int _ny = ny + dir[1];

                if(_nx<0 || _ny < 0 || _nx > I-1 || _ny > I-1) continue;
                if(visited[_nx][_ny]) continue;

                visited[_nx][_ny]=true;
                distance[_nx][_ny] = distance[nx][ny] + 1;
                q.add(_nx);
                q.add(_ny);
            }

        }

    }

}
