package study.ryuhosuk.practice.all_serch;

import java.util.Scanner;

// 완전 탐색
public class AllSearch2 {
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
        AllSearch2 host = new AllSearch2();
        host.input();
        host.recursive1(1);
        System.out.println(sb.toString());
    }

    // 자연수 N,M이 주어짐, 1부터 N까지의 자연수 중 중복 없이 M개를 고른 수열 (1<=M <= N<=8))
    void recursive3(int k){
       if (k==n+1){

       }else{
           for (int cand=1; cand<=n; cand++){
                boolean isUsed = false;
                for(int i=1; i<=k; i++){
                   if(selected[i]==cand){
                       isUsed=true;
                   }
                }
                if(!isUsed){
                    selected[k]=cand;
                    recursive3(k+1);
                    selected[k]=0;
                }


           }
       }
    }
    int[] alreadyUsed = new int[100];
    void recursive4(int k){
        if (k==n+1){

        }else{
            for (int cand=1; cand<=n; cand++){
                if(alreadyUsed[cand]==1) continue; // cand idx는 이미 사용(1~N까지의 자연수임을 착안하여 idx와 동일함을 이용)

                selected[k]= cand;
                alreadyUsed[cand] = 1;

                recursive4(k+1);

                selected[k] = 0;
                alreadyUsed[cand] = 0;
            }
        }
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
                    if(cand==selected[j]){      // 중복하는지 검사
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
