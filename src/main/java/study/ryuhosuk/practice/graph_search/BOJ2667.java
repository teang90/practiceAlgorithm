package study.ryuhosuk.practice.graph_search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ2667 {
    /*
    <그림 1>과 같이 정사각형 모양의 지도가 있다. 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다.
    철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다.
    여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다.
    대각선상에 집이 있는 경우는 연결된 것이 아니다. <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다.
    지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.
    * */
    static Scanner sc = new Scanner(System.in);
    static String[] a;
    static boolean[][] visit;   // 임의의 격자점에서 주변의 정점을 찾는데 사용할 파라미터 (반복문을 돌면서 주변의 정점을 찾을 때 사용할 파라미터)
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    static ArrayList<Integer> group;    // 단지
    static int N;
    static int group_cnt =0;
    static void input(){
        N = Integer.parseInt(sc.nextLine());
        a = new String[N];
        for(int i=0; i<N; i++) a[i] = sc.nextLine();
        visit = new boolean[N][N];
    }

    // x,y 를 갈 수 있다는걸 알고 방문
    static void dfs(int x, int y){
        group_cnt++;
        visit[x][y]=true;
        // 인접한 집으로 새로운 방문하기
        for(int k=0; k<4; k++){
            // 좌표 (x,y)에대해 인접한 새로운 정점 방문하기(격자 정점이니 주변에 4개의 정점잉 있음)
            int newX = x + dir[k][0];
            int newY = y + dir[k][1];

            // 위의 (newX,newY) 좌표가 실제 존재(1)하는 좌표인지 확인해야한다.
            if(newX <0 || newY<0 || newX >=N || newY >=N) continue;

            //  newX,Y 좌표에 집이 있는경우 -> 간선이 존재
            if(a[newX].charAt(newY)=='0') continue;

            // 해당 정점이 visit했는지?
            if(visit[newX][newY]) continue;
            dfs(newX, newY);
        }

    }
    
    static void sol(){
        group = new ArrayList<>();
        for(int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                if(!visit[i][j] && a[i].charAt(j)=='1'){    // 방문하지 않는 집이 있는 경우
                    // 갈 수 있는 칸인데, 이미 방문처리가 된(방문 안한 칸인거 같은데)... 즉 새롭게 만난 단지인 경우 -> 인접한 모든 정점을 방문해야한다.
                    group_cnt =0;   // 집이 몇개 있는지?
                    dfs(i,j);
                    group.add(group_cnt);
                    
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
