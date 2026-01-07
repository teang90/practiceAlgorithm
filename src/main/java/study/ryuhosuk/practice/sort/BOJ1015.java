package study.ryuhosuk.practice.sort;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1015 {
    public static void main(String[] args) {
        input();
        pro();
    }
    static int N;
    static Elem[] B;
    static int[] P;
    static void input(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        // B 배열 세팅
        B = new Elem[N];
        String[] nums = sc.nextLine().split(" ");
        for (int i=0; i<N; i++){
            Elem elem = new Elem();
            elem.num = Integer.parseInt(nums[i]);
            elem.idx = i;
            B[i] = elem;
        }

        // P 배열 세팅
        P = new int[N];


    }
    static void pro(){
        // 1. B 배열 정렬하기 (A배열이 B배열이 됨)
        Arrays.sort(B);

        // 2. B 배열의 값을 이용하여 P배열 채우기
        for (int bIdx=0; bIdx<N; bIdx++){
            P[B[bIdx].idx] = bIdx;
//            B[bIdx].idx = P[bIdx]; --> 이거는 뭘 뜻하지?
        }

        // 3. P 배열 출력
        StringBuilder sb = new StringBuilder();
        for (int a=0; a<N; a++){
            sb.append(a).append(" ");
        }

        System.out.println(sb.toString());
    }
    static class Elem implements Comparable<Elem>{
        int num;        // A배열의 idx위치를 기억하는 변수
        int idx;        // A[idx] 의 원래 값
        @Override
        public int compareTo(Elem o) {
            // num의 비내림차순
            return num - o.num;
        }
    }
}