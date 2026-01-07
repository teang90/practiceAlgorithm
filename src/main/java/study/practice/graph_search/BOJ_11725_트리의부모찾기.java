package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ_11725_트리의부모찾기 {
    static int N;
    static boolean[] visited;
    static List<Integer>[] nodes;
    static Map<Integer, Integer> parentsMap = new HashMap<>();

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        nodes = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++)
            nodes[i] = new ArrayList<>();

        for (int i = 0; i < N-1; i++) {
            String[] split = br.readLine().split("\\s+");
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1]);
            nodes[start].add(end);
            nodes[end].add(start);
            parentsMap.put(start, 0);
        }

    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    static void sol(){
        StringBuilder sb = new StringBuilder();

        dfs(1);

        for (int i = 2; i <= N; i++) {
            sb.append(parentsMap.get(i)).append("\n");
        }

        System.out.println(sb);

    }

    static void dfs(int start){
        visited[start]=true;

        for (Integer connectNode : nodes[start]) {
            if(visited[connectNode]) continue;

            visited[connectNode]=true;
            parentsMap.put(connectNode, start);
            dfs(connectNode);
        }
    }

}
