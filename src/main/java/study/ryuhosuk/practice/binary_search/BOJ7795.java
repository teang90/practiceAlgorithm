package study.ryuhosuk.practice.binary_search;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ7795 { // 이분탐색 연습: BOJ-1920, BOJ-1764, BOJ-3273, BOJ-10816

    static int totalN;
    static int Na;
    static int Nb;
    static int[] A;
    static int[] B;

    static Scanner sc = new Scanner(System.in);
    static void input(){
        Na = sc.nextInt();
        Nb = sc.nextInt();
        A = new int[Na];
        B = new int[Nb];

        String[] aParams = sc.nextLine().split(" ");
        for(int i=0; i<Na; i++){
            A[i]=Integer.parseInt(aParams[i]);
        }

        String[] bParams = sc.nextLine().split(" ");
        for(int i=0; i<Nb; i++){
            B[i]=Integer.parseInt(aParams[i]);
        }

    }
    // 이분 탐색으로 찾기
    static int lower_bound(int[] B, int L, int R, int X){
        // A[L . . . R]에서 X 미만의 수(X보다 작은) 중 제일 우측 인덱스를 return 하는 함수
        // 그런게 없다면 L-1을 return

        /* 이분탐색 start */
        int result = L-1;
        while (L <= R){
            int mid = (L+R)/2; // 가운데 위치
            if(B[mid] < X){
                result = mid;
                // 작은것중 오른쪽을 찾음.. -> 즉 L을 땡겨야함 -> 더 큰게 있나 찾아야함
                L = mid+1;
            }else if(B[mid] >= X){ // A의 원소보다 B[mid]가 크다면 더 A보다 크면서 B[mid]보다  더 작은 값을 찾아야함
                R = mid - 1;
            }
        }
        /* 이분탐색 end */
        return result;
    }

    static void pro(){
        // B 함수 정렬, 이분탐색 > 조건 1
        Arrays.sort(B);

        int sumResult=0;
        for (int i=0; i<Na; i++){
            // A[i]를 선택했을때 B에서는 A[i]보다 작은 게 몇개나 있는지 count하기
            sumResult += lower_bound(B, 1, Nb, A[i]);
        }

        System.out.println(sumResult);
    }


    public static void main(String[] args) {
        int totalTryCnt = sc.nextInt();
        for(int i=0; i<totalTryCnt; i++){
            input();
            pro();
        }
    }

}
