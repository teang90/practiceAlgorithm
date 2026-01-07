package study.self_repeat;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ11652_1 {
    // 1) 갖고있는 카드중에 가장 많은 카드의 값을 출력,
    // 2) 가장 많은 정수가 여러가지라면 작은 것을 출력 -> 이건 어차피 정렬을 할테니까 > 조건이면 같은건 이상조건이 아니기 때문에 안됨
    // 즉 정렬해서 최빈 갯수가 같더라도, 앞서 나타난(정렬상 앞에 배치된값)이 출력된다.
    static int N;   // 가지고 있는 카드의 갯수
    static long[] card;
    static Scanner sc = new Scanner(System.in);

    static void input(){
        N = Integer.parseInt(sc.nextLine().trim());
        card = new long[N];
        for(int i=0; i<N; i++) card[i] = Long.parseLong(sc.nextLine().trim());
    }

    static void sol() {
        Arrays.sort(card);  // 주어진 수 정렬
        
        int manyCnt = Integer.MIN_VALUE;
        int curCnt = 1;
        long prevCard = card[0];
        long mostFrequencyNum = card[0];
        for(int i=1; i<N; i++){
            // 이전 카드와 하나씩 비교
            if(prevCard == card[i]){    // -> prevCard 변수쓰지말고 card[n-1]로해서 if(card[n-1]==card[n])로 분기처리해도됨
                curCnt++;   // 이전과 같은 카드 +1
            }else if(prevCard != card[i]){
                curCnt = 1; // 새롭게 등장한 카드
            }

            if(curCnt > manyCnt){
                mostFrequencyNum = card[i];
                manyCnt = curCnt;
            }
        }
        System.out.println(mostFrequencyNum);
    }

    public static void main(String[] args) {
        input();
        sol();
    }
}
