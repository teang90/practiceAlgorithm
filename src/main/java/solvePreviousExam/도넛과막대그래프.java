package solvePreviousExam;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class 도넛과막대그래프 {
    public static void main(String[] args) {
        도넛과막대그래프 dn = new 도넛과막대그래프();
        /*[[2, 3], [4, 3], [1, 1], [2, 1]]	[2, 1, 1, 0]
[[4, 11], [1, 12], [8, 3], [12, 7], [4, 2], [7, 11], [4, 8], [9, 6], [10, 11], [6, 10], [3, 5], [11, 1], [5, 3], [11, 9], [3, 8]]	[4, 0, 1, 2]*/
        int[][] input = {{2,3},{4,3},{1,1},{2,1}};
        int[] solution = dn.solution(input);
        System.out.println(Arrays.toString(solution));
    }

    // in-bound 0 && out-bound 2개 이상 -> 출발점,
    //  out-deg가 2 중에서 out-bound 2개 이상 -> 8자
    //  out-deg 0 -> 막대 그래프
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        int[] inBound = new int[1000001];
        int[] outBound = new int[1000001];
        for (int i = 0; i < edges.length; i++) {
            outBound[edges[i][0]]++;
            inBound[edges[i][1]]++;
        }
        for (int i = 1; i < inBound.length; i++) {
            if(inBound[i] == 0 && outBound[i] >= 2){
                answer[0] = i;
                break;
            }
        }

        for (int i = 0; i < 1_000_000; i++) {
            if(i == answer[0]) continue;
            if(outBound[i] >= 2 && inBound[i]>=2) answer[3]++;
            if(outBound[i] == 0 && inBound[i]>0) answer[2]++;
        }
        answer[1] = outBound[answer[0]] - answer[3] - answer[2];
        return answer;
    }

}

// 도넛 특징 -> 크기 N인 도넛은 N개 정정, N개의 간선 존재
// 막대 특징 -> 크기 N인 막대는 N개의 정점과 N-1개의 간선 존재
// 8자 특징 ->  크기 N인 8자는 2N+1의 정점과 2N+2개의 간선 존재
// 정점과 간선의 관계가 주어지면 여기서 선택한 출발 정점 / 도넛이 몇개 / 막대가 몇개 / 8자 구조가 몇개 구하라
