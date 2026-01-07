package study.self_repeat;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class BOJ2470_by_binSearch {
    static int N;
    static int[] A;
    static Scanner sc = new Scanner(System.in);

    static void input(){
        N = Integer.parseInt(sc.nextLine().trim());
        A = new int[N];
        int[] a = {0};
        Stream.of(sc.nextLine().split(" ")).forEach(s->A[a[0]++] = Integer.parseInt(s));
    }
    static int binarySearch(int[] A, int L, int R, int X){
        int result = R+1;// 상한 값이 없는 곳으로...
        
        while (L<=R){
            int mid = (L+R)/2;
            if(A[mid] >= X){
                R = mid-1;
                result = mid;
            }else{
                L = mid+1;
            }
        }

        return result;
    }

    // 이분탐색
    static void sol(){
        // 1. 주어진 배열 정렬
        Arrays.sort(A);

        // 2. L, R, mid 로 탐색
        // 더해서 0에 가까운 값을 찾아야한다. A[i]에 가장 가까운 값을 찾기 위해 -A[i]와 가장 가까운 값을 찾아서 더해서 0에 가까운지 check
        int vol1=0, vol2=0, minVal = Integer.MAX_VALUE;
        for(int i=0; i<N; i++){
            int targetIdx = binarySearch(A, i+1, N-1, -A[i]);

            if((i < targetIdx && targetIdx < N) && Math.abs(A[i]+A[targetIdx]) < minVal){
                minVal = Math.abs(A[i]+A[targetIdx]);
                vol1 = A[i];
                vol2 = A[targetIdx];
            }

            if((i < targetIdx-1 && targetIdx-1 < N)&& Math.abs(A[i]+A[targetIdx-1]) < minVal){
                minVal = Math.abs(A[i]+A[targetIdx-1]);
                vol1 = A[i];
                vol2 = A[targetIdx-1];
            }
        }

        System.out.println(vol1+" "+vol2);

    }
    public static void main(String[] args) {
        input();
        sol();
    }


}
