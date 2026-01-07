package study.ryuhosuk.practice.two_pointers;

import java.util.Scanner;

public class BOJ1806 {
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
        for(int i=0; i<N; i++) A[i] = Integer.parseInt(nums[i]);
    }
    static void sol(){
        int cntOfElem = N;
//        int sum =0; // 구간 누적합
//        int p1=0;   // 첫 포인터    (하나씩 우측으로 이동하며, 이동할때 해당 idx의 값을 sum에서 빼줘야할듯?)
//        int p2=1;   // 두번째 포인터 (현재 누적합을 구한곳까지의 idx 값을 갖고있는데)

        int sum = 0;
        int R = -1;
        for(int L=0; L<N; L++){
            // L 고정상태에서 잡아놓고, R을 움직인다. S보다 크면 R은 멈추고, 누적합이 S보다 작을때까지 L을 하나씩 움직이면서 뺀다.
            if(L!=0) sum -= A[L-1];

            while (R+1<N && sum<S){
                R++;
                System.out.println(L+", "+R+" > "+A[L]+", "+A[R]);
                sum += A[R];
            }

            if(sum >= S){
                cntOfElem = Math.min(cntOfElem, R-L+1);
            }
        }

        if(cntOfElem==N){
            cntOfElem = 0;
        }
        System.out.println(cntOfElem);
    }

    public static void main(String[] args) {
        input();
        sol();
    }
}
