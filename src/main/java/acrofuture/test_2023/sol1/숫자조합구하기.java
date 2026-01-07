package acrofuture.test_2023.sol1;

public class 숫자조합구하기 {

    public static void main(String[] args) {

        Solution result = new Solution();

        int[] numbers = { 11,6,10,0};
//        int[] numbers = { 3, 30, 5, 34, 9, 1};

        System.out.println(result.solution1(numbers));

    }
}

class Solution {
    public String solution1(int[] numbers) {

        String[] str = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            str[i] = Integer.toString(numbers[i]);

        } //

        for (int i = 0; i < str.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < str.length; j++) {
                if ((str[j] + str[index]).compareTo(str[index] + str[j]) < 0) {
                    index = j;
                }
            }

            String temp = str[i];
            str[i] = str[index];
            str[index] = temp;

        } //

        String result = "";
        for (int i = 0; i < str.length; i++) {
            result += str[i];

        } //

        return result.toString();
    }
}


