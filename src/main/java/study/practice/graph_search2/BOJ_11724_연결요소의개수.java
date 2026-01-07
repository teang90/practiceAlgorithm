package study.practice.graph_search2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_11724_연결요소의개수 {
    private static int N, M;
    private static List<Integer>[] edges;
    private static boolean[] visited;

    public static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");

        N = Integer.valueOf(split[0]);
        M = Integer.valueOf(split[1]);
        visited = new boolean[N+1];
        edges = new ArrayList[N+1];
        for (int i = 1; i <= N; i++)
            edges[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            String[] split1 = br.readLine().split("\\s+");
            int start = Integer.valueOf(split1[0]);
            int end = Integer.valueOf(split1[1]);
            edges[start].add(end);
            edges[end].add(start);
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    public static void sol(){
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if(!visited[i]){
                dfs(i);
                answer++;
            }
        }

        System.out.println(answer);
    }

    public static void dfs(int start){
        visited[start] = true;
        for (Integer connectNodeIdx : edges[start]) {
            if(visited[connectNodeIdx]) continue;
            dfs(connectNodeIdx);
        }
    }


}
