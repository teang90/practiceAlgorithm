package study.practice.bruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 중복 가능, [1,N]의 자연수 중 M개를 고른 수열
public class BOJ_15651_N과M_3 {

    private static int N, M;
    private static int[] selected;
    private static StringBuilder sb = new StringBuilder();

    static void input(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] split = br.readLine().split("\\s+");
            N = Integer.parseInt(split[0]);
            M = Integer.parseInt(split[1]);
            selected = new int[M+1];
        }catch (Exception e){

        }
    }

    public static void main(String[] args) {
        input();
        sol(1);
        System.out.println(sb);
    }

    static void sol(int start){
        if(start == M+1) {
            for (int i = 1; i <= M; i++)
                sb.append(selected[i]).append(" ");

            sb.append("\n");
            return;
        }

        for (int cand = 1; cand < N+1; cand++) {
            selected[start] = cand;
            sol(start+1);
            selected[start] = 0;
        }

    }
}
