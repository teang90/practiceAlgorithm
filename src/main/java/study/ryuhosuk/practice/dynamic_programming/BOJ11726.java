package study.ryuhosuk.practice.dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ11726 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] Dy;
    static int N;
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
    }
    static void sol() {
        Dy = new int[1001];
        Dy[1] = 1;  // 1
        Dy[2] = 2;  // 11, =
        Dy[3] = 3;  // =1, 1=, 111
        Dy[4] = 5;  // 11=, 1=1,=11, 1111, ==

        for (int i = 3; i <= N; i++) {
            Dy[i] = (Dy[i-1] + Dy[i-2]) % 10007;
        }

        System.out.println(Dy[N]);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
