package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_11403_경로찾기 {
    static boolean[] visited;
    static List<Integer>[] nodes;
    static int N;
    static StringBuilder sb = new StringBuilder();

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new ArrayList[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split("\\s+");
            nodes[i] = new ArrayList<>();
            for (int j = 0; j < split.length; j++) {
                nodes[i].add(Integer.parseInt(split[j]));
            }
        }

    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    static void sol(){
        for (int i = 0; i < N; i++) bfs(i);

        System.out.println(sb);
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < N; i++)
            visited[i]=false;

        q.add(start);
        visited[start] = false;

        while (!q.isEmpty()){
            Integer poll = q.poll();

            for (int i = 0; i < N; i++) {
                if(visited[i]) continue;
                if(nodes[poll].get(i)!=1) continue;

                visited[i]=true;
                q.add(i);
            }
        }

        for (int i = 0; i < N; i++) sb.append(visited[i]?1:0).append(" ");
        sb.append("\n");
    }

}
