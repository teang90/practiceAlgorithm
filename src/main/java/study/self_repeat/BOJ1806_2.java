package study.self_repeat;

import java.util.Scanner;

public class BOJ1806_2 {
    // 연속된 수로 구성된 N짜리 수열에서, 연속된 수들의 부분합에서 그 합이 S가 되는 것중 가장 짧은 것의 길이를 구하시오
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
        int minLength=Integer.MAX_VALUE;
        int R=-1, sum=0;
        for(int L=0; L<N; L++){
            if(L!=0) sum -= nums[L-1];  // TODO 여기를 loop 진행 초반에 뺴주지 말고 while 문 뒤에 배치하도록

            while (R+1 < N && sum < S){
                sum+=nums[++R];
            }

            if(sum >= S){
//            if(sum == S){ // 문제 조건이 S 이상이 되는 수중에서 찾는거임
                minLength = Math.min(minLength, R-L+1);
            }
//            System.out.println(String.format("sum: %d, L: %d, R: %d", sum, L, R));
        }
        if(minLength==Integer.MAX_VALUE) minLength=0;
        System.out.println(minLength);
    }
    public static void main(String[] args) {
        input();
        sol();
    }
}
