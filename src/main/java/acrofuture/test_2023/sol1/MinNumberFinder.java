package acrofuture.test_2023.sol1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MinNumberFinder {
    public static void main(String[] args) {
        int[] arr = {1, 2, 25, 8, 5, 10, 100, 6};
        sol(arr);
    }
    static String sol(int[] nums){
        long strLength = Arrays.stream(nums).mapToObj(String::valueOf).collect(Collectors.joining()).length();
        List<String> strings = Arrays.stream(nums).mapToObj(String::valueOf).collect(Collectors.toList());

        String answer = "";
        Collections.sort(strings, (n1, n2)->{
            int a = (n2+n1).compareTo(n1+n2);
            return a;
        });

        System.out.println(Arrays.toString(strings.toArray()));


        return answer;
    }

}

// 주연 인베스트먼트
// 2060 0077 9601 수협
