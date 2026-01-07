package study.practice.bruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_14888_연산자끼워넣기 {
    private static int N;
    private static int[] nums;
    private static int[] opers;
    private static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        String[] split = br.readLine().split("\\s+");
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(split[i]);

        opers = new int[4];
        String[] operators = br.readLine().split("\\s+");
        for (int i = 0; i < 4; i++)
            opers[i] = Integer.parseInt(operators[i]);
    }

    public static void main(String[] args) {
        try{
            input();
            sol(1, nums[0]);
            System.out.println(max);
            System.out.println(min);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void sol(int k, int sum){
        if(k==N){
            min = Math.min(min, sum);
            max = Math.max(max, sum);
            return;
        }

        for (int cand = 0; cand < 4; cand++) {
            if(opers[cand]<=0) continue;

            opers[cand]--;
            sol(k+1, getSum(k, sum, cand));
            opers[cand]++;
        }
    }

    private static int getSum(int k, int sum, int cand) {
        int _sum = sum;
        if(cand ==0) _sum += nums[k];
        if(cand ==1) _sum -= nums[k];
        if(cand ==2) _sum *= nums[k];
        if(cand ==3) _sum /= nums[k];
        return _sum;
    }


}
