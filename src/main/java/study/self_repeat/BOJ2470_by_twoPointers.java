package study.self_repeat;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class BOJ2470_by_twoPointers {
    static int N;
    static int[] A;
    static Scanner sc = new Scanner(System.in);

    static void input(){
        N = Integer.parseInt(sc.nextLine().trim());
        A = new int[N];
        int[] a = {0};
        Stream.of(sc.nextLine().split(" ")).forEach(s->A[a[0]++] = Integer.parseInt(s));
    }

    
    // two pointers... -> 양쪽 끝에서부터 오는 포인터
    static void sol(){
        // 1. 주어진 배열 정렬
        Arrays.sort(A);

        int L = 0;      // 실제 용액 값이 있는 idx of start.
        int R = N-1;    // 실제 용액 값이 있는 idx of end.
        int minSum = Integer.MAX_VALUE;
        int vol1=0, vol2=0;
        while (L < R){   // L=R의 경우는 용액이 한개인 경우이므로 유효하지 않은 범위이다. -> L<R
            int sum = Math.abs(A[L] + A[R]);// minSum 과 비교하기 위해서는 절댓값을 씌워야한다.
            if(minSum >= sum){
                minSum = sum;
                vol1 = A[L];
                vol2 = A[R];
            }

            if(A[L] + A[R] < 0) L++;    //여기서 비교할때는 절댓값을 벗겨줘야만 L, R의 idx를 조정할 수 있다.
            else R--;
        }

        System.out.println(vol1+" "+ vol2);
    }
    public static void main(String[] args) {
        input();
        sol();
    }


}
