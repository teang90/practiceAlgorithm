package study.self_repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1806_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;
    static int N, S;
    static int[] A;
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        A = new int[N+1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }
    static void sol(){
        int R = 0, sum = 0;
        int len = Integer.MAX_VALUE;
        for (int L = 0; L <= N; L++) {
            sum -= A[L];

            while (0 < R+1 && R+1 <= N && sum < S){
                sum += A[++R];
            }

            if(sum >= S) len = Math.min(len, R-L);
        }
        System.out.println(len==Integer.MAX_VALUE?0:len);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();

    }

}
