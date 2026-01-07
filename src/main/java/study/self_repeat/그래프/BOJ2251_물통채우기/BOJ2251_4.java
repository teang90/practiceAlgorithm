package study.self_repeat.그래프.BOJ2251_물통채우기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class BOJ2251_4 {
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
        Queue<State4> q = new LinkedList<>();
        State4 initState = State4.of(start);
        q.add(initState);
        visit[start[0]][start[1]][start[2]]=true;
        if(start[0]==0) posiible[start[2]]=true;

        while (!q.isEmpty()){
            State4 poll = q.poll();
            // 3개의 인덱스를 서로소로 모두 물을 옮겨봐야한다. 따라서 from *3, idx *3 으로 각각의 루프가 총 2번이 필요하여 중첩 loop가 필요하다.
            for (int from = 0; from < 3; from++) {
                for (int to = 0; to < 3; to++) {
                    if(from==to) continue;
                    State4 move = poll.move(from, to, Limit);
                    if(visit[move.waters[0]][move.waters[1]][move.waters[2]]) continue;
                    q.add(move);
                    visit[move.waters[0]][move.waters[1]][move.waters[2]]=true;
                    if(move.waters[0]==0) posiible[move.waters[2]]=true;

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
        BOJ2251_4 bod = new BOJ2251_4();
        bod.input();
        bod.sol();
    }

    private static class State4{
        private int[] waters;

        public static State4 of(int... waters){
            State4 state4 = new State4();
            state4.waters = waters;
            return state4;
        }

        // 현재 물(this)에서 from -> to 로 물을 따른 후의 세 물통의 상태
        public State4 move(int fromIdx, int toIdx, int[] limitVol){
            int[] newWaterSt = {waters[0],waters[1],waters[2]};
            if(newWaterSt[fromIdx]+newWaterSt[toIdx] > limitVol[toIdx]){
                newWaterSt[toIdx] = limitVol[toIdx];
                newWaterSt[fromIdx] -= limitVol[toIdx]-waters[toIdx];
            }else{
                newWaterSt[toIdx] = newWaterSt[toIdx] + newWaterSt[fromIdx];
                newWaterSt[fromIdx] = 0;
            }

            return of(newWaterSt);
        }

    }

}
