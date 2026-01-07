package study.programmers;

public class Programmers_43165 {
    static int answer;

    public int solution(int[] numbers, int target) {
        dfs(0, numbers, target, 0);
        return answer;
    }
    static void dfs(int sIdx, int[] numbers, int target, int sum){
        if(sIdx == numbers.length){
            if(sum==target) answer++;

        }else{
            dfs(sIdx+1, numbers, target, sum + numbers[sIdx]);
            dfs(sIdx+1, numbers, target, sum - numbers[sIdx]);
        }
    }
    public static void main(String[] args) {
        Programmers_43165 p = new Programmers_43165();
        int res = p.solution(new int[]{4,1,2,1}, 4);
//        p.solution(new int[]{1, 1, 1, 1, 1}, 3);
        System.out.println(res);

    }
}


