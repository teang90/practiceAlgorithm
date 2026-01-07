package study.self_repeat;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1253_1 {
    // N개의 수 중에서 어떤 수가 다른 수 두 개의 합으로 나타낼 수 있다면 그 수를 “좋다(GOOD)”고 한다.
    // N개의 수가 주어지면 그 중에서 좋은 수의 개수는 몇 개인지 출력하라.
    // 수의 위치가 다르면 값이 같아도 다른 수이다.
    static int N;
    static int[] nums;
    static Scanner sc = new Scanner(System.in);
    static void input(){
        N = Integer.parseInt(sc.nextLine().trim());
        String[] arr = sc.nextLine().split(" ");
        nums = new int[N];
        for(int i=0; i<N; i++) nums[i] = Integer.parseInt(arr[i]);
    }

    static void sol(){
        Arrays.sort(nums);  // O(NlogN)
//        int L=0, R=1000000000;
        int answer = 0; // good 갯수
        for(int idx=0; idx<N; idx++){     // O(N)
            // nums[L] 이라는 숫자를 나머지 합으로 만들 수 있나?
            int L=0, R=N-1;
            while (L < R){
                if(idx == L) L++;
                else if(idx == R) R--;
                if(L==R) continue;      // idx: 1, L: 0, R: 0 이런 케이스가 있음 그래서 아래의 while 처럼 해주던가, L==R을 잡아주던가...

                if(nums[L]+nums[R] == nums[idx]){
                    answer++;
//                    System.out.println("idx: "+idx+", L: "+L+", R: "+R);
                    break;
                }
                else if(nums[L]+nums[R] < nums[idx]) L++;
                else if(nums[L]+nums[R] > nums[idx]) R--;
            }
//            while (L < R){
//                if(idx == L) L++;
//                else if(idx == R) R--;
//                else{
//                    if(nums[L]+nums[R] == nums[idx]){
//                        answer++;
////                    System.out.println("idx: "+idx+", L: "+L+", R: "+R);
//                        break;
//                    }
//                    else if(nums[L]+nums[R] < nums[idx]) L++;
//                    else if(nums[L]+nums[R] > nums[idx]) R--;
//                }
//            }
        }
        System.out.println(answer);
    }
    public static void main(String[] args) {
        input();
        sol();
    }

}
