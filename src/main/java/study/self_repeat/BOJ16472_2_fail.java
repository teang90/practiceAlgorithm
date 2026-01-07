package study.self_repeat;

import java.util.Scanner;

public class BOJ16472_2_fail {
    // 고양이 언어 번역기는 문자열을 주면 그 중에서 최대 N개의 종류의 알파벳을 가진 연속된 문자열밖에 인식하지 못한다.
    // 이 번역기가 인식할 수 있는 최대 문자열의 길이는 얼마인가?
    static int N;   // 인식할 수 있는 알파벳의 최대 개수 N [1, 26]
    static String str;  // 주어진 문자열
    static Scanner sc = new Scanner(System.in);
    static int[] cntArr = new int[26]; //알파벳 사용 현황을 파악하기 위함
    static int kind;    // 현재 코드가 돌면서 문자열에 사용된 종류의 갯수   (N보다 크면 안 됨)
    static void input(){
        N = Integer.parseInt(sc.nextLine());
        str = sc.nextLine();
    }

    /** TODO 이 풀이 틀렸음... -> 일단 류호석님 풀이방법 익힐것 */
    static void sol(){
        int length=Integer.MIN_VALUE, R=-1;

        // 중복을 검사해야하니까, 카운트 배열사용(나중에 카운트 map 사용해도 될듯)
        for(int L=0; L < str.length(); L++){
            // 중복된 문자열이 없는 선에서 R을 우측으로 이동하면서 문자열을 만들자
            while (R+1<str.length() && kind <= N)
                if(++cntArr[str.charAt(++R)-'a']==1) kind++;

            if(--cntArr[str.charAt(L)-'a']==0) kind--;

            length=Math.max(length, R-L);
        }
        System.out.println(length);
    }

    public static void main(String[] args) {
        input();
        sol();
    }
}

