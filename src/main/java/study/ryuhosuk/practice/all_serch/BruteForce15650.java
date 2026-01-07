package study.ryuhosuk.practice.all_serch;

public class BruteForce15650 {
//    1~N까지 중 중복 허용X M개를 고른 수열 , 오름차순

    static int N, M;
    static int[] selected = new int[100];

    public static void recurs(int k){
        if(k==N+1){
            // 출력
        }else{
            for(int cand=selected[k-1]+1; cand<=N; cand++){
                selected[k]=cand;
                recurs(k+1);
                selected[k]=0;
            }

        }
    }
}
