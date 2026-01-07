package study.self_repeat.그래프.BOJ2667_단지번호;

import java.util.*;

public class BOJ2667_1 {
    static Scanner sc = new Scanner(System.in);
    static int N, groupCnt;
    static List<Integer> group = new ArrayList<>();
    static String[] a;
    static boolean[][] visit;   // 방문 여부 확인 visit check
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};   // 격자형 정점에서 사용하기 위함 dfs, bfs 모두 사용가능

    static void input() {
        N = Integer.parseInt(sc.nextLine());
        a = new String[N];
        for(int i=0; i<N; i++) a[i] = sc.nextLine();
        visit = new boolean[N][N];
    }
    static void bfs(int i, int j) {
        Queue<Integer> Q = new LinkedList<>();
        // BFS 초기 세팅
        visit[i][j] = true;
        Q.add(i);
        Q.add(j);
        groupCnt++;

        while (!Q.isEmpty()){
            int x = Q.poll();
            int y = Q.poll();
            for (int k=0; k<4; k++){
                int newX = x + dir[k][0];
                int newY = y + dir[k][1];
                // 해당 해로운 칸(집)이 전체 N*N 문제 조건 내에 존재하는지 확인
                if(0>newX || newX > N-1 || 0 > newY || newY > N-1) continue;
                if(visit[newX][newY]) continue;
                if(a[newX].charAt(newY)=='0') continue;

                visit[newX][newY] = true;
                groupCnt++;
                Q.add(newX);
                Q.add(newY);
            }
        }
        
    }
    static void dfs(int x, int y) {
        groupCnt++; // 여기 그룹의 칸 갯수가 몇개인지 카운팅
        visit[x][y]=true;

        for(int k=0; k<4; k++){
            int newX = x + dir[k][0];
            int newY = y + dir[k][1];
            // 해당 해로운 칸(집)이 전체 N*N 문제 조건 내에 존재하는지 확인
            if(0>newX || newX > N-1 || 0 > newY || newY > N-1) continue;
            if(visit[newX][newY]) continue;
            if(a[newX].charAt(newY)=='0') continue;
            dfs(newX, newY);
        }
    }

    static void sol() {
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(!visit[i][j] && a[i].charAt(j)=='1'){
                    groupCnt = 0;   // 정답을 구하기 위한 변수 초기화
//                    dfs(i,j);
                    bfs(i,j);
                    group.add(groupCnt);
                }
            }
        }
        Collections.sort(group);
        System.out.println(group.size());
        for (int cnt: group) System.out.println(cnt);
    }
    public static void main(String[] args) {
        input();
        sol();
    }

}
