package study.ryuhosuk.practice.dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ15988 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T;
    static long[] dpArr;
//    static int[] dpArr;       ☆☆☆ 중요 ☆☆☆ -> int 로해서 틀렸었음..
    static void input() throws Exception {
        T = Integer.parseInt(br.readLine());
        dpArr = new long[1000001];
    }
    static void preprocess(){
        dpArr[1]=1;
        dpArr[2]=2;
        dpArr[3]=4;
        for (int i = 4; i <= 1000000; i++) {
            dpArr[i] = (dpArr[i-1]+dpArr[i-2]+dpArr[i-3]) % 1000000009;
        }
    }
    static void sol() throws Exception{
        preprocess();
        for (int i = 0; i < T; i++) {
            System.out.println(dpArr[Integer.parseInt(br.readLine())]);
        }
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
