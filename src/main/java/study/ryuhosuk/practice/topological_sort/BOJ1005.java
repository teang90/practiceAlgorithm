package study.ryuhosuk.practice.topological_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ1005 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, N, K, W; // T: 테스트 케이스, N: 건물 개수[2, 1000], K: 건물 순서 규칙 [1, 10^5], W: 건설시간을 구하려는 타겟 건물
    static int[] D, indeg, timeOfDone; // [0, 10^5]
    static List<Integer>[] adjList;

    static void input() throws Exception {
        String[] NK = br.readLine().trim().split(" ");
        N = Integer.parseInt(NK[0]);
        K = Integer.parseInt(NK[1]);
        indeg = new int[N+1];
        D = new int[N+1];   // 건물은 1번부터 시작, 건물당 건설에 걸리는 시간
        timeOfDone = new int[N+1];  // 건물 빌드 완성 시간
        String[] dStr = br.readLine().split(" ");
        for (int i = 1; i <= N; i++)
            D[i] = Integer.parseInt(dStr[i-1]);

        adjList = new List[N+1];
        for (int i = 1; i < N+1; i++) adjList[i] = new ArrayList<>();
        for (int i = 1; i <= K; i++){
            String[] buildOrder = br.readLine().split(" ");
            int fromBuilding = Integer.parseInt(buildOrder[0]), toBuilding = Integer.parseInt(buildOrder[1]);
            adjList[fromBuilding].add(toBuilding);
            indeg[toBuilding]++;    // in-degree 세팅
        }

        W = Integer.parseInt(br.readLine());
    }
    static void sol() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++){
            if (indeg[i] == 0) {
                q.add(i);
                timeOfDone[i] = D[i];
            }
        }

        while (!q.isEmpty()){
            int x = q.poll();
            for (int y : adjList[x]) {
                indeg[y]--;
                if(indeg[y]==0) q.add(y);

                timeOfDone[y] = Math.max(timeOfDone[y], timeOfDone[x]+D[y]);
            }
        }
        int targetTimeToBuild = timeOfDone[W];
        System.out.println(targetTimeToBuild);
    }
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i <T; i++) {
            input();
            sol();
            clean();
        }
    }

    static void clean(){
        N=0;
        K=0;
        W=0;
        D = null;
        indeg = null;
        adjList = null;
        timeOfDone = null;
    }
}
