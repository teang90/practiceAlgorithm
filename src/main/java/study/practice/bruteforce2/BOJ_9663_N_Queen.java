package study.practice.bruteforce2;

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

        for (int _col = 0; _col < N; _col++) {


            /** loop를 (prev < row) 까지 처리하는 이유
             *   한 행에는 퀸을 하나만 둔다
             *   위에서부터 한 행씩 내려오며 배치한다
             *   그래서:
             *   row 이전 행들 → 이미 퀸이 놓여 있음
             *   row 이후 행들 → 아직 비어 있음
             *   ➡️ 비교 대상은 오직 이전 행들뿐
             */
            boolean isSafe = true;
            for (int prev = 0; prev < row; prev++) {
                if(isUnSafe(row, _col, prev, col[prev])){
                    isSafe = false;
                    break;
                }
            }

            if(isSafe){
                col[row] = _col;
                sol(row+1);
                col[row]=0;
            }

        }
    }


    private static boolean isUnSafe(int x1, int y1, int x2, int y2){
//        return y1 == y2
//                || Math.abs(x1-x2)==Math.abs(y1-y2)
//                ;
        return y1 == y2 || x1-y1 == x2-y2 || x1+y1==x2+y2;
    }


}
