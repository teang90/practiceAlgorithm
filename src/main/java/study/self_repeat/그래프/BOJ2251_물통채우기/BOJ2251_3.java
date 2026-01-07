package study.self_repeat.그래프.BOJ2251_물통채우기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2251_3 {
    // 0,0,10 -> 8,0,2 -> 0,2,8     // 8
    //                 -> 0,8,2     // 2
    // -      -> 0,9,1              // 1
    //        -> 0,9,1 -> 1,9,0 -> 1,0,9 -> 0,1,9   // 9
    // 0,0,10 -> 10                 // 10
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[][][] visit;
    static int[] Limit = new int[3];    // 물통의 리터
    static boolean[] posiible = new boolean[201];    // 물통의 리터
    static void input() throws Exception {
        String[] param = br.readLine().split(" ");
        Limit[0] = Integer.parseInt(param[0]);
        Limit[1] = Integer.parseInt(param[1]);
        Limit[2] = Integer.parseInt(param[2]);
        visit = new boolean[201][201][201];
    }

    void bfs(int[] start){
        Queue<State> q = new LinkedList<>();
        State state = State.of(start[0], start[1], start[2]);
        q.add(state);
        visit[state.waters[0]][state.waters[1]][state.waters[2]] = true;
        posiible[state.waters[2]] = true;

        while (!q.isEmpty()){
            State st = q.poll();

            for (int from=0; from < 3; from++) {
                for (int to=0; to<3; to++) {
                    if(from==to) continue;
                    State move = st.move(from, to, Limit);
                    if(visit[move.waters[0]][move.waters[1]][move.waters[2]]) continue;
                    visit[move.waters[0]][move.waters[1]][move.waters[2]] = true;
                    q.add(move);
                    if(move.waters[0]==0) posiible[move.waters[2]] = true;
                }
            }

        }

    }

    void sol(){

        bfs(new int[]{0, 0, 10});
        for (int i = 0; i < posiible.length; i++) {
            if(posiible[i]) System.out.print(i+" ");
        }
    }
    public static void main(String[] args) throws Exception {
        BOJ2251_3 bod = new BOJ2251_3();
        bod.input();
        bod.sol();
    }

    private static class State{
        int[] waters = new int[3];

        private State(int A, int B, int C) {
            this.waters[0]=A;
            this.waters[1]=B;
            this.waters[2]=C;
        }

        public static State of(int A, int B, int C){
            return new State(A, B, C);
        }

        public State move(int from, int to, int[] Limit){
            int[] newWaters = new int[]{waters[0],waters[1],waters[2]};

            if(waters[from] + waters[to] <= Limit[to] ){
                newWaters[to] = newWaters[to] + newWaters[from];
                newWaters[from] = 0;
            }else{
                newWaters[from] = newWaters[from] - (Limit[to]-newWaters[to]);
                newWaters[to] = Limit[to];
            }

            return State.of(newWaters[0], newWaters[1], newWaters[2]);
        }
    }

}
