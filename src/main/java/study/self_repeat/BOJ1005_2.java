package study.self_repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1005_2 {
    static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
    static int T, N, K, W;
    static int[] T_done, T_buid;
    static List<Integer>[] adjList;
    static StringTokenizer st ;
    static int[] indeg;
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        T_buid = new int[N+1];
        T_done = new int[N+1];
        indeg = new int[N+1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++)
            T_buid[i]=Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            indeg[to]++;
        }

        W = Integer.parseInt(br.readLine());
    }
    public static void search(){
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++)
            if(indeg[i]==0){
                q.add(i);
                T_done[i] = T_buid[i];
            }

        while (!q.isEmpty()){
            int x = q.poll();
            for (int to : adjList[x]) {
                indeg[to]--;
                if(indeg[to]==0) {
                    q.add(to);
                }

                T_done[to] = Math.max(T_done[to], T_done[x]+T_buid[to]);
            }
        }
    }
    static void sol() throws Exception {
        search();
        System.out.println(T_done[W]);
    }
    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            input();
            sol();
        }
    }
}
