package study.self_repeat.그래프.BOJ2251_물통채우기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2251_2 {
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
        State initState = new State(start);
        q.add(initState);
        visit[initState.X[0]][initState.X[1]][initState.X[2]] = true;
        posiible[initState.X[2]] = true;

        while (!q.isEmpty()){
            State st = q.poll();

            for (int from = 0; from < 3; from++) {
                for (int to = 0; to < 3; to++) {
                    if(from == to ) continue;   // 이거 또 빼먹었네... 동일한 물통끼리는 물 붓는 행위할 필요가 없는데...
                    State newState = st.move(from, to, Limit);
                    if(visit[newState.X[0]][newState.X[1]][newState.X[2]]) continue;
                    visit[newState.X[0]][newState.X[1]][newState.X[2]] = true;
                    q.add(newState);
                    if(newState.X[0]==0) posiible[newState.X[2]] = true;
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
        BOJ2251_2 bod = new BOJ2251_2();
        bod.input();
        bod.sol();
    }
    class State{
        int[] X = new int[3];
        State(int[] _X) {
            X[0] = _X[0];
            X[1] = _X[1];
            X[2] = _X[2];
        }
        State move(int from, int to, int[] Limit){
            int[] nX = new int[]{X[0],X[1],X[2]};
            if(X[from]+X[to] > Limit[to]){
                nX[from] -= Limit[to] - nX[to];
                nX[to] = Limit[to];
            }else{
                nX[to] = nX[to] + nX[from];
                nX[from] = 0;
            }
            return new State(nX);
        }
    }
}
