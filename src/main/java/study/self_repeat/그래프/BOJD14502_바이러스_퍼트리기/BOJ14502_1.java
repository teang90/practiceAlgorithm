package study.self_repeat.그래프.BOJD14502_바이러스_퍼트리기;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ14502_1 {
    static Scanner sc = new Scanner(System.in);
    static int N,M,B, answer;
    static int[][] A;
    static boolean[][] visit;
    static int[][] blank;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static void input(){
        String[] nums = sc.nextLine().split(" ");
        N = Integer.parseInt(nums[0]);  // 연구소 세로 size
        M = Integer.parseInt(nums[1]);  // 연구소 가로 size
        visit = new boolean[N+1][M+1];
        A = new int[N+1][M+1]; // 방을 위치를 구현한 방 좌표 배열
        for (int i=0; i<N; i++){
            String[] rooms = sc.nextLine().split(" ");
            for(int j=0; j<M; j++) A[i+1][j+1] = Integer.parseInt(rooms[j]);
        }
        blank = new int[N*M + 1][2];    // 빈방 좌표

//        for (int i=0; i<N; i++){
//            for(int j=0; j<M; j++) System.out.println(A[i+1][j+1]);
//        }
    }
    // 3개의 벽을 다 설치했다면, 바이러스 확산
    static void bfs(){
        Queue<Integer> Q = new LinkedList<>();
        // multisource bfs...
        for (int i = 1; i <=N ; i++) {
            for (int j = 1; j <= M; j++) {
                visit[i][j]=false;
                if(A[i][j]==2){
                    visit[i][j]=true;
                    Q.add(i);
                    Q.add(j);
                }
            }
        }

        while (!Q.isEmpty()){
            // 바이러스의 위치
            int x = Q.poll();
            int y = Q.poll();

            for (int k = 0; k < 4; k++) {
                int newX = x + dir[k][0];
                int newY = y + dir[k][1];

                // 범위 유효성 check
                if(newX<1 || newX > N || newY<1 || newY > M) continue;
                // 벽이면 못가니까 pass 또는 visit check,
                if(A[newX][newY]==1 || visit[newX][newY]) continue;

                visit[newX][newY] = true;
                Q.add(newX);
                Q.add(newY);
            }
        }

        int cnt=0;
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= M; j++)
                if(A[i][j]==0 && !visit[i][j]) cnt++;

        answer = Math.max(answer, cnt);
    }
    static void combinationByDFS(int idx, int wallCnt){
        if(wallCnt==3){
            bfs();
            return;
        }
        if(idx > B) return ;

        A[blank[idx][0]][blank[idx][1]]=1;
        combinationByDFS(idx+1, wallCnt+1);

        A[blank[idx][0]][blank[idx][1]]=0;
        combinationByDFS(idx+1, wallCnt);
    }

    static void sol(){
        // 빈방을 정리해서 갖고있자
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <=M; j++) {
                if(A[i][j]==0){
                    B++;
                    // 빈방 좌표 세팅
                    blank[B][0]=i;
                    blank[B][1]=j;
                }
            }
        }
        combinationByDFS(1, 0); // 1번 위치(행의 시작 index)부터 시작, 현재 벽의 갯수는 0개
        System.out.println(answer);
    }
    public static void main(String[] args) {
        input();
        sol();
    }
}
