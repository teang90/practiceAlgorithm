package study.practice.bruteforce2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 순열,
public class BOJ_15663_N과M_9 {
    private static int N, M;
    private static int[] selected;
    private static int[] nums;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        input();
        sol(0);
        System.out.println(sb);
    }

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        nums = new int[N];
        selected = new int[M];
        String[] numsStr = br.readLine().split("\\s+");
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(numsStr[i]);

        visited = new boolean[N];
        Arrays.sort(nums);
    }

    private static void sol(int k){
        if(k==M){
            for (int i = 0; i < M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int prev = 0; // 같은 depth에서 사용하고 있는 값 (recursive하는 동안 prev에는 재귀에서 돌고있는 depth의 값이 할당되어있음)
        for (int i = 0; i <N; i++) {
            if(visited[i]) continue;
            if(prev==nums[i]) continue;

            prev = nums[i];
            visited[i] = true;
            selected[k] = nums[i];

            sol(k+1);

            visited[i] = false;
            selected[k] = 0;
        }





    }

}
