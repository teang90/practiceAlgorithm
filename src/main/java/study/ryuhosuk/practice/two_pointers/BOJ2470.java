package study.ryuhosuk.practice.two_pointers;

public class BOJ2470 {
    // 정렬
    // 최소 + 최대 <0
    // 최소입장에서는 최선의 +를 했으니 나머지 음수값들은 고려 X
    // 최소 + 최대 >0
    // 최대 입장에서는 최선을 만남,
    static int N;   // 용액 총 갯수
    static int[] A;   // 용액 총 갯수
    static void sol(){
        // nums 정렬

        // 변수
        int best_sum = Integer.MAX_VALUE;
        int v1 =0, v2 =0, L =1, R=N;

        // loop 돌면서
        while (L < R){
            int sum = A[L] + A[R];
            if(Math.abs(sum) < best_sum){
                best_sum = Math.abs(sum);
                v1 = A[L];
                v2 = A[R];
            }

            if(sum > 0) R--;  // 정렬된 상태에서는 0보다 크면 어차피 최소랑 더했을테니 현재 가장 큰 값인 R을 좌측으로 떙겨서 0에 가까운 수를 찾는다.
            else L++; // 정렬된 상태에서는 0보다 작으면 어차피 최대랑 더했을테니 현재 가장 작은 값인 L을 우측으로 떙겨서 0에 가까운 수를 찾는다. 
        }
        // 두 합이 best_sum보다 작으면 0에 가까운 값을 찾음 -> 정답 갱신

    }
}
