package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_14502_연구소3 {
    static int N, M, wallLimit = 3, caseCount = 0, answer=Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer>[] wallsCase;
    static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        visited = new boolean[N][M];

        wallsCase = new ArrayList[N*M];
        for (int i = 0; i <N*M; i++) wallsCase[i] = new ArrayList<>();

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] split1 = br.readLine().split("\\s+");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(split1[j]);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    static void sol() {
        // 경우의 수 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==0){
                    wallsCase[caseCount].add(i);
                    wallsCase[caseCount].add(j);
                    caseCount++;
                }
            }
        }

        constuctWall(0, 0);

        System.out.println(answer);
    }

    static void constuctWall(int _caseCount, int wallCnt){
        if(wallCnt==wallLimit){
            spreadVirus();
            return;
        }

        if(_caseCount >= caseCount)
            return;

        Integer x = wallsCase[_caseCount].get(0);
        Integer y = wallsCase[_caseCount].get(1);

        map[x][y]=1;
        constuctWall(_caseCount+1, wallCnt+1);

        map[x][y]=0;
        constuctWall(_caseCount+1, wallCnt);

    }

    static void spreadVirus(){
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j]=false;
                if(map[i][j]==2){
                    q.add(i);
                    q.add(j);
                    visited[i][j]=true;
                }
            }
        }

        while(!q.isEmpty()){
            Integer x = q.poll();
            Integer y = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];

                if(nx <0|| ny <0|| nx > N-1 || ny > M-1) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny]!=0) continue;

                q.add(nx);
                q.add(ny);
                visited[nx][ny]=true;
            }
        }

        int safeZone = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if(!visited[i][j]&&map[i][j]==0)
                    safeZone++;

        answer = Math.max(answer, safeZone);
    }

}