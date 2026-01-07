package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2251_물통 {

    static int A, B, C;
    static boolean[][][] visited;
    static int[] limits;
    static boolean[] A_IS_ZERO;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        visited = new boolean[201][201][201];
        String[] split = br.readLine().split("\\s+");
        A = Integer.parseInt(split[0]);
        B = Integer.parseInt(split[1]);
        C = Integer.parseInt(split[2]);
        limits = new int[]{A, B, C};
        A_IS_ZERO = new boolean[200+1];
    }

    public static void main(String[] args)  throws Exception {
        input();
        sol();
    }

    static void sol() {
        // A가 비어있을때, C 물통에 담겨 있을 수 있는 물의 양
        // C -> A || C-> B 경우의 수를 나눠야 함 -> 이떄 물의 상태 status를 알아야 함
        bfs(0, 0, C);
        for (int i = 0; i <= 200; i++) {
            if(A_IS_ZERO[i]) System.out.print(i+" ");
        }
    }

    static void bfs(int A, int B, int C){
        Queue<WaterStatus> q = new LinkedList<>();
        q.add(new WaterStatus(new int[]{0, 0, C}));
        visited[A][B][C] = true;

        while (!q.isEmpty()){
            WaterStatus poll = q.poll();

            if(poll.asIsWaters[0]==0)
                A_IS_ZERO[poll.asIsWaters[2]] = true;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    WaterStatus pouredWaters = poll.pour(i, j, limits);
                    int[] pouredWaterVol = pouredWaters.asIsWaters;

                    if(i==j) continue;
                    if(visited[pouredWaterVol[0]][pouredWaterVol[1]][pouredWaterVol[2]]) continue;

                    q.add(pouredWaters);
                    visited[pouredWaterVol[0]][pouredWaterVol[1]][pouredWaterVol[2]]=true;
                }
            }

        }
    }

    static class WaterStatus {
        int[] asIsWaters;

        WaterStatus(int[] waters) {
            asIsWaters = waters;
        }

        public WaterStatus pour(int fromIdx, int toIdx, int[] limitVolume) {
            int[] newWater = {asIsWaters[0], asIsWaters[1], asIsWaters[2]};
            // from -> to로 물을 부으려할떄 to에 부을 수 있나?
            if(asIsWaters[toIdx]==limitVolume[toIdx]) return this;
            // 부을 수 있다면 from의 물을 to에 다 붓는 경우
            if(limitVolume[toIdx] >= asIsWaters[fromIdx]+asIsWaters[toIdx]){
                newWater[toIdx] = asIsWaters[fromIdx]+asIsWaters[toIdx];
                newWater[fromIdx] = 0;
            }else{
                // 부을 수 있다면 from의 물을 to 다 못 붓는 경우
                newWater[fromIdx] = asIsWaters[fromIdx]-(limitVolume[toIdx] - asIsWaters[toIdx]);
                newWater[toIdx] =  limitVolume[toIdx];
            }

            return new WaterStatus(newWater);
        }

    }

}

