package study.ryuhosuk.practice.all_serch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1759 {
    static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
    static int L, C;
    static int[] chars;
    static int[] selected;
    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        selected = new int[C];

        String line = br.readLine().replace(" ", "");
        chars = new int[C];
        for (int i = 0; i < C; i++) {
            chars[i] = line.charAt(i);
        }
    }
    static void recurs(int idx){
        if(idx==C){

        }else{

        }
    }
    static void sol(){
        Arrays.sort(chars);
        recurs(0);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
