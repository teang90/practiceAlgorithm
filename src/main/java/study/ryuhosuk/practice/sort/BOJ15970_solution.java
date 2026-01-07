package study.ryuhosuk.practice.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ15970_solution {
    static int N;
    static List<Integer>[] colorList;
    static int sumLofLength=0; // 점 간격의 누적 합

    /** 정답의 최대치 : 최대의 점일때 /화살표 길이의 합의 최대값: 5000 * 10^5 = 2500 * 2 * 10^5 */
    static void input(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        colorList = new ArrayList[N];
        for(int color=0; color<N; color++){
            colorList[color] = new ArrayList();
        }

        for (int i=0; i<N; i++){
            String[] oneLine = sc.nextLine().split(" ");
            int pos = Integer.parseInt(oneLine[0]);
            int color = Integer.parseInt(oneLine[1]);

            colorList[color].add(pos);
        }
    }

    // 점 갯수: N <= 5000
    // 0 <= 점 위치: N <= 10^5
    // 점의 색깔 [1,N]
    static void pro() {
        for (int color=0; color < colorList.length; color++){   //색깔별 배열에서 리스트 뽑아내기
            List<Integer> listOfCoord = colorList[color];
            Collections.sort(listOfCoord); // 각 점 집합 정렬

            for(int idx=0; idx<listOfCoord.size(); idx++){
                // 현재 j 점(버택스)에 대한 좌,우 점들의 거리 구하기
                int distOfLeft = getDistanceOfLeft(color, idx);
                int distOfRight = getDistanceOfRight(color, idx);
                sumLofLength += Math.min(distOfLeft, distOfRight);
            }
        }

        System.out.println(sumLofLength);
    }

    static int getDistanceOfLeft(int color, int idx){
        if(idx==0) return Integer.MAX_VALUE;    // 좌측에 더 이상 점 없는경우(시작점)
        return colorList[color].get(idx) - colorList[color].get(idx-1);
    }

    static int getDistanceOfRight(int color, int idx){  
        if(idx+1 == colorList.length) return Integer.MAX_VALUE; // 우측에 더 이상 점이 없음, 우측끝점
        return colorList[color].get(idx+1) - colorList[color].get(idx);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

}
