package study.ryuhosuk.practice.all_serch;

import java.util.Scanner;

// BOJ 14888
/*
N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다. 또, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다. 연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)으로만 이루어져 있다.

우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다. 이때, 주어진 수의 순서를 바꾸면 안 된다.

예를 들어, 6개의 수로 이루어진 수열이 1, 2, 3, 4, 5, 6이고, 주어진 연산자가 덧셈(+) 2개, 뺄셈(-) 1개, 곱셈(×) 1개, 나눗셈(÷) 1개인 경우에는 총 60가지의 식을 만들 수 있다. 예를 들어, 아래와 같은 식을 만들 수 있다.

1+2+3-4×5÷6
1÷2+3+4-5×6
1+2÷3×4-5+6
1÷2×3-4+5+6
식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다. 또, 나눗셈은 정수 나눗셈으로 몫만 취한다. 음수를 양수로 나눌 때는 C++14의 기준을 따른다. 즉, 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다. 이에 따라서, 위의 식 4개의 결과를 계산해보면 아래와 같다.

1+2+3-4×5÷6 = 1
1÷2+3+4-5×6 = 12
1+2÷3×4-5+6 = 5
1÷2×3-4+5+6 = 7
N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.
* */
public class AllSearch_Applied {
    static int N, max, min;
    static int[] nums, operators, order;

    static int calculator(){
        int val = nums[1];

        for (int i=1; i < N-1; i++){
            if (order[i] == 1)
                val += nums[i+1];
            if (order[i] == 2)
                val *= nums[i+1];
            if (order[i] == 3)
                val -= nums[i+1];
            if (order[i] == 4)
                val /= nums[i+1];
        }

        return val;
    }

    // order에 1 ~ N-1의 연산자를 순서대로 저장
    public static void recur(int k){
        if(k==N){   //모든 연산자들을 전부 나열하는 방법을 찾음

            // 정한 연산자 순서대로 계산해서 정답을 갱신
            int res = calculator();
            max = Math.max(max, res);
            min = Math.max(min, res);

        }else{ // k 번쨰 연산자는 무엇을 선택할 것인가?
            
            // 4가지의 연산자들 중에 뭘 쓸지 선택(무작위 순열) & 재귀 호출
            for (int cand = 1; cand <= 4; cand++){
                if(operators[cand] > 0){
                    operators[cand]--;
                    order[k] = cand;
                    recur(k+1);
                    operators[cand]++;
                    order[k]=0;
                }
            }
        }
    }

    public static void input(){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
//        operators = new int[N-1];
//        order = new int[N-1];
        nums = new int[N+1];
        operators = new int[5]; // 연산자 4개이므로
        order = new int[N+1];

        for (int i=1; i<=N; i++){
            nums[i]= sc.nextInt();
        }

        for (int i=1; i<4; i++){
            operators[i] = sc.nextInt();
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        input();
        recur(1);
    }


}