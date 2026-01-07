package study.ryuhosuk.practice.two_pointers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ16472 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static String msg;
    static int[] cntArr;
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        msg = br.readLine();
        cntArr = new int[27];
    }
    static boolean checkPossibleAndPut(int idx) {
        idx = msg.charAt(idx) - 'a';
        cntArr[idx]++;
        int occupiedCnt = 0;
        boolean firstOccupy = cntArr[idx]==1;

        for (int i = 0 ; i < cntArr.length; i++) {
            if(cntArr[i]>0) occupiedCnt++;
        }

        if(occupiedCnt > N){
            cntArr[idx]--;
            if(firstOccupy) occupiedCnt--;
            return false;
        }

        return occupiedCnt <= N;
    }
    static void sol() {
        int R=-1, len=Integer.MIN_VALUE;

        for (int L=0; L<msg.length(); L++){
            while (R+1 < msg.length() && checkPossibleAndPut(R+1)){
                R++;
                len=Math.max(len, R-L+1);
            }

            cntArr[msg.charAt(L)-'a']--;
        }
        System.out.println(len);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
