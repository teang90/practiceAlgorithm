package study.self_repeat.동적프로그래밍;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ10870 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T;
    static int[] Dy;
    static void input() throws Exception {
        T = Integer.parseInt(br.readLine());
        Dy = new int[21];
    }
    static void sol() {
        Dy[0]=0;
        Dy[1]=1;
        Dy[2]=1;
        for (int i = 3; i <= 20; i++) {
            Dy[i] = (Dy[i-1] + Dy[i-2]);
        }

    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
        System.out.println(Dy[T]);
    }
}
