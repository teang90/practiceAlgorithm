package study.ryuhosuk.practice.sort;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ15970 {
    static int N;
    static List[] posList;
    static int sumLofLength=0; // 점 간격의 누적 합

    /** 정답의 최대치 : 최대의 점일때 /화살표 길이의 합의 최대값: 5000 * 10^5 = 2500 * 2 * 10^5 */
    static void input(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        posList = new List[N];

        for (int i=0; i<N; i++){
            Elem elem = new Elem();
            String[] oneLine = sc.nextLine().split(" ");
            elem.pos = Integer.parseInt(oneLine[0]);
            elem.color = Integer.parseInt(oneLine[1]);

            if(posList[elem.color]==null){
                posList[elem.color] = new ArrayList();
            }
            posList[elem.color].add(elem);
        }
    }

    // 점 갯수: N <= 5000
    // 0 <= 점 위치: N <= 10^5
    // 점의 색깔 [1,N]
    static void pro() {
        for (int i=0; i<posList.length; i++){   //색깔별 배열에서 리스트 뽑아내기
            List<Elem> listOfColor = posList[i];
            Collections.sort(listOfColor); // 각 점 집합 정렬

            for(int j=0; j<listOfColor.size(); j++){
                // 현재 j 점(버택스)에 대한 좌,우 점들의 거리 구하기
                int distOfLeft = getDistanceOfLeft(j, listOfColor);
                int distOfRight = getDistanceOfRight(j, listOfColor);
                sumLofLength += Math.min(distOfLeft, distOfRight);
            }
        }

        System.out.println(sumLofLength);
    }

    static int getDistanceOfLeft(int idx, List<Elem> posList){
        Elem e = posList.get(idx);
        Elem eOfLeft = posList.get(idx-1);
        return eOfLeft==null? Integer.MAX_VALUE : e.pos - eOfLeft.pos; // 좌측에 점이 없으면 -1
    }

    static int getDistanceOfRight(int idx, List<Elem> posList){
        Elem e = posList.get(idx);
        Elem eOfRight = posList.get(idx+1);
        return eOfRight==null? Integer.MAX_VALUE : eOfRight.pos - e.pos; // 좌측에 점이 없으면 -1
    }

    static class Elem implements Comparable<Elem>{
        int pos;
        int color;
        @Override
        public int compareTo(Elem o) {
            return pos - o.pos;
        }
    }

    public static void main(String[] args) {
        input();
        pro();
    }

}
