package study.ryuhosuk.practice.dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ15991 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T;
    static long[] dpArr;
    static void input() throws Exception {
        T = Integer.parseInt(br.readLine());
        dpArr = new long[100001];
    }
    static void preprocess(){
        dpArr[1] = 1;
        dpArr[2] = 2;
        dpArr[3] = 2;
        dpArr[4] = 3;
        dpArr[5] = 3;
        dpArr[6] = 6;
        dpArr[7] = 6;
        for (int i = 8; i <= 100000; i++) {
            dpArr[i] = (dpArr[i - 1*2]+dpArr[i - 2*2] + dpArr[i - 3*2]) % 1000000009;
        }
    }
    static void sol() throws Exception {
        preprocess();
        for (int i = 0; i < T; i++) {
            System.out.println(dpArr[Integer.parseInt(br.readLine())]);
        }

    }

    public static void main(String[] args) throws Exception  {
        input();
        sol();
    }
}
