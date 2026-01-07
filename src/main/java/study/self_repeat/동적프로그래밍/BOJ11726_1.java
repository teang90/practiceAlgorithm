package study.self_repeat.동적프로그래밍;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ11726_1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] Dy;
    static int N;
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    private static void sol() {
        Dy = new int[1000+1];
        Dy[1] = 1;
        Dy[2] = 2;
        for (int i = 3; i <= N; i++) {
            Dy[i] = (Dy[i-1] + Dy[i-2]) % 10_007;
        }
        System.out.println("Dy = " + Dy[N]);
    }

}

