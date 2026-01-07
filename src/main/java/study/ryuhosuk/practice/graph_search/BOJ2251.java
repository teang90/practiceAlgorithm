package study.ryuhosuk.practice.graph_search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2251 {
    // 8,9,10
    // 0,0,10 // initial state
    // 1) 0,9,1
    // 2) 8,0,2
    // 3) 8,2,0 -> 0,2,8
    // 4) 1,9,0 -> 1,0,9
    static Scanner sc = new Scanner(System.in);
    static boolean[][][] visit;
    static int[] limit = new int[3];
    static boolean[] possible;
    static void input() {
        String[] arr = sc.nextLine().split(" ");
        limit[0] = Integer.parseInt(arr[0]);
        limit[1] = Integer.parseInt(arr[1]);
        limit[2] = Integer.parseInt(arr[2]);
        visit = new boolean[201][201][201];
        possible = new boolean[201];
    }
    static void bfs(int x1, int x2, int x3) {
        Queue<State> Q = new LinkedList<>();
        visit[x1][x2][x3] = true;
        Q.add(new State(new int[]{x1, x2, x3}));

        while (!Q.isEmpty()){
            State st = Q.poll();

            if(st.X[0]==0) possible[st.X[2]] = true;    // A가 비어있을떄의 C의 물의 양(문제 조건 참고) -> 정답 조건 sieving(체질) 과정
            for(int from=0; from <3; from++){
                for (int to=0; to<3; to++){
                    if(from == to) continue;    // 동일한 물통이면 안함
                    State nxt = st.move(from, to, limit);

                    if(!visit[nxt.X[0]][nxt.X[1]][nxt.X[2]]){
                        visit[nxt.X[0]][nxt.X[1]][nxt.X[2]] = true;
                        Q.add(nxt);
                    }

                }
            }
        }

    }

    // 정점 O(200^3)
    // 간선 O(200^3*6)    A -> B, C, B->A, C, C->A,B : 6가지
    static void sol() {
        StringBuilder sb = new StringBuilder();
        bfs(0,0,limit[2]);
        //정답계산하기
        for (int i=0; i<possible.length; i++){
            if(possible[i]) sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        sol();
    }
}

// 복잡한 상태의 정점은 dto로 관리하자 -> 현재 물통의 상태와 물통의 물의 이동을 구현하는 객체
class State{
    // int 배열 -> A,B,C 물의 양
    int[] X;
    public State(int[] _X){
        X = new int[3];
        for(int i=0; i<3; i++) X[i]=_X[i];
    }
    // move() -> from to 로 물을 붓는 함수
    public State move(int from, int to, int[] limit){
        int[] nX = new int[]{X[0],X[1],X[2]};
        // from이 먼저 비거나(empty), to가 먼저 가득 차거나(full)
        if(X[from]+X[to] > limit[to]){
            nX[from] -= limit[to] - nX[to];  //limit[to]에 nX[from]만큼 넣어준 양
            nX[to] = limit[to]; // limit 만큼 가득 채우는 경우는 nX[to] 물통에 limit[to] 만큼 채우는게 최대임
        }else {
            nX[to] += nX[from]; // X[from]을 다 넣을 수 있는 경우
            nX[from] = 0;
        }

        return new State(nX);
    }

}