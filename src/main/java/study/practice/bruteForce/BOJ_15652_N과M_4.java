package study.practice.bruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 1~N 자연수중 M개를 고른 수열, 중복 가능, 수열은 비내림차순이어야함
public class BOJ_15652_N과M_4 {
    private static int N, M;
    private static int[] selected;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        input();
        sol(1);
        System.out.println(sb);
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        selected = new int[M+1];
    }

    static void sol(int k){
        if(k==M+1){
            for (int i = 1; i < M+1; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int beforeValue = selected[k - 1] == 0? 1 : selected[k - 1];
        for (int cand = beforeValue; cand < N+1; cand++) {
            selected[k] = cand;
            sol(k+1);
            selected[k] = 0;
        }

    }

}
