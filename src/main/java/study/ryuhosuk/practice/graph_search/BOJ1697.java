package study.ryuhosuk.practice.graph_search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1697 {
    static Scanner sc = new Scanner(System.in);
    static int N, K;
    static boolean[] visit;
    static int[] dist;
    static void input(){
        String[] arr = sc.nextLine().split(" ");
        N = Integer.parseInt(arr[0]);
        K = Integer.parseInt(arr[1]);
        visit = new boolean[100001];// *2씩 하다보면 상한을 넘을 수 있음, 그래서 그냥 입력 상한까지로 잡는게 편할듯?
        dist = new int[100001];     // 위와 유사한 맥락
    }
    static void bfs(){
        Queue<Integer> Q = new LinkedList<>();
        Q.add(N);
        visit[N] = true;
        dist[N] =0;
        while (!Q.isEmpty()){
            int x = Q.poll();
            if(x==K){ // 이 케이스는?
                System.out.println(x+" x==K");
                break;
            }
            //이동할 다음 위치
            int y = x-1; // x-1
            if(y>=0 && !visit[y]){
                System.out.println(y+" x-1==K");
                visit[y] = true;
                dist[y] = dist[x]+1;
                Q.add(y);
            }
            y=x+1;  // x+1
            if(y < 100001 && !visit[y]){
                System.out.println(y+" x+1==K");
                visit[y] = true;
                dist[y]=dist[x]+1;
                Q.add(y);
            }
            y=2*x;  // 2*x
            if(y<100001 && !visit[y]){
                System.out.println(y+" 2x==K");
                visit[y] = true;
                dist[y]=dist[x]+1;
                Q.add(y);
            }
        }
    }

    static void sol(){
        bfs();
        System.out.println(dist[K]);
    }

    public static void main(String[] args) {
        input();
        sol();
    }
    
    // 정점: 문제의 하나의 상태 또는 하나의 정점, 위치 등
    // 간선: 이동을 의미, 상태의 변화(물통), 이 문제(단방향)에선 +1, -1, *2
}
