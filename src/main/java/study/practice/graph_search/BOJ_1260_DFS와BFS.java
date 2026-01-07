package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1260_DFS와BFS {

    static int N, M, V;
    static List<Integer>[] edges;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        V = Integer.parseInt(split[2]);
        visited = new boolean[N+1];
        edges = new List[N+1];
        for (int i = 1; i <= N; i++)
            edges[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            String[] split1 = br.readLine().split("\\s+");
            int start = Integer.parseInt(split1[0]);
            int end = Integer.parseInt(split1[1]);
            edges[start].add(end);
            edges[end].add(start);
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    // 방문할 정점이 여러개인 경우에는 정점번호가 작은 것을 먼저 방문, 방문할 점이없으면 종료
    static void sol(){
        for (int i = 1; i <= N; i++)
            Collections.sort(edges[i]);

        dfs(V);

        for (int i = 0; i < N+1; i++)
            visited[i] = false;

        sb.append("\n");

        bfs(V);

        System.out.println(sb);
    }

    static void dfs(int start){
        visited[start] = true;
        sb.append(start).append(" ");
        List<Integer> edge = edges[start];

        for (Integer relatedNode : edge) {
            if(visited[relatedNode]) continue;
            visited[relatedNode] = true;
            dfs(relatedNode);
        }
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        sb.append(start).append(" ");

        while (!q.isEmpty()){
            Integer poll = q.poll();
            List<Integer> edge = edges[poll];

            for (Integer relatedNode : edge) {
                if(visited[relatedNode]) continue;
                sb.append(relatedNode).append(" ");
                visited[relatedNode] = true;
                q.add(relatedNode);
            }
        }
    }
}
