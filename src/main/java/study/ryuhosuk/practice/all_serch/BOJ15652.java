package study.ryuhosuk.practice.all_serch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15652 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] nums;
    static StringBuilder sb = new StringBuilder();
    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[M+1];
    }
    static void recur(int k){
        if(k == M+1){
            for (int i = 0; i < M; i++) {
                sb.append(nums[i+1]).append(" ");
            }
            sb.append("\n");
        }else{
            int start = nums[k-1];
            if(start==0) start=1;
            for (int i = start; i <= N; i++) {
                nums[k] = i;
                recur(k+1);
                nums[k] = 0;
            }
        }

    }

    static void sol(){
        recur(1);
        System.out.println(sb);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
