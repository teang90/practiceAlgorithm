package study.ryuhosuk.practice.two_pointers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2251 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int A, B, C;
    static boolean[][][] visit;
    static int[] limit;
    static List<Integer> res;
    static void input() throws Exception {
        res = new ArrayList<>();
        limit = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        limit[0]=Integer.parseInt(st.nextToken());
        limit[1]=Integer.parseInt(st.nextToken());
        limit[2]=Integer.parseInt(st.nextToken());
        visit = new boolean[201][201][201];
    }
    static void bfs(int A, int B, int C){
        Queue<Water> q = new LinkedList<>();
        q.add(new Water(new int[]{A, B, C}));
        visit[A][B][C] = true;

        while (!q.isEmpty()){
            Water water = q.poll();

            if(water.state[0]==0) res.add(water.state[2]);


            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(i==j) continue;
                    Water ns = water.move(limit, i, j);
                    if(visit[ns.state[0]][ns.state[1]][ns.state[2]]) continue;
                    q.add(ns);
                    visit[ns.state[0]][ns.state[1]][ns.state[2]] = true;
                }
                
            }

        }

    }
    static void sol() {
        // 8 9 10
        // 0,0,10
        // 0, 9, 1 / 0, 1,9
        // 8, 0, 2 / 0, 8,2 / 0, 2, 8
        bfs(0,0, limit[2]);
        Collections.sort(res);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.size(); i++) {
            sb.append(res.get(i)).append(" ");
        }
        System.out.println(sb);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
class Water {
    int[] state = new int[3];
    Water(int[] X){state = X;}
    /** from X to Y 물통으로 옮기는 상황 -> 옮긴 결과 새로운 물통 생성, 기존의 state는 그대로 놔둬야 함 */
    Water move(int[] limit, int from, int to) {
        int[] _state = Arrays.copyOf(state, state.length);
        if(_state[from]+_state[to] > limit[to]){
            _state[from] -= limit[to] - _state[to];
            _state[to] = limit[to];
        }else{
            _state[to] += _state[from];
            _state[from] = 0;
        }
        return new Water(_state);
    }
}