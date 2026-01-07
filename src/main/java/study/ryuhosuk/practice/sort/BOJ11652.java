package study.ryuhosuk.practice.sort;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ11652 {//  +) 20291 파일정리도 유사유형
    public static void main(String[] args) {
        input();
        pro();
    }
    static int N;
    static long[] nums;
    static void input(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        nums = new long[N];
        for (int i=0; i<N; i++){
            nums[i] = sc.nextLong();
        }
    }
    static void pro(){
        // 1) 정렬
        Arrays.sort(nums);

        // 2) 다음 숫자로 넘어갈때 동일하면 +1, 다른 숫자면 수 변경 및 max인지 비교
//        int maxCnt=Integer.MAX_VALUE;
        int maxCnt=1;
        int curCnt=1;
        long manyNum=nums[0];
        for(int i=1; i<N; i++){
            if(nums[i-1]==nums[i]){ // 동일할떄
                curCnt++;
            }else{ // 동일하지 않을떄 -> 카운트 초기화 및 최빈수가 무엇인지 판단
                curCnt=1;
            }

            if(curCnt > maxCnt){
                manyNum = nums[i];
                maxCnt=curCnt;
            }
        }
        System.out.println(manyNum);
    }



}
