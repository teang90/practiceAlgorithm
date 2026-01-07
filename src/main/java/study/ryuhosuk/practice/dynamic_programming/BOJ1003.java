package study.ryuhosuk.practice.dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1003 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, N;
    static int[][] Dy ;
    static void input() throws Exception {
        T = Integer.parseInt(br.readLine());
        Dy = new int[41][2];
        Dy[0][0]=1;
        Dy[1][1]=1;

    }

    static void preprocess(){
        for (int i = 2; i <= 40; i++) {
            Dy[i][0] = Dy[i-1][0] + Dy[i-2][0];
            Dy[i][1] = Dy[i-1][1] + Dy[i-2][1];
        }
    }

    static void sol() throws Exception {
        preprocess();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(Dy[n][0]).append(" ").append(Dy[n][1]).append("\n");
        }
        System.out.println(sb);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
