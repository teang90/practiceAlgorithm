package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_14502_연구소2 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    static int answer;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");

        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        map = new int[N][M];
        visited = new boolean[N][M];

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
        System.out.println(answer);
    }

    static void sol() {
        constructWall(0, 0);
    }

    // 조합으로 탐색: startIdx부터만 탐색 → 중복 제거
    static void constructWall(int wallCnt, int start) {
        if (wallCnt == 3) {
            spreadVirus();
            return;
        }

        for (int idx = start; idx < N * M; idx++) {
            int x = idx / M;
            int y = idx % M;

            if (map[x][y] == 0) {
                map[x][y] = 1;
                constructWall(wallCnt + 1, idx + 1);
                map[x][y] = 0;
            }
        }
    }

    static void spreadVirus() {
        boolean[][] visited = new boolean[N][M];
        int[][] copy = new int[N][M];

        // map 복사 (바이러스 퍼뜨리기 위해)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = map[i][j];
            }
        }

        Queue<int[]> q = new LinkedList<>();

        // 초기 바이러스 위치 큐에 등록
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == 2) {
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        // BFS
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny]) continue;
                if (copy[nx][ny] != 0) continue;

                visited[nx][ny] = true;
                copy[nx][ny] = 2;
                q.add(new int[]{nx, ny});
            }
        }

        // 안전영역 계산
        int tmp = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == 0) tmp++;
            }
        }

        answer = Math.max(answer, tmp);
    }
}