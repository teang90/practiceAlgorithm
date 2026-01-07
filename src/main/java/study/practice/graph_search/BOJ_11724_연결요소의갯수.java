package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11724_연결요소의갯수 {
    static int N, M;
    static List<Integer>[] nodes;
    static int[] edge;
    static boolean[] visited;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        visited = new boolean[N+1];
        nodes = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++)
            nodes[i] = new ArrayList();

        for (int i = 0; i < M; i++) {
            String[] split1 = br.readLine().split("\\s+");
            int start = Integer.parseInt(split1[0]);
            int end = Integer.parseInt(split1[1]);
            nodes[start].add(end);
            nodes[end].add(start);
        }

    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    static void sol(){
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if(!visited[i]){
                dfs(i);
                answer++;
            }
        }
        System.out.println(answer);
    }

    static void dfs(int start){
        visited[start] = true;

        for (Integer connectNode : nodes[start]) {
            if(visited[connectNode]) continue;
            dfs(connectNode);
        }
    }
}
