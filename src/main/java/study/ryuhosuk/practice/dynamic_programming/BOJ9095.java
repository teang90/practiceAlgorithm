package study.ryuhosuk.practice.dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ9095 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T;
    static int[] Dy;
    static void input() throws Exception {
        T = Integer.parseInt(br.readLine());
    }
    static void preprocess(){
        Dy = new int[15];

        // 1. 초기값 구하기
        Dy[1] = 1;
        Dy[2] = 2;  // 1+1, 2
        Dy[3] = 4;  // 1+1+1, 1+2, 2+1, 3

        // 2. 점화식을 토대로 Dy 배열 채우기
        for (int i=4; i<=11; i++){
            Dy[i] = Dy[i-1] + Dy[i-2] + Dy[i-3];
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
