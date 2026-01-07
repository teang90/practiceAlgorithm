package study.ryuhosuk.practice.two_pointers;

import java.util.Scanner;

public class BOJ1806_solution {
    static int N;
    static int S;
    static int[] A;
    static Scanner sc = new Scanner(System.in);
    static void input(){
        String[] a = sc.nextLine().split(" ");
        N = Integer.parseInt(a[0]);
        S = Integer.parseInt(a[1]);
        A = new int[N];
        String[] nums = sc.nextLine().split(" ");
        for(int i=0; i <= N; i++) A[i] = Integer.parseInt(nums[i]);
    }

    static void sol(){
       int R=-1, sum=0, ans = N+1;
       for(int L=0; L < N+1; L++){
           // L-1을 구간에서 제외하기(이전의 L값 을 sum에서 -해주기)
           if(L!=0) sum -= A[L-1];

           // R을 옮길 수 있을 땨까지 옮기기
           while (R+1 <= N && sum < S){
               R++;
               sum+=A[R];
           }

           // [L...R]의 부분합이 조건 만족하면 ans 정답 갱신
           if(sum>=S){
               ans = Math.min(ans, R-L+1);
           }
       }
        if(ans == N+1){
            ans = 0;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        sol();
    }
}
