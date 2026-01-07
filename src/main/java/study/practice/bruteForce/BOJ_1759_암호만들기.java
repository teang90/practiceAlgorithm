package study.practice.bruteForce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class BOJ_1759_암호만들기 {
    private static StringBuilder sb = new StringBuilder();
    private static int L, C;
    private static String[] nums;
    private static String[] selected;
    static final List<String> result = List.of("a", "e", "i", "o", "u");

    public static void main(String[] args) throws Exception {
        input();
        sol(0, 0);
        System.out.println(sb);
    }

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("\\s+");
        L = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);
        selected = new String[L];
        nums = br.readLine().split("\\s+");
        Arrays.sort(nums);
    }

    private static void sol(int k, int startIdx){
        if(k==L){
            if(isPossible(selected)){
                for (int i = 0; i < L; i++) {
                    sb.append(selected[i]);
                }
                sb.append("\n");
            }
            return;
        }

        // 중복 없이, 순서 있음
        for (int i = startIdx; i < C; i++) {
            selected[k] = nums[i];
            sol(k+1, i+1);
            // sol(k+1, startIdx+1); -> 내가 잘 못 생각한 부분, 지금 현재 인덱스인 i의 다음인 i+1을 넘겨야한다.
            selected[k] = null;
        }

    }

    private static boolean isPossible(String[] selected){
        int vowel=0;
        int consonant=0;

        for (int i = 0; i < L; i++) {
            if(result.contains(selected[i])){
                vowel++;
            }else{
                consonant++;
            }
        }

        return vowel>=1 && consonant>=2;
    }



}
