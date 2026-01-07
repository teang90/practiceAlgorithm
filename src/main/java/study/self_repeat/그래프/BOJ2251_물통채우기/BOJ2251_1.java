package study.self_repeat.그래프.BOJ2251_물통채우기;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2251_1 {
    // init setting 8,9,10
    // 1) 0,0,10
    // 2) 0,9,1
    // 3) 0,9,1 ->1,9,0 -> 1,0,9 -> 0,1,9
    // 3) 8,0,2 ->0,10,2
    // 4) 8,0,2 ->8,2,0 -> 0,2,8
    static Scanner sc = new Scanner(System.in);
    static boolean[][][] visit = new boolean[201][201][201];
    static int[] limit = new int[3];
    static boolean[] possibleForAnswer = new boolean[201];
    static void input(){
        String[] waters = sc.nextLine().split(" ");
        limit[0] = Integer.parseInt(waters[0]);
        limit[1] = Integer.parseInt(waters[1]);
        limit[2] = Integer.parseInt(waters[2]);
    }
    static void bfs(int A, int B, int C){
        Queue<State> Q = new LinkedList<>();
        State initState = new State(new int[]{A,B,C});
        Q.add(initState);
        visit[initState.X[0]][initState.X[1]][initState.X[2]] = true;
        possibleForAnswer[C] = true;

        while (!Q.isEmpty()){
            State state = Q.poll();

//            if(state.X[0]==0) possibleForAnswer[state.X[2]] = true;
            for(int i=0; i<3; i++){
                for (int j = 0; j < 3; j++) {
                    if(i==j) continue;
                    State newState = state.move(i, j, limit);
                    if(visit[newState.X[0]][newState.X[1]][newState.X[2]]) continue;

                    visit[newState.X[0]][newState.X[1]][newState.X[2]] = true;
                    Q.add(newState);
                    if(newState.X[0]==0) possibleForAnswer[newState.X[2]] = true;
                }
            }
        }
    }
    static void sol(){
        bfs(0,0,limit[2]);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<possibleForAnswer.length; i++)
            if(possibleForAnswer[i]) sb.append(i).append(" ");

        System.out.println(sb);
    }
    public static void main(String[] args) {
        input();
        sol();
    }
}
class State{
    int[] X = new int[3];
    public State(int[] _X){
        X[0]=_X[0];
        X[1]=_X[1];
        X[2]=_X[2];
    }

    // 현재의 State객체의 물의 양 상태로 을 붓는 행위 및 물 이동 이후의 통의 결과를 State 객체로 변환
    public State move(int from, int to, int[] limit){
        int[] nX = new int[]{X[0],X[1],X[2]};
        if(X[from]+X[to] > limit[to]){ // 현재 from 통과 to 통의 물을 모두 합쳤을떄, to 번쨰 통의 부피를 넘는 경우
            nX[from] -= limit[to] - nX[to];
            nX[to] = limit[to];  // to 번째 통 full
        }else{ // to 번쨰의 통에 물을 다 부을수 있을때
            nX[to] = nX[to] + nX[from];
            nX[from] = 0;
        }
        return new State(nX);
    }
}