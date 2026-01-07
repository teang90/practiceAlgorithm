package study.self_repeat;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2470_4_by_binSearch {
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
        int sol1 = 0, sol2 = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int X = nums[i] * -1;
            int targetSolIdx = binarySearch(nums, i + 1, nums.length - 1, X);
            System.out.println("targetSolIdx = " + targetSolIdx);
            if(i < targetSolIdx && targetSolIdx <= nums.length -1  &&
                    Math.abs(nums[i]+nums[targetSolIdx]) <= min){

                min=Math.abs(nums[i]+nums[targetSolIdx]);
                sol1 = nums[i];
                sol2 = nums[targetSolIdx];
            }

            if(i < targetSolIdx-1 && targetSolIdx-1 <= nums.length -1  &&
                    Math.abs(nums[i]+nums[targetSolIdx-1]) <= min){

                min=Math.abs(nums[i]+nums[targetSolIdx-1]);
                sol1 = nums[i];
                sol2 = nums[targetSolIdx-1];
            }
        }
        System.out.println(sol1+" "+sol2);
    }

    static int binarySearch(int[] nums, int L, int R, int X) {
        int res = R;
        while (L <= R){
            int mid = (L+R)/2;
            if(nums[mid]<X){
                L = mid+1;
            }else{
                res = mid;
                R = mid-1;
            }
        }

        return res;
    }



}
