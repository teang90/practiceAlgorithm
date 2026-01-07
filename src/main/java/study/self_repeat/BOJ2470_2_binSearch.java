package study.self_repeat;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2470_2_binSearch {
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
    static int binarySearch(int[] nums, int L, int R, int X) {
        int res = R;
//        int res = R+1;
//        while (L<R) {
        while (L <= R) {
            int mid = (L+R)/2;
            System.out.println("mid> "+mid);
            if(nums[mid] >= X){
                res = mid;
                R = mid-1;
            }else
                L= mid + 1;
        }
        return res;
    }

    static void sol(){
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int vol1=0, vol2=0, minSum = Integer.MAX_VALUE;
        for(int i=0; i<N; i++) {    
            // A 용액과 더해서 0에 가장 가까운 값이 무엇인지 찾자 : A를 제외한 나머지 중에서 -A에 가장 가까운 값을 찾자
//            int targetIdx = binarySearch(nums, i+1, N, -nums[i]);
            int targetIdx = binarySearch(nums, i+1, N-1, -nums[i]);
            System.out.println(targetIdx);
//            if(i<targetIdx  && Math.abs(nums[i]+nums[targetIdx]) < minSum){
            if(i<targetIdx && targetIdx<N && Math.abs(nums[i]+nums[targetIdx]) < minSum){
                minSum = Math.abs(nums[i]+nums[targetIdx]);
                vol1 = nums[i];
                vol2 = nums[targetIdx];
            }

//            if(i < targetIdx-1 && Math.abs(nums[i]+nums[targetIdx-1]) < minSum){
            if(i < targetIdx-1 && targetIdx-1 < N && Math.abs(nums[i]+nums[targetIdx-1]) < minSum){
                minSum = Math.abs(nums[i]+nums[targetIdx-1]);
                vol1 = nums[i];
                vol2 = nums[targetIdx-1];
            }
        }
        System.out.println();
        System.out.println(vol1+" "+vol2);
    }

    public static void main(String[] args) {
        input();
        sol();
    }
}
