package study.self_repeat.그래프.BOJD14502_바이러스_퍼트리기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14502_4 {
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
        Queue<Integer> q = new LinkedList<>();

        // 바이러스 위치에서 bfs 시작
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = false;
                if(space[i][j] == 2){
                    visit[i][j] = true;
                    q.add(i);
                    q.add(j);
                }
            }
        }

        while (!q.isEmpty()) {
            int xforBirusToMove = q.poll();
            int yforBirusToMove = q.poll();

            for (int k = 0; k < 4; k++) {
                int newX = xforBirusToMove + dir[k][0];
                int newY = yforBirusToMove + dir[k][1];

                if(newX < 0 || newY < 0 || newX >= N || newY >= M) continue;
                if(visit[newX][newY]) continue;
                if(space[newX][newY] != 0) continue;

                visit[newX][newY] = true;
                q.add(newX);
                q.add(newY);

            }

        }

        // 바이러스 확산이 끝나고 남아있는 안전 공간의 갯수를 확인한다.
        int safeZone = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(space[i][j] == 0 && !visit[i][j]){
                    safeZone++;
                }
            }
        }
        answer = Math.max(answer, safeZone);

    }


    /** 백트레킹으로 벽을 세울 수 있는 곳에 세워본다 */
    static void backtrack(int idx, int curWall){

        if(curWall == wall){
            // 벽 3개를 다 세웠다면 바이러스를 퍼트린다.
            bfs();
            return ;
        }
        // 벽을 세울곳이 더 없다면 종료
        if(idx >= cnt) return ;

        // 벽을 세워본다
        space[blank[idx].get(0)][blank[idx].get(1)] = 1;
        backtrack(idx+1, curWall+1);

        // 현재 idx 위치에는 벽을 안세워본다.
        space[blank[idx].get(0)][blank[idx].get(1)] = 0;
        backtrack(idx+1, curWall);
    }

    static void sol() {
//      벽을 세울 수 있는 곳(벽 후보군)을 blank에 수집
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(space[i][j] == 0) {
                    blank[cnt] = new ArrayList();
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
