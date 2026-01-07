package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_5567_결혼식 {
    static int n, m;
    static List<Integer>[] nodes;
    static int[] distance;
    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        distance = new int[n+1];

        nodes = new List[n+1];
        for (int i = 1; i <= n; i++)
            nodes[i] = new ArrayList<>();

        for (int i = 1; i <= m; i++) {
            String[] split = br.readLine().split("\\s+");
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1]);
            nodes[start].add(end);
            nodes[end].add(start);
        }

    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    static void sol(){
        for (int i = 1; i <= n; i++)
            distance[i] = -1;

        bfs();

        int answer = 0;
        for (int i = 1; i <= n; i++){
            if(distance[i]==1 || distance[i]==2){
                answer++;
            }
        }

        System.out.println(answer);
    }

    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        distance[1] = 0;
        q.add(1);

        while (!q.isEmpty()){
            Integer relatedPersionIdx = q.poll();
            for (int relatedPerson : nodes[relatedPersionIdx]){
                if(distance[relatedPerson] != -1) continue;
                distance[relatedPerson] = distance[relatedPersionIdx]+1;
                q.add(relatedPerson);
            }

        }

    }

}
