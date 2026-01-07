package study.ryuhosuk.practice.dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ11052 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] p, Dy;
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        p = new int[1001];
        String[] cards = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            p[i] = Integer.parseInt(cards[i-1]);
        }
        Dy = new int[1001];
    }
    static void sol() throws Exception {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                Dy[i] = Math.max(Dy[i], Dy[i-j]+p[j]);
            }
        }
        System.out.println(Dy[N]);
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

}
