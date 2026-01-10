package study.practice.bruteforce2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 중복 없이, 1~N의 자연수 중에 중복없이 M개를 고른 수열 -> 중복이 불가능한 순열
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
        if(k==M+1){
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if(duplicated[i]) continue;

            selected[k] = i;
            duplicated[i]=true;
            sol(k+1);
            selected[k]=0;
            duplicated[i]=false;

        }
    }

}
