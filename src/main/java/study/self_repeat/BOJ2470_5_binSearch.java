package study.self_repeat;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2470_5_binSearch {
    static int N;
    static int[] nums;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        input();
        sol();
    }

    static void input(){
        N = Integer.parseInt(sc.nextLine().trim());
        nums = new int[N];
        String[] solArr = sc.nextLine().trim().split(" ");
        for(int i=0; i<N; i++) nums[i]=Integer.parseInt(solArr[i]);
    }
    static void sol(){
        Arrays.sort(nums);

        int sol1=0, sol2=0, minVal=Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int targetIdx = binSearch(nums, i+1, N - 1, -1 * nums[i]);
            System.out.println("targetIdx = " + targetIdx);
            if( i<targetIdx && targetIdx<=nums.length-1 && Math.abs(nums[i]+nums[targetIdx]) <= minVal){
                minVal=Math.abs(nums[i]+nums[targetIdx]);
                sol1=nums[i];
                sol2=nums[targetIdx];
            }

            if( i<targetIdx-1 && targetIdx-1<=nums.length-1 && Math.abs(nums[i]+nums[targetIdx-1]) <= minVal){
                minVal=Math.abs(nums[i]+nums[targetIdx-1]);
                sol1=nums[i];
                sol2=nums[targetIdx-1];
            }
            
        }
        System.out.println(sol1+" " + sol2);
    }
    static int binSearch(int[] nums, int L, int R, int X){
        int resIdx = R;
        while (L <= R){
            int mid = (L+R)/2;
            if(nums[mid] < X){
                L=mid+1;
            }else{
                R=mid-1;
                resIdx = mid;
            }
        }
        return resIdx;
    }

}
