package study.ryuhosuk.practice.all_serch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ9663 {
    /** 진 행 중 */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, count;
    static int[] cols;
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        cols = new int[N];
    }
    static boolean unPossible(int row1, int col1, int row2, int col2){
        // 행이 같은지, 열이 같은지, 좌표의 |x-y|, |x+y| 값이 같은지
        if(row1==row2 || col1==col2 ||
                Math.abs(row1 - col1) == Math.abs(row2 - col2) ||
                Math.abs(row1 + col1) == Math.abs(row2 + col2)
        ){
            System.out.println(String.format("true << r1: %d, c1: %d, r2: %d, c2: %d", row1,col1,row2,col2));
            return true;
        }
        System.out.println(String.format("false >> r1: %d, c1: %d, r2: %d, c2: %d", row1,col1,row2,col2));
        return false;
    }

    // https://st-lab.tistory.com/118
    static boolean isValid(int row){
        for (int i = 0; i < row; i++) {
            if (cols[i] == cols[row] ||
                    Math.abs(row - i) == Math.abs(cols[row] - cols[i])
//                    || Math.abs(row + i) == Math.abs(cols[row] + cols[i])
            ) {
                return false;
            }
        }
        return true;
    }
    static void recur(int row) {
        if(row == N){
            count++;
        }else{
            for (int col = 0; col < N; col++) {
                cols[row] = col;
                if(isValid(row)){
                    recur(row+1);
                }
            }
        }

    }
    static void sol() {
        recur(0);
        System.out.println(count);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
