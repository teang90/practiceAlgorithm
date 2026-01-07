package study.programmers;

import java.util.*;

public class Programmers_43163 {

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        Map<String, Integer> dist = new HashMap<>();
        for (int i = 0; i < words.length; i++)
            dist.put(words[i], -1);

        bfs(begin, words, dist);

        return dist.get(target)==null?0:(dist.get(target)==-1?0:dist.get(target));
    }
    void bfs(String begin, String[] words, Map<String, Integer> dist) {
        Queue<String> q = new LinkedList<>();
        q.add(begin);
        dist.put(begin, 0);

        while (!q.isEmpty()){
            String key = q.poll();

            for (int i = 0; i < words.length; i++) {
                // 현재 key -> 에서 다른 단어로 갈 수 있는 단어 목록
                if(!isAdjWord(key, words[i], key.length())) continue;
                if(dist.get(words[i]) >= 0) continue;

//                System.out.println(String.format("%s, %s, key-> %d, word->%d", key, words[i], dist.get(key), dist.get(words[i])));
                dist.put(words[i], dist.get(key)+1);
                q.add(words[i]);

            }
        }

    }

    boolean isAdjWord(String begin, String target, int length) {
        for (int idx = 0; idx < begin.length(); idx++) {
            if(begin.charAt(idx)==target.charAt(idx)) length--;
        }
        return length == 1;
    }

    public static void main(String[] args) {
        Programmers_43163 p = new Programmers_43163();
//        int res = p.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"});
        int res = p.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"});
        System.out.println(res);
    }
}
