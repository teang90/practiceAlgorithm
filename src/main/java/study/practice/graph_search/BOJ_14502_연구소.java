package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ_14502_연구소 {
    static int N, M;// 세로 N, 가로 M
    static int[][] map;
    static boolean[][] visited;
    static int wallLimit = 3;
    static List<Integer>[] walls;
    static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    static int answer, caseCount;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        map = new int[N][M];
        visited = new boolean[N][M];
        walls = new ArrayList[N*M + 1];
        for (int i = 0; i <N*M; i++)
            walls[i] = new ArrayList<>();

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
        // 벽을 세워본다. -> 완전탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==0){
                    walls[caseCount].add(i);
                    walls[caseCount].add(j);
                    caseCount++;
                }
            }
        }

        constructWall(0, 0);

        System.out.println(answer);
    }

    static void constructWall(int wallCnt, int cnt){
        // 벽 3개를 다 세우면, 바이러스를 퍼뜨려본다. -> bfs
        if(wallCnt == wallLimit) {
            spreadVirus();
            return;
        }

        if(cnt >= caseCount) return;

        List<Integer> wall = walls[cnt];
        Integer x = wall.get(0);
        Integer y = wall.get(1);

        map[x][y]=1;
        constructWall(wallCnt+1, cnt+1);
        map[x][y]=0;
        constructWall(wallCnt, cnt+1);

    }

    static void spreadVirus(){
        Queue<Integer> q = new java.util.LinkedList<>();
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

        while (!q.isEmpty()){
            int x = q.poll();
            int y = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] != 0) continue;
                q.add(nx);
                q.add(ny);
                visited[nx][ny]=true;
            }
        }

        int tmpAnswer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!visited[i][j] && map[i][j]==0) tmpAnswer++;
            }
        }

        answer = Math.max(answer, tmpAnswer);
    }
}

