package study.ryuhosuk.practice.all_serch;

public class TEST {
    private int n;
    private int[] board;
    private int count;

    public int solveNQueen(int n) {
        this.n = n;
        board = new int[n];
        count = 0;
        solve(0);
        return count;
    }

    private void solve(int row) {
        if (row == n) {
            count++;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(row, col)) {
                board[row] = col;
                solve(row + 1);
            }
        }
    }

    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

}
