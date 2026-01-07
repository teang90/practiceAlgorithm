package study.ryuhosuk.practice.dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ10870 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] Dy;
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        Dy = new int[21];
    }
    static void preprocess(){
        Dy[0]=0;
        Dy[1]=1;
        for (int i = 2; i < 21; i++) {
            Dy[i] = Dy[i-1]+Dy[i-2];
        }
    }
    static void sol(){
        preprocess();
        System.out.println(Dy[N]);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
