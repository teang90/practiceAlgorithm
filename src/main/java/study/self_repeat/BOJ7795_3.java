package study.self_repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ7795_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, A, B;
    static int[] A_arr, B_arr;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            input();
            sol();
        }
    }
    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        A_arr = new int[A];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < A; i++) A_arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(A_arr);

        B_arr = new int[B];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < B; i++) B_arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(B_arr);

    }
    static void sol() {
        int answer = 0;
        for (int i = 0; i < A; i++) {
            answer += binSearch(B_arr, 0, B-1, A_arr[i]);
        }
        System.out.println(answer);
    }
    static int binSearch(int[] B_arr, int L, int R, int X){
        int idx = 0;
        while (L <= R){
            int mid = (L+R)/2;
            if(B_arr[mid] < X){
                L = mid + 1;
                idx = mid+1;
            }else{
                R = mid - 1;
            }
        }

        return idx;
    }


}
