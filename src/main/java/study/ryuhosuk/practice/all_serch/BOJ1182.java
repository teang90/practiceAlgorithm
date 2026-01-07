package study.ryuhosuk.practice.all_serch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, S, answer;
    static int[] nums;
    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        nums = new int[N];
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());
    }
    static void recur(int idx, int sum){
        if(idx==N){
            if(sum==S) answer++;
        }else {
            recur(idx+1, sum+nums[idx]);
            recur(idx+1, sum);
        }
    }

    static void sol() {
        recur(0, 0);
        if(S==0){   // 아무것도 더하지 않는 경우...
            answer--;
        }
        System.out.println(answer);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
