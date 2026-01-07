package study.self_repeat.그래프.BOJD14502_바이러스_퍼트리기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ14502_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, B, ans;
    static boolean[][] visit;
    static int[][] blank;   // 빈방
    static int [][] A;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static void input() throws Exception {
        String[] arr = br.readLine().split(" ");
        N = Integer.parseInt(arr[0]);
        M = Integer.parseInt(arr[1]);
        visit = new boolean[N][M];
        blank = new int[N*M+1][2];
        A = new int[N][M];
        for (int i = 0; i <N; i++) {
            String[] rooms = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(rooms[j]);
            }
        }
    }

    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = false;
                if(A[i][j]==2){
                    visit[i][j] = true;
                    q.add(i);
                    q.add(j);
                }
            }
        }

        while (!q.isEmpty()){
            int x = q.poll();
            int y = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if(nx < 0 || ny <0|| nx >=N || ny >= M) continue;
                if(visit[nx][ny]) continue;
                if(A[nx][ny] != 0) continue;

                visit[nx][ny] = true;
                q.add(nx);
                q.add(ny);
            }
        }
        int cnt=0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!visit[i][j] && A[i][j] == 0){
                    cnt++;
                }
            }
        }
        ans = Math.max(ans, cnt);
    }
    static void dfs(int idx, int wall){
        if(wall == 3){
            bfs();
            return;
        }
        if(idx > B) return;
        A[blank[idx][0]][blank[idx][1]] = 1;
        System.out.println("1: "+blank[idx][0]+", "+blank[idx][1]);
        dfs(idx+1, wall+1);

        A[blank[idx][0]][blank[idx][1]] = 0;
        System.out.println("0: "+blank[idx][0]+", "+blank[idx][1]);
        dfs(idx+1, wall);

    }
    static void sol(){
        // 빈방 모으기
        for (int i = 0; i <N; i++) {
            for (int j = 0; j < M; j++) {
                if(A[i][j]==0){
                    B++;
                    blank[B][0] = i;
                    blank[B][1] = j;
                }
            }
        }
        dfs(1,0);
        System.out.println(ans);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
