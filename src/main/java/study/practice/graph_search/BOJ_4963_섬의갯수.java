package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_4963_섬의갯수 {
    static int[][] dir = {
            {-1,0}, {1,0}, {0,-1}, {0,1},
            {-1,-1}, {-1,1}, {1,-1}, {1,1}
    };

    static int[][] map;
    static boolean[][] visited;
    static int w, h;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void input() throws Exception {
        String[] split = br.readLine().split(" ");
        w = Integer.parseInt(split[0]); // 열
        h = Integer.parseInt(split[1]); // 행

        // ✔ 행 = h, 열 = w
        visited = new boolean[h][w];
        map = new int[h][w];

        for (int i = 0; i < h; i++) {
            String[] split1 = br.readLine().split(" ");
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(split1[j]);
            }
        }
    }

    static void sol(){
        int answer = 0;

        for (int i = 0; i < h; i++) {      // row
            for (int j = 0; j < w; j++) {  // col
                if (!visited[i][j] && map[i][j] == 1) {
                    dfs(i, j);
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }

    static void dfs(int x, int y){
        visited[x][y] = true;

        for (int i = 0; i < 8; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            // ✔ row < h, col < w
            if (nx < 0 || ny < 0 || nx > h-1 || ny > w-1)
//            if (nx < 0 || ny < 0 || nx > w-1 || ny > h-1)
                continue;

            if (visited[nx][ny])
                continue;

            if (map[nx][ny] != 1)
                continue;

            dfs(nx, ny);
        }
    }

    public static void main(String[] args) throws Exception {
        while (true){
            input();
            if (w == 0 && h == 0) break;
            sol();
        }
    }
}
