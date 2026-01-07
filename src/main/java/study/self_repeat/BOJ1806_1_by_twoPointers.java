package study.self_repeat;

import java.util.Scanner;
import java.util.stream.Stream;

public class BOJ1806_1_by_twoPointers {
    static int N;
    static int S;
    static int[] A;
    static Scanner sc = new Scanner(System.in);
    static void input(){
        String[] arr = sc.nextLine().split(" ");
        N = Integer.parseInt(arr[0]);
        S = Integer.parseInt(arr[1]);
        A = new int[N];
        int[] a = {0};
        Stream.of(sc.nextLine().split(" ")).forEach(s->A[a[0]++] = Integer.parseInt(s));
    }
    // 투 포인터: 한쪽 방향으로 두 포인터가 움직이는 경우
    static void sol(){
        int R=-1;
        int ans = Integer.MAX_VALUE;
        int sum=0;
        for(int L=0; L<N; L++){
            if(L != 0)
                sum-=A[L-1];

            while (R+1<N && sum<S){
                sum+=A[++R];
            }

            if(sum >= S)
                ans = Math.min(ans, R-L+1);
        }

        if(ans==Integer.MAX_VALUE)
            ans = 0;

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        sol();
    }
}
