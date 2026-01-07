package study.self_repeat;

import java.util.Scanner;

public class BOJ16472_1_solution {
    // 고양이 언어 번역기는 문자열을 주면 그 중에서 최대 N개의 종류의 알파벳을 가진 연속된 문자열밖에 인식하지 못한다.
    // 이 번역기가 인식할 수 있는 최대 문자열의 길이는 얼마인가?
    static int N;   // 인식할 수 있는 알파벳의 최대 개수 N [1, 26]
    static String str;  // 주어진 문자열
    static Scanner sc = new Scanner(System.in);
    static int[] cntArr = new int[26]; //알파벳 사용 현황을 파악하기 위함
    static int kind;
    static void input(){
        N = Integer.parseInt(sc.nextLine());
        str = sc.nextLine();
    }
    static void add(char x){
        cntArr[x-'a']++;
        if(cntArr[x-'a']==1) kind++;    // 새롭게 나타난 알파벳
    }
    
    static void erase(char x){
        cntArr[x-'a']--;
        if(cntArr[x-'a']==0) kind--;    // 새롭게 나타난 알파벳
    }

    static void sol(){
        int length = str.length(), ans=0;
        // R을 한 칸씩 우측으로 이동
        for(int R=0, L=0; R<length; R++){
            // R번쨰 문자를 오른쪽에 추가
            add(str.charAt(R));
            
            // 불가능하면 가능할때까지 L을 이동
            while(kind > N){    // 더이상 문자 추가가 불가능한 경우에는 좌측부터 문자를 제거한다.
                erase(str.charAt(L));
                L++;
            }

            ans = Math.max(ans, R-L+1);
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        sol();
    }
}

