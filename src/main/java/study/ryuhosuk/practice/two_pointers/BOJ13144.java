package study.ryuhosuk.practice.two_pointers;

import java.util.Scanner;

public class BOJ13144 {
    // 길이가 N인 수열이 주어질때, 수열에서 연속한 1개 이상의 수를 뽑았을때,
    // 같은 수가 여러 번 등장하지 않는 경우의 수를 구하라.
    static int N;
    static int[] A;
    static Scanner sc = new Scanner(System.in);
    static void input(){
        N = Integer.parseInt(sc.nextLine().trim());
        String[] arr = sc.nextLine().split(" ");
        A = new int[N];
        for (int i=0; i<N; i++) A[i]=Integer.parseInt(arr[i]);
    }

    static void sol(){
        int R=0;
        int ans=0;

        for(int L=0; L<N; L++){



        }






    }

    public static void main(String[] args) {
        input();
        sol();
    }

}