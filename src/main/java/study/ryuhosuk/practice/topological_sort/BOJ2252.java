package study.ryuhosuk.practice.topological_sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class BOJ2252 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M; // N: 학생수, M 키 비교 횟수
    static int[] indeg;
    static ArrayList<Integer>[] adjList ;
    static void input() throws Exception {
        String[] arr = br.readLine().split(" ");
        N = Integer.parseInt(arr[0]);
        M = Integer.parseInt(arr[1]);
        adjList = new ArrayList[N+1];
        indeg = new int[N+1];
        for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();
        for (int i = 1; i <= M; i++) {
            String[] param = br.readLine().split(" ");
            adjList[Integer.parseInt(param[0])].add(Integer.parseInt(param[1]));
            // indegree 계산 필수!!!
            indeg[Integer.parseInt(param[1])]++;
        }
    }
    static void sol() {
        StringBuilder sb = new StringBuilder();
        Deque<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if(indeg[i]==0){
                q.add(i);
                sb.append(i).append(' ');
            }
        }

        while (!q.isEmpty()){
            int x = q.poll();

            for (int y: adjList[x]) {
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
