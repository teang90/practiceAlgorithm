package acrofuture.test_2023.sol1;

import java.util.ArrayList;
import java.util.List;

public class 자동줄바꿈 {

    public static void main(String[] args) {

        Solutions result = new Solutions();

        String text = "가나다abc123가나다abc123가나다abc123가나"
                + "다abc123가나다abc123가나다abc123가나다"
                + "abc123가나다abc123";

        int n = 20;

        List<String> r = result.solution3(text, n);

        for (int i = 0; i < r.size(); i++) {
            System.out.println(r.get(i));
        }

    }

}

class Solutions {
    public List<String> solution3(String text, int n) {
        List<String> result = new ArrayList<>();
        int index = 0;
        int length = text.length();
        int kor = n;

        while ( index < length) {
            int a = length - index;

            if (a <= n) {
                result.add(text.substring(index));
                index = length;

            } else {
                if (text.charAt(index) >= '가' && text.charAt(index) <= '힣') {
                    kor = n / 2;
                }

                int end = index + kor;
                while (end > index && text.charAt(end) != ' ' && text.charAt(end) != '\t') {
                    end--;
                }

                if (end == index) {
                    end = index + kor;
                }
                result.add(text.substring( index , end).trim());
                index = end;
            }
        }

        return result;
    }
}
