package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_3184_양 {
    //. -> 빈필드, # -> 울타리, o -> 양, v -> 늑대
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static String[] map;
    static int R, C;    // 행, 열
    static boolean[][] visited;
    static int wolfs, totalWolfs;
    static int ships, totalShips;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        R = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);
        map = new String[R];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine();
        }
        visited = new boolean[R][C];
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    static void sol() throws Exception {
        // 일단 울타리로 구역을 나눈다.
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(!visited[i][j] && map[i].charAt(j)!='#'){
                    // 울타리 내에 양의수 늑대의 수를 계산한다.
                    wolfs=0;
                    ships=0;

                    // 한 영역 내의 늑대의 수가 크거나 같으면 해당 영역의 양의 수는 0으로 계산한다.
                    // 한 영역 내의 양의 수가 더 크면 해당 영역의 늑대의 수는 0 으로 계산한다.
                    dfs(i, j);

                    if(wolfs >= ships) totalWolfs+= wolfs;
                    else totalShips += ships;
                }
            }
        }

        // 각 영역별로 남아있는 양의 수를 합산한다.
        System.out.println(totalShips+" "+totalWolfs);
    }

    static void dfs(int x, int y){
        visited[x][y]=true;

        if(map[x].charAt(y)=='v') wolfs++;
        if(map[x].charAt(y)=='o') ships++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if(nx < 0 || ny < 0 || nx > R-1 || ny > C-1) continue;
            if(visited[nx][ny]) continue;
            if(map[nx].charAt(ny)=='#') continue;
            dfs(nx, ny);
        }

    }

}
