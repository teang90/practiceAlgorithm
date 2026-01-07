package study.self_repeat;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2805_4 {

    static int N; // 나무의 수 N: [1, 1000000]
    static int M;   // 상근이가 필요로하는 목재의 총 길이 M: [1, 2000000000]
    static int[] treeList; // [0, 1000000000]
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        input();
        sol();
    }
    static void input() {
        String[] NM = sc.nextLine().split(" ");
        N = Integer.parseInt(NM[0]);
        M = Integer.parseInt(NM[1]);

        String[] trees = sc.nextLine().split(" ");
        treeList = new int[N];
        for(int i=0; i<N; i++) treeList[i] = Integer.parseInt(trees[i]);

    }

    // 절단 높이 H 지정, -> 지면으로부터 H미터 올라서 연속한 나무 모두 절단 -> A' 나무의 나무 높이 A - H를 얻게된다.
    // 적어도 M미터의 나무를 집에 가져가기 위해서, 절단기에 설정할 수 있는 H의 최댓값을 구하라
    static void sol() {
        int L=0, R= 1_000_000_000, answer=0;
        while (L<=R){
            int mid = (L+R)/2;
            if(determine(mid)){
                L = mid+1;
                answer = mid;
            }else{
                R = mid-1;
            }
        }
        System.out.println("answer = " + answer);
    }

    static boolean determine(int H){
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if(treeList[i]-H>0)
                sum+=treeList[i]-H;
        }
        return sum >= M;
    }

    // TODO 나무 파라미터 값중에서 L, R 범위 찾아서 해보기 ->
    //  이 풀이가 좀 더 나은듯?, 실제 파라미터 H를 실제로 주어진 값 범위 내에서 적당히 표현되는지 생각해서 변수 범위를 잡도록하자.
    static void sol2(){

    }



}
