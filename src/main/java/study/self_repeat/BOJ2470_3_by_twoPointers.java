package study.self_repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2470_3_by_twoPointers {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] sols;
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        sols = new int[N];
        for (int i = 0; i < N; i++) sols[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(sols);
    }
    static void sol() {
        int sum = Integer.MAX_VALUE;

        int L=0, R = N-1, v1 = 0, v2 = 0;

        while (L<R){
            int partialSum = sols[L]+ sols[R];
            if(sum >= Math.abs(partialSum)) {
                sum = Math.abs(partialSum);
                v1 = sols[L];
                v2 = sols[R];
            }

            if(partialSum > 0){
                R--;
            }else if(partialSum < 0) {
                L++;
            }else if(partialSum==0){
                break;
            }
        }
        System.out.println(v1+" "+v2);
    }
    
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
