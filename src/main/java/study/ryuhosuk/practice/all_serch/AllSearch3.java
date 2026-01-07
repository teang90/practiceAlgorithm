package study.ryuhosuk.practice.all_serch;

import java.util.Scanner;

// 완전 탐색
public class AllSearch3 { // 순서가 존재 & 중복허용
    static StringBuilder sb = new StringBuilder();
    static int n; // 1~4
    static int m; //
    int[] selected;
    int[] used;
    void input(){
        Scanner sc = new Scanner(System.in);
//        n = sc.nextInt();
//        m = sc.nextInt();
        n=7;
        m=3;
        selected = new int[m+1];
        used = new int[n];
    }

    public static void main(String[] args) {
        // 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
        AllSearch3 host = new AllSearch3();
        host.input();
        host.recursive1(1);
        System.out.println(sb.toString());
    }

    void recursive1(int k){
        if(k > m){ // 다 고른경우
            for (int i=1; i<=m; i++){
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
        }else{ // 진행중
            for (int cand=1; cand <= n; cand++){
                boolean isUsed = false;

                for(int j=1; j<k; j++){
                    if(cand==selected[j]){
                        isUsed = true;
                    }
                }
                if(!isUsed){
                    selected[k] = cand;
                    recursive1(k+1); // // k+1번~M번을 모두 탐색하애하는 일을하는 상황
//                    selected[k] = 0; // 다 끝나면 관례적으로 0으로 release... -> ??

                }

            }
        }
    }


    /** idx를 파라미터(n의 값들) 저장 여부로 사용하는 방식 */
    void recursive2(int k){
        if(k > m){ // 다 고른경우
            for (int i=1; i<=m; i++){
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
        }else{ // 진행중
            for (int cand=1; cand <= n; cand++){
                boolean isUsed = false;

                if(used[k] == 1) continue;

                selected[k] = cand;
                used[cand] = 1;

                recursive2(k+1);


                selected[k]=0;
                used[cand]=0;

            }
        }
    }
}
