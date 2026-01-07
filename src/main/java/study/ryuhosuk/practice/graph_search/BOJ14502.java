package study.ryuhosuk.practice.graph_search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ14502 { // 연구소( https://www.acmicpc.net/problem/14502 )
    static Scanner sc = new Scanner(System.in);
    static int N, M, B, ans;    // 세로, 가로
    static boolean[][] visit;
    static int[][] blank, A;    // A는 최초 연구소 설계도인듯
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};   // 격자 점의 이동을 알기 위함
    static void input() {
        String[] arr = sc.nextLine().split(" ");
        N = Integer.parseInt(arr[0]);
        M = Integer.parseInt(arr[1]);
        A = new int[N+1][2];    // ?
        blank = new int[N*M + 1][2];
        visit = new boolean[N+1][M+1];
        for(int i=1; i<=N; i++){
            String[] vals = sc.nextLine().split(" ");
            for (int j=1; i<=M; j++) A[i][j]=Integer.parseInt(vals[j-1]);
        }
    }

    static void bfs() {
        Queue<Integer> Q = new LinkedList<>();
        // 1. 모든 바이러스의 위치를 시작점으로 넣어준다, 바이러스 확산을 위한 초기 세팅(all visit set false)
        for(int i=1; i<=N; i++){
            for (int j=1; j<=M; j++){
                visit[i][j] = false;
                if (A[i][j]==2){    // 바이러스
                    Q.add(i);   // Q의 add 순서가 행, 열이 보장된다면,
                    Q.add(j);
                    visit[i][j] = true;
                }
            }
        }
        
        // 2. BFS -> 바이러스 확산
        while (!Q.isEmpty()){
            int x = Q.poll();   // 먼저 뽑는게 행(보장)
            int y = Q.poll();   // 이후 뽑는게 열(보장)
            for(int k=0; k<4; k++){
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                if(nx<1 || ny<1 || nx > N || ny > M) continue;
                if(A[nx][ny]!=0) continue;      // 0이 아니면 벽 or 바이러스기때문에 확산할 필요 X
                if(visit[nx][ny]) continue;     // 이미 방문했었다면 넘어가자(이미 확산)
                visit[nx][ny] = true;
                Q.add(nx);
                Q.add(ny);
            }
        }

        // 3. 탐색 종료 시 안전 영역의 넓이를 구한다.
        int cnt =0;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(A[i][j]==0 && !visit[i][j]) cnt++;
            }
        }
        ans = Math.max(cnt,ans);
    }

    // 완전탐색으로 먼저 벽을 세우는 경우를 뽑는다. -> combination 3 on N*M (n*m C 3)) -> 이 결과에 대한 하나의 케이스에 대해서 벽을 세우고 바이러스를 퍼뜨려보자.
    static void dfs(int idx, int selected_cnt){
        // 3개의 벽을 다 세웠을떄마다 bfs를 호출한다.
        if(selected_cnt==3){
            bfs();  //벽 3개를 다 세우면, 바이러스를 퍼뜨리자
            return ;
        }
        if(idx>B) return ; // 더 이상 세울 수 있는 벽이 없을때(설계도에 위치한 공간이 없는 상태?)
        
        // 설치된 벽이 3개가 안될때는 벽을 하나씩 세워보자(무작정 벽을 세우는거임) -> 벽을 세우는 모든 경우의 수를 볼테니까
        A[blank[idx][0]][blank[idx][1]] = 1;    // 벽을 세워보는 경우
        dfs(idx+1,selected_cnt+1);
        
        A[blank[idx][0]][blank[idx][1]] = 0;    // 벽을 안세워보는 경우
        dfs(idx+1,selected_cnt);
    }

    static void sol() {
        // 문제의 주어진 초기 상태의 모든 빈공간을 찾자
        for (int i=1; i<=N;i++){
            for(int j=1; j<=M; j++){
                if(A[i][j]==0){ // 빈공간이면 모으자
                    B++;    // 빈 공간 +1 총 갯수
                    // B번쨰(순서는 크게 의미 없을듯?) 빈공간의 좌표를 확보
                    blank[B][0]=i;  // 행
                    blank[B][1]=j;  // 열
                }
            }
        }

        // 벽을 3개 세우는 모든 방법을 확인
        dfs(1,0);
        System.out.println(ans);
    }
    public static void main(String[] args) {
        input();
        sol();
    }

}
