package study.self_repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2252_1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;    // N:학생 수 [1, 32000], M: 비교 횟수 [1,10^5]
    static List<Integer>[] adjList;
    static int[] indeg;

    static void input() throws Exception {
        StringTokenizer NM = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(NM.nextToken());
        M = Integer.parseInt(NM.nextToken());
        indeg = new int[N+1];
        adjList = new List[N+1];
        for (int i = 1; i <=N; i++) adjList[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            indeg[to]++;
        }
    }

    static void sol(){
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if(indeg[i]==0){
                q.add(i);
                sb.append(i).append(' ');
            }
        }

        while (!q.isEmpty()){
            int x = q.poll();
            for (int y : adjList[x]) {
                indeg[y]--;
                if(indeg[y]==0){
                    q.add(y);
                    sb.append(y).append(' ');
                }
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();

    }
}
