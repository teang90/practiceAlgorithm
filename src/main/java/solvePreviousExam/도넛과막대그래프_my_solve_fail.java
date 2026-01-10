package solvePreviousExam;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class 도넛과막대그래프_my_solve_fail {
    public static void main(String[] args) {
        도넛과막대그래프_my_solve_fail dn = new 도넛과막대그래프_my_solve_fail();
        /*[[2, 3], [4, 3], [1, 1], [2, 1]]	[2, 1, 1, 0]
[[4, 11], [1, 12], [8, 3], [12, 7], [4, 2], [7, 11], [4, 8], [9, 6], [10, 11], [6, 10], [3, 5], [11, 1], [5, 3], [11, 9], [3, 8]]	[4, 0, 1, 2]*/
        int[][] input = {{2,3},{4,3},{1,1},{2,1}};
        int[] solution = dn.solution(input);
        System.out.println(Arrays.toString(solution));
    }

    private final static boolean[] visited = new boolean[1_000_000];
    private final static List<Integer>[] edgeList = new List[1_000_000];
    public int[] solution(int[][] input) {
        int[] answer = new int[4];
        Set<Integer> visitedSet = new HashSet<>();
        int startNode = 0; // out-arrow만 존재하고, out arrow가 많은 것
        int N = 0;
        for (int i = 0; i < 1_000_000; i++)
            edgeList[i] = new ArrayList<>();

        int[] maxOutBound = new int[1_000_000+1];
        for (int i = 0; i < input.length; i++){
            visitedSet.add(input[i][0]);
            visitedSet.add(input[i][1]);
            edgeList[input[i][0]].add(input[i][1]);
            maxOutBound[input[i][0]]++;
        }
        N = visitedSet.size();// 정점의 갯수

        for (int i = 0; i < maxOutBound.length; i++)
            if(startNode < maxOutBound[i])
                startNode = maxOutBound[i];

        answer[0]= startNode;

        List<Integer> connectedNodes = edgeList[startNode];
        for (int connNode : connectedNodes) {
            for (int i = 0; i < visited.length; i++)
                visited[i] = false;
            AtomicInteger cellCnt = new AtomicInteger(1);
            AtomicInteger edgesCnt = new AtomicInteger(0);
            System.out.println("cellCnt: "+cellCnt+", edgesCnt = " + edgesCnt);
            findNodeShape(connNode, cellCnt, edgesCnt);
            System.out.println("cellCnt: "+cellCnt+", edgesCnt = " + edgesCnt);

            if(cellCnt.get()==edgesCnt.get()) answer[1]++;
            if(cellCnt.get()==edgesCnt.get()-1) answer[2]++;
            if(cellCnt.get() == (edgesCnt.get()+1)) answer[3]++;
        }

        return answer;
    }

    private void findNodeShape(int start, AtomicInteger cellCnt, AtomicInteger edgesCnt){
        visited[start] = true;

        List<Integer> connNodes = edgeList[start];
        for (int connNode : connNodes){
            edgesCnt.addAndGet(1);
            if(visited[connNode]) continue;
            cellCnt.addAndGet(1);
            findNodeShape(connNode, cellCnt, edgesCnt);
        }
    }

}

// 도넛 특징 -> 크기 N인 도넛은 N개 정정, N개의 간선 존재
// 막대 특징 -> 크기 N인 막대는 N개의 정점과 N-1개의 간선 존재
// 8자 특징 ->  크기 N인 8자는 2N+1의 정점과 2N+2개의 간선 존재
// 정점과 간선의 관계가 주어지면 여기서 선택한 출발 정점 / 도넛이 몇개 / 막대가 몇개 / 8자 구조가 몇개 구하라
