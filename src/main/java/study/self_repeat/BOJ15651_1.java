package study.self_repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15651_1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] answer;
    static StringBuilder sb = new StringBuilder();
    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = new int[M+1];
    }
    static void recur(int k) {
        if(k==M+1){
            for (int i = 1; i <= M; i++) {
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
        }else{
            for (int i = 1; i <= N; i++) {
                answer[k]=i;
                recur(k+1);
                answer[k]=0;
            }
        }
    }
    static void sol() {
        // 길이 M인 수열을 모두 구하라
        // 1부터 N까지 자연수중 M개를 고른 수열, 중복 허용
        recur(1);
        System.out.println(sb);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

}
