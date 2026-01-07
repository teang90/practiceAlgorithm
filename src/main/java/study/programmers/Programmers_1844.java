package study.programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_1844 {
    public int solution(int[][] maps) {
        int[][] dist = new int[maps.length][maps[0].length];
        for (int i = 0; i < maps.length; i++) {
            Arrays.fill(dist[i], -1);
        }

        bfs(0,0, dist, maps);

        return dist[maps.length-1][maps[0].length-1];
    }
    public void bfs(int Xi, int Yi, int[][] dist, int[][] maps){
        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
        Queue<Integer> q = new LinkedList<>();
        dist[Xi][Yi] = 1;
        q.add(Xi);
        q.add(Yi);

        while (!q.isEmpty()){
            int x = q.poll();
            int y = q.poll();
            for (int i = 0; i < 4; i++) {
                int Xn = x + dir[i][0];
                int Yn = y + dir[i][1];
                if(Xn < 0 || Yn <0 || Xn >= maps.length || Yn >= maps[0].length) continue;
                if(dist[Xn][Yn] >= 0 || maps[Xn][Yn]==0) continue;

                dist[Xn][Yn] = dist[x][y] + 1;
                q.add(Xn);
                q.add(Yn);
            }
        }
    }
    public static void main(String[] args) {
        Programmers_1844 p = new Programmers_1844();
        int res = p.solution(new int[][]{{1,0,1,1,1}, {1,0,1,0,1}, {1,0,1,1,1}, {1,1,1,0,1}, {0,0,0,0,1}});
        System.out.println(res);
    }
}
