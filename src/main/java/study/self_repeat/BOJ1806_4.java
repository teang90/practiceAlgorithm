package study.self_repeat;

import java.util.Scanner;

public class BOJ1806_4 {
    static int N;
    static int S;
    static int[] nums; // [1, 10000]
    static Scanner sc = new Scanner(System.in);
    static void input() {
        String[] NS = sc.nextLine().split(" ");
        N = Integer.parseInt(NS[0]);
        S = Integer.parseInt(NS[1]);

        nums = new int[N];
        String[] numStr = sc.nextLine().split(" ");
        for(int i=0; i<N; i++) nums[i] = Integer.parseInt(numStr[i]);
    }
    static void sol() {

    }
    public static void main(String[] args) {
        input();
        sol();
    }
}
