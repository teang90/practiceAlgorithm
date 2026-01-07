package acrofuture.test_2023.sol1;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sol3 {
    public static void main(String[] args) {

        Sol3 main = new Sol3();
        int n = 20;
        String text = "가나다abc123가나다abc123가나다abc123가나다abc123가나다abc123가나다abc123가나다abc123가나다abc123";
        List<String> list = main.solution3(text, n);
        System.out.println(list.toString());

    }
    public List<String> solution3(String text, int n) {

        int point = 0;
        String tmp ="";

        for (int i = 0; i < text.length(); i++) {
            // 한글, 영문, 숫자 구분
            if(text.charAt(i)>='가'&&text.charAt(i)<='힣') {
                point += 2;
                tmp += text.charAt(i);
            }else {
                point += 1;
                tmp += text.charAt(i);
            }
            // 주어진 n을 초과하는지 확인
            if(point == n) {
                tmp += " ";
                point = 0;
            } else if (point > n) {
                tmp = tmp.substring(0, tmp.length() - 1);
                tmp += " ";
                point = 0;
                i = i - 1;
            }

        }
        // " " 구분자로 잘라서 List에 담기
        List<String> answer = Arrays.asList(tmp.split(" "));

        return answer;

    }
}
