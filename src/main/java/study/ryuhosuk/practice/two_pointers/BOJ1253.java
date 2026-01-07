package study.ryuhosuk.practice.two_pointers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1253 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] A;
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = new int[N];
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
    }
    static void sol() {
        Arrays.sort(A);
        int goodCnt=0;
        for (int i = 0; i < N; i++) {
            int L = 0, R = N-1;
            while (L < R) {
                if(i==R) {
                    R--;
                }else if(i==L){
                    L++;
                }else{
                    if (A[L]+A[R] == A[i]){
                        goodCnt++;
                        break;
                    }else if(A[L]+A[R] < A[i]){
                        L++;
                    }else if(A[L]+A[R] > A[i]){
                        R--;
                    }
                }


            }
        }

        System.out.println(goodCnt);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
