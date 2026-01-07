package study.self_repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13144_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] cntArr, listOfNum;
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        cntArr = new int[100001];
        listOfNum = new int[N+1];
        for (int i = 0; i < N; i++) listOfNum[i] = Integer.parseInt(st.nextToken());
    }
    static void sol() {
        int R = -1;
        long answer = 0;
        for (int L = 0; L < N; L++) {
            while (R+1<N && cntArr[listOfNum[R+1]]==0){
                cntArr[listOfNum[++R]]++;
                answer += R-L+1;
            }
            cntArr[listOfNum[L]]--;
        }
        System.out.println(answer);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

}
