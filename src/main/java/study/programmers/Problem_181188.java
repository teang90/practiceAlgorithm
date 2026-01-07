package study.programmers;

import java.util.*;

public class Problem_181188 {

    static int solution(int[][] targets) {
        int answer=0;
        Arrays.sort(targets, Comparator.comparingInt(a -> a[1]));
        int start = targets[0][1];  // 최초 폭격 미사일의 끝점
        answer++;   // 미사일 하나 사용
        
        for (int i = 0; i < targets.length; i++) {
            if(targets[i][0] >= start){
                answer++;
                start = targets[i][1];
            }
        }

        return answer;
    }

}
