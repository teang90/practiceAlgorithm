package study.practice.bruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 중복 없이, 1~N의 자연수 중에 중복없이 M개를 고른 수열
public class BOJ_15649_N과M_1 {
    private static int N, M;
    private static int[] selected;
    private static boolean[] duplicated;

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        input();
        sol(1); // 1~M번재 원소를 조건에 맞도록 추출하기 위해 모두 찾아본다
        System.out.println(sb);
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        selected = new int[M+1];
        duplicated = new boolean[N+1];
    }

    static void sol(int k){
        if(k == M+1) { // 선택해야하는 경우의 수를 모두 선택한 경우
            for (int i = 1; i < M+1; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int cand = 1; cand < N+1; cand++) {
            if(duplicated[cand]) continue;

            selected[k] = cand;
            duplicated[cand] = true;

            sol(k+1);

            selected[k] = 0;
            duplicated[cand] = false;
        }
    }

}
