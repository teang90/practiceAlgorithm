package study.practice.bruteforce2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 1~N 자연수 중, 중복 없이 M개를 고른 수열, 오름 차순이여야한다.
public class BOJ_15650_N과M_2 {
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

    /**
     * 여기서 startIdx로 순서를 결정 짓는 순간 조합 풀이가된다.
     * 즉 1,2 -> 2,1 이러한 경우가 배제되고 1,2만 경우의 수로 치기 떄문임
     * */
    static void sol(int k, int startIdx){
        if(k==M+1){
            for (int i = 1; i < M+1; i++)
                sb.append(selected[i]).append(" ");

            sb.append("\n");
            return;
        }

        for (int cand = startIdx; cand <= N; cand++) {
            selected[k] = cand;
            sol(k+1, cand+1);
            selected[k] = 0;
        }

    }

}
