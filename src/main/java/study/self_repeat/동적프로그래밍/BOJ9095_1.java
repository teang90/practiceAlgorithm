package study.self_repeat.동적프로그래밍;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ9095_1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T;
    static int[] Dy;
    static void input() throws Exception {
        T = Integer.parseInt(br.readLine());
    }
    static void preprocess(){
        Dy = new int[11];
        Dy[1] = 1;
        Dy[2] = 2;
        Dy[3] = 4;
        Dy[4] = 7;
        for (int i = 4; i <= 10; i++) {
            Dy[i] = (Dy[i-1] + Dy[i-2] + Dy[i-3]);
        }

    }
    static void sol() throws Exception {
        preprocess();
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(Dy[N]);
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
