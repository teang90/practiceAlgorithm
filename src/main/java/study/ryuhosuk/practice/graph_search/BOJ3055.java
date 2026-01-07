package study.ryuhosuk.practice.graph_search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ3055 {
    static Scanner sc = new Scanner(System.in);
    static int R, C;
    static boolean[][] visit;
    static int[][] dist_water;
    static int[][] dist_hedgehog;
    static String[] forest;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

    // 비어있는 곳은 '.'로 표시되어 있고, 물이 차있는 지역은 '*', 돌은 'X'로 표시되어 있다.
    // 비버의 굴은 'D'로, 고슴도치의 위치는 'S'로 나타내어져 있다.
    static void input(){
        String[] arr = sc.nextLine().split(" ");
        R = Integer.parseInt(arr[0]);
        C = Integer.parseInt(arr[1]);
        forest = new String[R];
        for (int i = 0; i < R; i++) forest[i] = sc.nextLine();
        visit = new boolean[R][C];
        dist_water = new int[R][C];
        dist_hedgehog = new int[R][C];
    }
    
    static void bfs_water() {
        Queue<Integer> waterQ = new LinkedList<>();
        // multisource bfs > 물이 있는 곳들(물의 시작점)을 모두 waterQueue에 넣자
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dist_water[i][j]=-1;
                if(forest[i].charAt(j)=='*'){
                    waterQ.add(i);
                    waterQ.add(j);
                    visit[i][j] = true;
                    dist_water[i][j]=0;
                }
            }
        }

        while (!waterQ.isEmpty()){
            int x = waterQ.poll();
            int y = waterQ.poll();

            for (int k = 0; k < 4; k++) {
                // 물과 고슴도치는 돌을 통과할 수 없다. 또, 고슴도치는 물로 차있는 구역으로 이동할 수 없고,
                // 물도 비버의 소굴로 이동할 수 없다.
                int xToMove = x + dir[k][0];
                int yToMove = y + dir[k][1];
                // 물이 이동할 수 있는 새로운 위치 xToMove, yToMove의 유효성 체크
                if(xToMove < 0 || xToMove >= R || yToMove<0 || yToMove >= C) continue;
                if(visit[xToMove][yToMove]) continue;
                if(forest[xToMove].charAt(yToMove)!='.') continue;

                waterQ.add(xToMove);
                waterQ.add(yToMove);
                visit[xToMove][yToMove] = true;
                dist_water[xToMove][yToMove] = dist_water[x][y]+1;
            }
        }
    }

    static void bfs_hedgehog() {
        Queue<Integer> hdhQ = new LinkedList<>();
        // visit 배열 초기화( water bfs에서 water 이동에 사용되었음) & 고슴도치 visit, 위치 세팅
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                visit[i][j] = false;
                dist_hedgehog[i][j]=-1;
                if(forest[i].charAt(j)=='S'){
                    visit[i][j] = true;
                    dist_hedgehog[i][j]=0;
                    hdhQ.add(i);
                    hdhQ.add(j);
                }
            }
        }

        while (!hdhQ.isEmpty()){
            int x = hdhQ.poll();
            int y = hdhQ.poll();
            for (int k = 0; k < 4; k++) {
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if(visit[nx][ny]) continue;
                if(forest[nx].charAt(ny) !='.' && forest[nx].charAt(ny) !='D') continue;
                // 아직 내 다음 위치는 정해지지 않은 상태이다. 그래서 현재 내 위치의 다음 시간과 물의 다음 위치를 비교해야한다.
                // 왜냐면 dist_hedgehog[x][y]+1 이식 자체가 dist_hedgehog[nx][ny]니까
                if(dist_water[nx][ny]!=-1 && dist_hedgehog[x][y]+1 >= dist_water[nx][ny]) continue;

//                if(dist_water[nx][ny]!=-1 && dist_hedgehog[nx][ny] >= dist_water[x][y]+1) continue;
//                 -> 이건 틀린 조건(by tyjeong), 내 현재 위치 다음 시간이 다음 물의위치의 물과 비교해야함

                hdhQ.add(nx);
                hdhQ.add(ny);
                visit[nx][ny] = true;
                dist_hedgehog[nx][ny]=dist_hedgehog[x][y]+1;
            }
        }
    }
    static void sol(){
        bfs_water();
        
        bfs_hedgehog();
        
        // 정답 산출
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(forest[i].charAt(j)=='D'){
                    if(dist_hedgehog[i][j]==-1) System.out.println("KAKTUS");
                    else System.out.println(dist_hedgehog[i][j]);
                }
            }
        }

    }
    public static void main(String[] args) {
        input();
        sol();
    }

}
