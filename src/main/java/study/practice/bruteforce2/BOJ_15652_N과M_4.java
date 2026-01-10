package study.practice.bruteforce2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 *
 * 1부터 N까지 자연수 중에서 M개를 고른 수열
 * 같은 수를 여러 번 골라도 된다.
 * 고른 수열은 비내림차순이어야 한다.
 * 길이가 K인 수열 A가 A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK를 만족하면, 비내림차순이라고 한다.
 *
 * ->  중복 가능 & 조합(순서가 있음) -> 중복 조합 문제
 */
// 1~N 자연수중 M개를 고른 수열, 중복 가능, 수열은 비내림차순이어야함
public class BOJ_15652_N과M_4 {
    private static int N, M;
    private static int[] selected;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        input();
        sol(1, 1);
        System.out.println(sb);
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        selected = new int[M+1];
    }

    static void sol(int k, int startIdx){
        if(k==M+1){
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }


        for (int cand = startIdx; cand < N+1; cand++) {
            selected[k] = cand;
            // cand 그대로 넣으면 중복(1 -> 2) 가 아니고 (1->1) and (1->2)
            // 이런식으로 현재 startIdx에서 다시 시작할 수 있음
            sol(k+1, cand);
            selected[k] = 0;
        }



    }

}
