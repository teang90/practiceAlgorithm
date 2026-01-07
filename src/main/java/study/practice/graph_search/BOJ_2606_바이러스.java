package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_2606_바이러스 {
    static int computers, edges;
    static List<Integer>[] nodes;
    static boolean[] visited;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        computers = Integer.parseInt(br.readLine());
        edges = Integer.parseInt(br.readLine());
        nodes = new ArrayList[computers+1];
        visited = new boolean[computers+1];
        visited[0] = true;

        for (int i = 0; i <= computers; i++)
            nodes[i] = new ArrayList<>();

        for (int i = 0; i < edges; i++) {
            String[] split = br.readLine().split("\\s+");
            Integer start = Integer.valueOf(split[0]);
            Integer end = Integer.valueOf(split[1]);
            nodes[start].add(end);
            nodes[end].add(start);
        }

    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    static void sol() throws Exception {
        int infested = 0;
        bfs(1);
//        dfs(1);
        for (int i = 2; i <= computers; i++){
            if(visited[i]){
                infested++;
            }
        }

        System.out.println(infested);
    }

    static void dfs(int start){
        visited[start]=true;

        List<Integer> node = nodes[start];
        for (int i : node) {
            if(visited[i]) continue;

            dfs(i);
        }
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start]=true;

        while (!q.isEmpty()){
            Integer poll = q.poll();
            for(int i : nodes[poll]){
                if(visited[i]) continue;

                visited[i]=true;
                q.add(i);
            }
        }


    }

}
