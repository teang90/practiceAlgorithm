package study.practice.graph_search2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_11403_경로찾기_dfs {
    private static int N;
    private static List<Integer>[] nodes;
    private static boolean[][] visited;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1][N + 1];

        nodes = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) nodes[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] arr = br.readLine().split("\\s+");
            for (int j = 0; j < arr.length; j++) {
                if (Integer.valueOf(arr[j]) == 1) {
                    nodes[i].add(j);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    public static void sol() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j] && (nodes[i].contains(j)))
                    dfs(i, j);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(visited[i][j]? "1" : "0").append(" ");
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb);
    }

    public static void dfs(int start, int end) {
        visited[start][end] = true;

        List<Integer> connectedNodes = nodes[end];
        for (int connectedNode : connectedNodes) {
            if (visited[start][connectedNode]) continue;
            dfs(start, connectedNode);
        }
    }

}