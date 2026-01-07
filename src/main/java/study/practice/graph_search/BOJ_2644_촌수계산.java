package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_2644_촌수계산 {
    static int TOT_CNT;
    static int start, end;
    static List<Integer>[] nodes;
    static int[] dist;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TOT_CNT = Integer.parseInt(br.readLine());
        String[] split = br.readLine().split("\\s+");
        start = Integer.parseInt(split[0]);
        end = Integer.parseInt(split[1]);
        int M = Integer.parseInt(br.readLine());
        nodes = new ArrayList[TOT_CNT+1];
        dist = new int[TOT_CNT+1];

        for (int i = 0; i < TOT_CNT+1; i++) nodes[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String[] split1 = br.readLine().split("\\s+");
            Integer parent = Integer.valueOf(split1[0]);
            Integer child = Integer.valueOf(split1[1]);
            nodes[parent].add(child);
            nodes[child].add(parent);
        }

    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
        System.out.println(dist[end]);
    }

    static void sol(){
        for (int i = 0; i < TOT_CNT+1; i++) dist[i] = -1;
        bfs(start);
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        dist[start] = 0;
        while (!q.isEmpty()){
            Integer poll = q.poll();
            for (int i : nodes[poll]) {
                if(dist[i] > 0) continue;
                dist[i] = dist[poll]+1;
                q.add(i);
            }
        }

    }

}
