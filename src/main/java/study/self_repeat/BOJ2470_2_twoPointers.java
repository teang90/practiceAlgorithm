package study.self_repeat;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2470_2_twoPointers {
    // 산성 용액과 알칼리성 용액의 특성값이 주어졌을 때, 이 중 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 찾는 프로그램을 작성하시오.
    static int N;
    static int[] nums;
    static Scanner sc = new Scanner(System.in);
    static void input(){
        N = Integer.parseInt(sc.nextLine().trim());
        nums = new int[N];
        String[] solArr = sc.nextLine().trim().split(" ");
        for(int i=0; i<N; i++) nums[i]=Integer.parseInt(solArr[i]);
    }

    static void sol(){
        Arrays.sort(nums);
        int vol1=0, vol2=0, minSum = Integer.MAX_VALUE;
        // 정렬을 한 상태라면 양쪽 끝에서부터 당기면서 합의 최소 값을 찾는다
        int L=0, R= N-1;
        while (L < R){
            int sumSol = nums[L]+nums[R];
            if(Math.abs(sumSol) < minSum){  // 더 작으면 지금 상태의 vol1, vol2 갖고있기
                vol1= nums[L];
                vol2= nums[R];
                minSum = Math.abs(sumSol);
            }
            if(sumSol < 0) L++;
            else R--;
        }

        System.out.println(vol1+" "+vol2);
    }

    public static void main(String[] args) {
        input();
        sol();
    }
}
