package study.self_repeat;

import java.util.Scanner;

public class BOJ16472_1 {
    // 고양이 언어 번역기는 문자열을 주면 그 중에서 최대 N개의 종류의 알파벳을 가진 연속된 문자열밖에 인식하지 못한다.
    // 이 번역기가 인식할 수 있는 최대 문자열의 길이는 얼마인가?
    static int N;   // 인식할 수 있는 알파벳의 최대 개수 N [1, 26]
    static String str;  // 주어진 문자열
    static Scanner sc = new Scanner(System.in);
    static int[] cntArr = new int[26]; //알파벳 사용 현황을 파악하기 위함
    static void input(){
        N = Integer.parseInt(sc.nextLine());
        str = sc.nextLine();
    }
    static boolean composable(int idx) {
        // idx: R+1번쨰 문자열
        char alphabet = str.charAt(idx); // 이 문자가 현재 카운트 배열에 몇개쨰 있는지?
        int cntOfTargetIdx = cntArr[alphabet-'a']; // 현재 alphabet의 idx 번쨰에 있는 갯수
        int kind=0;
        for(int i=0; i<cntArr.length; i++){
            if(i==alphabet-'a'){
                cntArr[i]++;
            }
            if(cntArr[i]>0) kind++;
        }
        if(kind <= N) return true;
        else cntArr[alphabet-'a']--;

        return false;
    }
    static void sol(){
        int length=Integer.MIN_VALUE, R=-1;

        // 중복을 검사해야하니까, 카운트 배열사용(나중에 카운트 map 사용해도 될듯)
        for(int L=0; L < str.length(); L++){
            // 중복된 문자열이 없는 선에서 R을 우측으로 이동하면서 문자열을 만들자
            while (R+1 < str.length() && composable(R+1)){
                R++;
//                cntArr[str.charAt(R+1)-'a']++;    // 이거 또 잘못했네... count arr.. 이미 composable에서 처리해서 여기서는 필요 없다고...
                length = Math.max(length, R-L+1);
            }

            cntArr[str.charAt(L)-'a']--;
        }

        System.out.println(length);
    }

    public static void main(String[] args) {
        input();
        sol();
    }
}

