package study.self_repeat.그래프.BOJD14502_바이러스_퍼트리기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14502_7 {
    private static int N, M;
    private static boolean[][] visit;
    private static int[][] dir = {{1,0},{-1,0}, {0,1}, {0,-1}};
    private static int[][] map;
    private static int WAR = 3, safezoneCnt = Integer.MIN_VALUE, doingCnt=0;
    private static List<Integer>[] blanks;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        visit = new boolean[N][M];
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] split1 = br.readLine().split("\\s+");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.valueOf(split1[j]);
            }
        }

        blanks = new ArrayList[N*M+1]; // 빈방의 일렬 갯수
    }

    public static void main(String[] args) throws Exception {
        input();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0){
                    blanks[doingCnt] = new ArrayList<>();
                    blanks[doingCnt].add(i);
                    blanks[doingCnt].add(j);
                    doingCnt++;
                }
            }
        }
        constructWall(0, 0);
        System.out.println(safezoneCnt);
    }

    static void constructWall(int simCnt, int currWall){
        if(currWall == WAR){
            spreadVirus();
            return;
        }

        if(simCnt >= doingCnt) return;

        int x = blanks[simCnt].get(0);
        int y = blanks[simCnt].get(1);

        map[x][y] = 1;
        constructWall(simCnt+1, currWall+1);

        map[x][y] = 0;
        constructWall(simCnt+1, currWall);
    }

    static void spreadVirus(){
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = false;
                if(map[i][j] == 2){
                    q.add(i);
                    q.add(j);
                    visit[i][j] = true;
                }
            }
        }

        while(!q.isEmpty()){
            Integer x = q.poll();
            Integer y = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if(nx<0 || ny <0 || nx > N-1 || ny > M -1) continue;
                if(visit[nx][ny]) continue;
                if(map[nx][ny]!=0) continue;

                visit[nx][ny] = true;
                q.add(nx);
                q.add(ny);
            }
        }

        int tmpSafeZoneCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0 && !visit[i][j]){
                    tmpSafeZoneCnt++;
                }
            }
        }
        safezoneCnt = Math.max(safezoneCnt, tmpSafeZoneCnt);
    }






    
    
}
