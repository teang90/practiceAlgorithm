package study.ryuhosuk.practice.dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2011 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String str = "";
    static void input() throws Exception {
        str = br.readLine();
    }
    static void search(int idx) {
        if(idx==str.length()){

        }else{
            int num = str.charAt(idx)+str.charAt(idx+1);

        }
    }
    static void sol() throws Exception {

    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
