package study.self_repeat;

import java.util.Arrays;
import java.util.Scanner;

// 심해에는 두 종류의 생명체 A와 B가 존재한다. A는 B를 먹는다. A는 자기보다 크기가 작은 먹이만 먹을 수 있다.
// 예를 들어, A의 크기가 {8, 1, 7, 3, 1}이고, B의 크기가 {3, 6, 1}인 경우에 A가 B를 먹을 수 있는 쌍의 개수는 7가지가 있다. 8-3, 8-6, 8-1, 7-3, 7-6, 7-1, 3-1.
public class BOJ7795_1 {
    static Scanner sc = new Scanner(System.in);
    static int tt;
    static void input(){
        tt = Integer.parseInt(sc.nextLine().trim());
    }
    static void sol(){
        int A, B;

        for(int i=0; i<tt; i++){
            int[] numsA, numsB;
            String[] AandB = sc.nextLine().trim().split(" ");
            A = Integer.parseInt(AandB[0]);
            B = Integer.parseInt(AandB[1]);

            numsA = new int[A];
            String[] strA = sc.nextLine().trim().split(" ");
            for(int j=0; j<A; j++) numsA[j] = Integer.parseInt(strA[j]);

            numsB = new int[B];
            String[] strB = sc.nextLine().trim().split(" ");
            for(int j=0; j<B; j++) numsB[j] = Integer.parseInt(strB[j]);
            //SOLUTION
            // 정렬해서, A의 특정 원소보다 미만인 원소가 몇개인지 찾기 -> A의 특정 원소보다 작은 것중 가장 큰 B의 원소를 찾자
            Arrays.sort(numsB);
            System.out.println(Arrays.toString(numsB));
            int ans = 0;
            for(int a=0; a<A; a++){
                int res = binarySearch(numsB, 0, B-1, numsA[a]);    
                // B-> B-1로 바꾼 이유, 실제 value는 idx:[0, B-1] 범위로 있기떄문
//                int res = binarySearch(numsB, 0, B, numsA[a]);
                System.out.println(res);
                ans += res;
            }
            System.out.println(ans);
        }
    }
    static int binarySearch(int[] nums, int L, int R, int X){
        int res = 0;
        while (L<=R){
            int mid = (L+R)/2;
            if(nums[mid] >= X){
                R = mid -1;
            }else{
                res = mid+1;
//                res = mid;    // res를 mid -> mid+1로 바꾼이유, mid는 idx값이기 때문에 실제 해당 dix보다 작은 갯수는 +1해줘야함
                // 실제 배열에서 3번쨰라면 idx로는 2라서 실제 해당 원소보다 작은 것들의 갯수는 3개임
                L = mid+1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        input();
        sol();
    }
}