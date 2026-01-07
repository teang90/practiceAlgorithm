package study.self_repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2805_3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] trees;

    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        trees = new int[N];
        for (int i = 0; i < N; i++) trees[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(trees);
    }

    static void sol() {
        int L=0, R=1_000_000_000, H=0;
        while (L<=R){
            int mid = (L+R)/2;
            if(possible(mid)){
                L=mid+1;
                H=mid;
            }else{
                R=mid-1;
            }
        }
        System.out.println("H = " + H);
    }

    static boolean possible(int H){
        int res = 0;

        for (int i = 0; i < trees.length; i++) {
            if(trees[i] > H){
                res+=trees[i]-H;
            }
        }

        return res >= M;
    }



}