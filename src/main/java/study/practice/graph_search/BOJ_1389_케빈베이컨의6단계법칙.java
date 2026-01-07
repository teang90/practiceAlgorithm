package study.practice.graph_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1389_케빈베이컨의6단계법칙 {

    static int N, M;
    static List<Integer>[] nodes;
    static int[] distance;
    static int[] answers;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        answers = new int[N+1];
        distance = new int[N+1];
        nodes = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) nodes[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String[] split1 = br.readLine().split("\\s+");
            Integer start = Integer.valueOf(split1[0]);
            Integer end = Integer.valueOf(split1[1]);
            nodes[start].add(end);
            nodes[end].add(start);
        }

    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    static void sol(){
        for (int i = 1; i <= N; i++) {

            for (int j = 1; j <= N; j++)
                distance[j] = -1;

            bfs(i);

            for (int j = 1; j <= N; j++)
                answers[i] += distance[j];

        }


        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int tmpMin = Math.min(min, answers[i]);
            if(tmpMin == answers[i])
                min = answers[i];
        }
        List<Integer> minIdxs = new ArrayList<>();
        for (int i = 1; i <= N; i++)
            if(answers[i] == min) minIdxs.add(i);

        Collections.sort(minIdxs, Comparator.comparingInt(o -> o));
        System.out.println(minIdxs.get(0));
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        distance[start] = 0;

        while (!q.isEmpty()){
            Integer poll = q.poll();
            for (int relatedPersonIdx : nodes[poll]) {
                if(distance[relatedPersonIdx] > 0) continue;
                distance[relatedPersonIdx] = distance[poll]+1;
                q.add(relatedPersonIdx);
            }
        }
    }
}
