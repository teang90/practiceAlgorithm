package study.ryuhosuk.practice.all_serch;

import java.util.Scanner;
import java.util.stream.Stream;

// 완전 탐색
public class AllSearch {
    static StringBuilder sb = new StringBuilder();
    static int n; // 1~4
    static int m; //
    int[] selected;
    void input(){
        Scanner sc = new Scanner(System.in);
//        n = sc.nextInt();
//        m = sc.nextInt();
        n=7;
        m=3;
        selected = new int[m+1];
    }

    public static void main(String[] args) {
        // 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
        AllSearch host = new AllSearch();
        host.input();
        System.out.println(n);
        System.out.println(m);
        host.recursive1(1);
        System.out.println(sb.toString());
    }
    // 1부터 n까지 자연수 중에서 M개를 고른 수열, 중복허용
    public void recursive1(int k){

        if(k==n+1){
          Stream.of(selected).forEach(s->sb.append(s).append(""));
        }else{
            for (int cand = 1; cand<=n; cand++){
                selected[k]=cand;
                recursive1(k+1);
            }
        }

    }

//    void recursive1(int k){
//        if(k > m){ // 다 고른경우
//            for (int i=1; i<=m; i++){
//                sb.append(selected[i]).append(" ");
//            }
//            sb.append("\n");
//        }else{ // 진행중
//            for (int cand=1; cand <= n; cand++){
//                selected[k] = cand;
//                // k+1번~M번을 모두 탐색하애하는 일을하는 상황
//                recursive1(k+1);
//                selected[k] = 0; // 다 끝나면 관례적으로 0으로 release...
//            }
//        }
//    }

}
