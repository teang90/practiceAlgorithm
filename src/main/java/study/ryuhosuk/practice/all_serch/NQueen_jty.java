package study.ryuhosuk.practice.all_serch;

public class NQueen_jty {
    static int N;   // 입력받는 값
    static int ans; // 가능한 경우의 수
    static int[] col; // col[i]: i번 행의 퀸은 col[i]번 열애 놓았다는 기록 -> 어떤 열에 놓았는지의 정보
    static void recur(int row){ // 행
        if(row == N+1){ // 이미 1번부터 N번까지 다 놓았다는 것 -> 성공
            ans++;
        }else{
            // row번쨰 행을 모든 열에 놓아봐야하는 경우
            for (int c=1; c<=N; c++){ // N*N 체스판 -> i는 열

                boolean possible = true;
                for(int j=1; j<row-1; j++){ // 이전 행의 퀸들과 공격가능한지 유효성 체크
                    if(attackable(row, c, j, col[j])){
                        possible = false;
                        break;
                    }
                }

                if(possible){
                    col[row] = c; // -> 퀸의 좌표를 (row, c)으로 할당
                    recur(row+1); // 다음 행에 들어갈 수 있는 모든 열(column)을 조사
                    col[row] = 0;
                }
            }
        }
    }
    
    // Queen 끼리 공격 가능한지 여부를 반환(각 행에는 모두 1개씩만(DFS) 들어 있어서 각 좌표(체스판)의 열비교만 하면될듯)
    /** x1,y1 -> 새 행의 열에 놓을 위치, x2, y2 이전 row의 Queen 위치 */
    public static boolean attackable(int x1, int y1, int x2, int y2){
        boolean res = false;

        // 공격가능한 케이스들
        if(y1==y2) res= true;
        if(x1+y1 == x2+y2) return true;
        if(x1-y1==x2-y2) return true;

        return res;
    }




}
