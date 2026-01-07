package study.ryuhosuk.practice.all_serch;

public class BruteForce15652 {
//    1~N까지 중 M개를 고른 수열, 중복허용, 고른 순서가 비내림차순(2, 3 (O) / 3,2 (X))
    // -> 중복허용, 순서존재
    static int N, M;
    static int[] selected = new int[100];

    public static void recurs(int k){
        if(k==N+1){
            // 출력
        }else{
            int start = selected[k-1];
            if(start==0) start=1;

            for (int cand = start; cand<=N; cand++){
                selected[k] = cand;
                recurs(k+1);
                selected[k]=0;
            }
        }
    }
}
