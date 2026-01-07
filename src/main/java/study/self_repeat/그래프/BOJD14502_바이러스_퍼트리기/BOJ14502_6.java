package study.self_repeat.그래프.BOJD14502_바이러스_퍼트리기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14502_6 {
    private final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N,M, cnt, answer = Integer.MIN_VALUE;
    private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    private static final int wall = 3;
    private static boolean[][] visit;
    private static int[][] map;
    private static List<Integer>[] blank;

    private static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visit = new boolean[N][M];
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split("\\s+");

            for (int j = 0; j < M; j++) {
                map[i][j]=Integer.parseInt(split[j]);
            }
        }

        blank = new ArrayList[N*M+1];
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
        System.out.println(answer);
    }

    private static void sol(){
        // 벽을 놓을 수 있는 공간을 모은다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]==0){
                    blank[cnt] = new ArrayList<>();
                    blank[cnt].add(i);
                    blank[cnt].add(j);
                    cnt++;
                }
            }
        }

        // 벽을 막기
        construckWall(0, 0);

        // 벽 3개로 다 막았으면
        // 바이러스 퍼뜨리기
        // 바이러스가 다 퍼지고 나서 남아있는 공간이 있나? 있다면 남은 공간이 몇개인지 확인
        // 시뮬레이팅 다해보고 가장 최대의 남은 공간의 갯수를 구하라
    }

    // idx: 현재 빈공간의 idx 값
    static void construckWall(int idx, int currentWall){
        if(currentWall == wall){
            spreadVirusByBfs();
            return;
        }

        if(idx >= cnt) return ;

        int x = blank[idx].get(0);
        int y = blank[idx].get(1);
        map[x][y] = 1;
        construckWall(idx+1, currentWall+1);

        map[x][y] = 0;
        construckWall(idx+1, currentWall);
    }

    static void spreadVirusByBfs(){
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = false;
                if(map[i][j]==2){
                    visit[i][j] = true;
                    q.add(i);
                    q.add(j);
                }
            }
        }

        while(!q.isEmpty()){
            int x = q.poll();
            int y = q.poll();
            for (int k = 0; k < 4; k++) {
                int nxtX = x + dir[k][0];
                int nxtY = y + dir[k][1];
                if(nxtX <0 || nxtY<0 || nxtX>N-1 || nxtY>M-1) continue;
                if(visit[nxtX][nxtY]) continue;
                if(map[nxtX][nxtY] != 0) continue;

                visit[nxtX][nxtY] = true;
                q.add(nxtX);
                q.add(nxtY);
            }
        }

        int tmpSafeZoneCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!visit[i][j] && map[i][j] == 0)
                    tmpSafeZoneCnt++;
            }
        }
        answer = Math.max(answer, tmpSafeZoneCnt);

    }
    
    
}
