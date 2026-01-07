package study.self_repeat;

import java.util.Scanner;

public class BOJ13144 {
    static int N;   // [1, 100000]
    static int[] nums;  // 수열의 각 원소는 [1, 100000]
    static int[] cntArr = new int[100000];  // [1, 100000] 범위의 어떤 숫자가 임의로 나올지 모르기떄문에 range는 100000까지 잡는다.
    static Scanner sc = new Scanner(System.in);
    static void input() {
        N = Integer.parseInt(sc.nextLine());
        nums = new int[N];
        String[] arr = sc.nextLine().split(" ");
        for(int i=0; i<N; i++) nums[i]=Integer.parseInt(arr[i]);
    }

    // 길이가 N인 수열이 주어질 때, 수열에서 연속한 1개 이상의 수를 뽑았을 때
    // 같은 수가 여러 번 등장하지 않는 경우의 수를 구하는 프로그램을 작성하여라.
    static void sol() {
        // TODO
//        int R=0, answer=0;    // do-while 세팅
        int R=-1, answer=0;
        for(int L=0; L<N; L++){
            // 일단 R을 중복된 숫자가 없을때까지 우측으로 이동시키자.
            while(R+1<N && cntArr[nums[R+1]]==0){
                R++;
                cntArr[nums[R]]++;
                answer += R-L+1;        // R-L+1 이 계산식으로 경우의 수 나오는 절차 알아보기
            }

//----------------------------------------------
            // R=0;으로 세팅해서 do-while로 처리하는데, while 조건이 false인데도 do의 body를 탐... 뭐지?
//            boolean tOfF = true;
//            do {
//                System.out.println("시작: "+R);
//                cntArr[nums[R]]++;
//                R++;
//                answer++;
//                System.out.println("종료: "+R);
//                tOfF = (R<N && cntArr[nums[R]]==0);
//                if(!tOfF) break;
//            } while (tOfF);
//----------------------------------------------

            // while을 못 돈다는건 중복된 숫자가 있다는것이다. -> 이제 L을 우측으로 이동하면서 중복된걸 뺴주자
            cntArr[nums[L]]--;
        }
        System.out.println(answer);
    }

    public static void main(String[] args) {
        input();
        sol();
    }
}