package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_7569_토마토 {
    static int[][] dir = {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};
    static int M, N, H; // M은 가로칸, N은 세로칸, H 층수
    static int[][][] map, distance;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        M = Integer.parseInt(split[0]);
        N = Integer.parseInt(split[1]);
        H = Integer.parseInt(split[2]);
        map = new int[M][N][H];
        distance = new int[M][N][H];
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                String[] split1 = br.readLine().split("\\s+");
                for (int m = 0; m < M; m++) {
                    map[m][n][h] = Integer.parseInt(split1[m]);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
    static void sol(){
        // bfs 돌려서 만약 0이 남아있다면 -1 출력, 0이 없다면 distance에서 가장 큰 값을 출력
        for (int h=0; h<H; h++)
            for(int n=0; n<N; n++)
                for (int m = 0; m < M; m++)
                    distance[m][n][h] = -1;

        bfs();

        int answer = 0;
        for (int h=0; h<H; h++){
            for(int n=0; n<N; n++){
                for (int m = 0; m < M; m++){
                    if(map[m][n][h]!=-1 && distance[m][n][h]== -1){
                        answer = -1;
                    }
                }
            }
        }

        int tmpMax = Integer.MIN_VALUE;
        if(answer!=-1){
            for (int h=0; h<H; h++){
                for(int n=0; n<N; n++){
                    for (int m = 0; m < M; m++){
                        if(distance[m][n][h] > tmpMax){
                            tmpMax = distance[m][n][h];
                        }
                    }
                }
            }
            answer = tmpMax;
        }

        System.out.println(answer);
    }
    static void bfs(){
        Queue<Integer> q = new LinkedList();
        for (int h=0; h<H; h++){
            for(int n=0; n<N; n++){
                for (int m = 0; m < M; m++) {
                    if(map[m][n][h]==1){
                        q.add(m);
                        q.add(n);
                        q.add(h);
                        distance[m][n][h] = 0;
                    }
                }
            }
        }

        while (!q.isEmpty()){
            Integer nx = q.poll();
            Integer ny = q.poll();
            Integer nh = q.poll();
            for (int[] dir : dir) {
                int _nx = nx + dir[0];
                int _ny = ny + dir[1];
                int _nh = nh + dir[2];
                if(_nx<0 || _ny<0||_nh<0||_nx>M-1||_ny>N-1||_nh>H-1) continue;
                if(distance[_nx][_ny][_nh] != -1) continue;
                if(map[_nx][_ny][_nh] == -1) continue;
                distance[_nx][_ny][_nh] = distance[nx][ny][nh]+1;
                q.add(_nx);
                q.add(_ny);
                q.add(_nh);
            }
        }

    }
}
