package study.self_repeat.그래프.BOJD14502_바이러스_퍼트리기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14502_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, wall=3, cnt, answer = Integer.MIN_VALUE, B;
    static boolean[][] visit;
    static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    static int[][] space;
    static List<Integer>[] blank;

    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());   // 세로
        M = Integer.parseInt(st.nextToken());   // 가로
        visit = new boolean[N+1][M+1];
        space = new int[N+1][M+1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for (int j = 0; j < M; j++)
                space[i][j] = Integer.parseInt(st.nextToken());
        }
        blank = new ArrayList[N*M+1];
        // Arrays.fill(blank, new ArrayList<>()); -> 이거는 왜 안됬지????????

    }
    static void bfs() {
        // space 참조
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = false;
                if(space[i][j]==2){
                    visit[i][j] = true;
                    q.add(i);
                    q.add(j);
                }
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();
//            System.out.println(x+", "+y);
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visit[nx][ny]) continue;
                if(space[nx][ny] != 0) continue;
                visit[nx][ny] = true;
                q.add(nx);
                q.add(ny);
            }
        }

        int thisCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!visit[i][j] && space[i][j]==0){
                    thisCnt++;
                }
            }
        }
        answer = Math.max(answer, thisCnt);
    }
    static void backtrack(int idx, int curWall){
        if(curWall == wall){
            bfs();
            return ;
        }

        if(idx >= cnt) return ;

        space[blank[idx].get(0)][blank[idx].get(1)] = 1;    // 벽을 세우는 경우
        backtrack(idx+1, curWall+1);

        space[blank[idx].get(0)][blank[idx].get(1)] = 0;    // 벽을 세우지 않는 경우
        backtrack(idx+1, curWall);
    }
    static void sol() {
//        // 1) 백 트레킹
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(space[i][j]==0){
                    blank[cnt] = new ArrayList<>();
                    blank[cnt].add(i);
                    blank[cnt].add(j);
                    cnt++;
                }
            }
        }

        backtrack(0, 0);

        // 3) 2)*의 경우의 수가 최대인 경우를 구하기
        System.out.println(answer);

    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
