package study.practice.graph_search2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_11403_경로찾기_bfs {
    private static int N;
    private static List<Integer>[] nodes;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        nodes = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) nodes[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] arr = br.readLine().split("\\s+");
            for (int j = 0; j < arr.length; j++) {
                nodes[i].add(Integer.valueOf(arr[j]));
            }
        }

    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    public static void sol() {
        for (int i = 0; i < N; i++) {
//            if(!visited[i])
                bfs(i);
        }

        System.out.println(sb);
    }

    public static void bfs(int start) {
        for (int i = 0; i <N; i++) visited[i] = false;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()){
            Integer nextIdx = q.poll();
            List<Integer> connectedIdxs = nodes[nextIdx];
            for (int i=0; i<connectedIdxs.size(); i++) {
                if(visited[i]) continue;
                if(connectedIdxs.get(i)!=1) continue;
                visited[i] = true;
                q.add(i);
            }
        }

        for (int i = 0; i < N; i++) {
            sb.append(visited[i]? 1:0).append(" ");
        }
        sb.append("\n");
    }

}