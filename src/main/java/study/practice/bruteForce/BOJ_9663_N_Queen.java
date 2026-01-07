package study.practice.bruteForce;

import javax.lang.model.SourceVersion;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
 * N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.
 *
 * 첫째 줄에 N이 주어진다. (1 ≤ N < 15)
 * 첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.
 */
public class BOJ_9663_N_Queen {
    private static int N, ANSWER;
    private static int[] col;


    public static void main(String[] args) throws Exception {
        input();
        sol(0);
        System.out.println(ANSWER);
    }

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        col = new int[N];

    }

    private static void sol(int row){
        if(row==N) {
            ANSWER++;
            return;
        }

//        // 내가 생각했던 로직 -> Q, Q 인접한것만 안전하면 재귀로 넣어버림
//        // -> 문제는 Q, Q(안전), Q (뒤에 Q가 더 나옴) -> 그래서 모든 Q의 관계에서 가능여부를 찾아야함
//        // 내가 작성한 코드 BEGIN
//        for (int c = 0; c < N; c++) {
//            boolean isSafe = false;
//            for (int d = 0; d < row; d++) {
//                if(!isUnSafe(row, c, d, col[d])){
//                    isSafe = true;
//                    break;
//                }
//            }
//
//            if(isSafe) {
//                col[row]=c;
//                sol(row+1);
//                col[row]=0;
//            }
//
//        }
//        // 내가 작성한 코드 END
        for (int c = 0; c < N; c++) {
            boolean isSafe = true;
            for (int d = 0; d < row; d++) {
                if(isUnSafe(row, c, d, col[d])){
                    isSafe = false;
                    break;
                }
            }

            if(isSafe) {
                col[row]=c;
                sol(row+1);
                col[row]=0;
            }
        }
    }

    private static boolean isUnSafe(int x1, int y1, int x2, int y2){
//        return y1 == y2
//                || Math.abs(x1-x2)==Math.abs(y1-y2)
//                ;
        return y1 == y2
                || x1-y1==x2-y2
                || x1+y1==x2+y2
                ;
    }


}
