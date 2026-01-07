package study.ryuhosuk.practice.all_serch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, count, min=Integer.MAX_VALUE, max=Integer.MIN_VALUE ;
    static int[] nums, oper;// 숫자 순서 switch 불가
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");

        oper = new int[4];
        for (int i = 0; i < 4; i++) {
            oper[i] = Integer.parseInt(st.nextToken());
            count += oper[i];
        }

    }
    static void recur(int k, int sum){
        if(k==N){
            max = Math.max(max, sum);
            min = Math.min(min, sum);
        }else{
            for (int i = 0; i < 4; i++) {
                if(oper[i]>0){
                    oper[i]--;
                    if(i==0) recur(k+1, sum+nums[k]);
                    if(i==1) recur(k+1, sum-nums[k]);
                    if(i==2) recur(k+1, sum*nums[k]);
                    if(i==3) recur(k+1, sum/nums[k]);
                    oper[i]++;
                }
            }
        }
    }
    static void sol(){
        recur(1, nums[0]);
        System.out.println(max);
        System.out.println(min);
    }
    public static void main(String[] args) throws Exception{
        input();
        sol();
    }
}
